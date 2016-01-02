package presentation.financeui.initui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.businessbl.controller.VehicleManagerController;
import businesslogic.financebl.controller.AccountBLController;
import businesslogic.financebl.controller.InitialStockBLController;
import businesslogic.managebl.controller.OrganizationManageController;
import businesslogic.repertorybl.RepertoryBL;
import businesslogic.userbl.UserBL;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.financeui.FinanceFrame;
import presentation.financeui.LogDiaryPanel;
import vo.InitInfoVO;
import vo.UserVO;

public class InitialStockPanel_main extends OperationPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyLabel newButton;
	private MyLabel detailButton;
	

	private MyTable initTable;
	
	private int selectedIndex;

	 InitialStockBLController controller;
	 UserBL userController;
	 OrganizationManageController organizationController;
	 VehicleManagerController vehicleController;
	 RepertoryBL repertoryController;
	 AccountBLController accountController;
	 FinanceFrame financeFrame;
	 public UserVO userVO;
	 private ArrayList<InitInfoVO> initInfoVOs;
	
	 public LogDiaryPanel logDiaryPanel;
	
	public InitialStockPanel_main(InitialStockBLController controller,UserBL userController,OrganizationManageController organizationController,
			VehicleManagerController vehicleController,RepertoryBL repertoryController,AccountBLController accountController,
			FinanceFrame parent,UserVO userVO,LogDiaryPanel logDiaryPanel){
		this.controller=controller;
		this.userController=userController;
		this.organizationController=organizationController;
		this.vehicleController=vehicleController;
		this.repertoryController=repertoryController;
		this.accountController=accountController;
		this.financeFrame=parent;
		this.logDiaryPanel = logDiaryPanel;
		this.userVO=userVO;
		newButton = new MyLabel("新建");
		detailButton =new MyLabel("详情");

//		function = new JLabel("期初建账");

		setLayout(null);
		
		add(newButton);
		add(detailButton);
//		add(function);
		
		initInfoVOs = controller.getAllInitInfo();
		addListener();
		setBaseInfo();

	}

	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		detailButton.setBounds((int)(width * 17.0109693877551/25),(int)(height *  0.93052837573385516/20),(int)(width *  2.7683673469387754 /25),(int)(height *   1.1350293542074363/20));
		newButton.setBounds((int)(width * 21.01019387755102/25),(int)(height * 0.93052837573385516/20),(int)(width *  2.7682551020408165 /25),(int)(height *   1.1350293542074363/20));
		initTable.setLocationAndSize((int)(width * 1.1002551020408165/25),(int)(height * 2.505479452054795/20),(int)(width *  23.007397959183675 /25),(int)(height *  15.921154598825832/20));

	}
	
	/**
	 * 设置账户信息
	 * 建账需要加一个建账人的属性
	 * */
	public void  setBaseInfo(){
		String[] head = new String[]{"建账日期","操作员"};
		int[] width ={297,297};
		initTable = new MyTable(head,getInfos(), width, true);
		add(initTable);
	}
	
	/**
	 * 期初建账表格中的信息设置
	 * */
	public ArrayList<String[]> getInfos(){
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		initInfoVOs = controller.getAllInitInfo();
		if(initInfoVOs!= null){
		for(InitInfoVO v :  initInfoVOs){
			lineInfo.add(new String[]{v.time,v.userID});
		}
		return lineInfo;
		}
		else{
			return new ArrayList<String[]>();
		}
	}
	
	
	public void addListener(){

		newButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				newInitInfoui();
				}
		});
		
		detailButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				detailui();
			}
		});
	}

	public void startui() {

	}

	public void endui() {

	}

	public void dateOK() {

	}

	public void newInitInfoui() {
		newButton.reSet();
		financeFrame.changePanel(new InitialStockPanel_new(controller, userController,organizationController,
				vehicleController,repertoryController,accountController, financeFrame,this,userVO,logDiaryPanel));
		}
	
	public void detailui(){
		detailButton.reSet();
		ArrayList<Integer> selectedIndexs = initTable.getSelectedIndex();
		if(selectedIndexs.size()==0){
			JOptionPane.showMessageDialog(null, "请选中某一个或某一项后再查看哦！", "没有选择账户", JOptionPane.WARNING_MESSAGE);
		}
		else if(selectedIndexs.size()!=1){
			JOptionPane.showMessageDialog(null, "一次只能查看一项信息哟！", "没有选择账户", JOptionPane.WARNING_MESSAGE);
		}
		else{

			selectedIndex = selectedIndexs.get(0);
			InitInfoVO vo = initInfoVOs.get(selectedIndex);
			financeFrame.changePanel(new InitialStockPanel_detail(controller, financeFrame,vo.time));
		}
		
	/*	int row =table.getSelectedRow();
		if(row==-1){
			JOptionPane.showMessageDialog(null, "请选择需要查看的行！", "提示",
					JOptionPane.CLOSED_OPTION);
		}
		else{
			String time = im.getValueAt(row, 0);
			if(time==null){
				JOptionPane.showMessageDialog(null, "请选择需要查看的行！", "提示",
						JOptionPane.CLOSED_OPTION);
			}
			else{
				time=time.substring(0,4)+time.substring(5,7)+time.substring(8,10);
				financeFrame.changePanel(new InitialStockPanel_detail(controller, financeFrame,time));
			}
		}
		*/
	}
	public void refresh(){
		initTable.setInfos(getInfos());
	}
}
