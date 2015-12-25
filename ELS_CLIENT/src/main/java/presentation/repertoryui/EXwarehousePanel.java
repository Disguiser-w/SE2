package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.MySearchField;
import businesslogic.repertorybl.GoodsBL;
import businesslogic.repertorybl.RepertoryBL;
import vo.InventoryVO;
import vo.UserVO;

public class EXwarehousePanel extends OperationPanel {
	
	private static final long serialVersionUID = 23L;
	
	private RepertoryFrame repertoryFrame;
	
	private RepertoryBL repertoryBL;
	private GoodsBL goodsBL;
	
	private MySearchField searchField;

	public MyTable exwarehouseMessageTable;
	
	private MyLabel leaveRepertoryLabel;
	
	private ArrayList<InventoryVO> inventorys;
	
	private String goodsID;
	private String repertoryID;
	
	private int selectedIndex;
	
	public EXwarehousePanel(RepertoryFrame repertoryFrame, UserVO userVO) {
		
		this.repertoryFrame = repertoryFrame;
		
		repertoryBL = new RepertoryBL(userVO.userID);
		goodsBL = new GoodsBL();
		
		repertoryID = userVO.organization;
		
		searchField = new MySearchField();

		leaveRepertoryLabel = new MyLabel("出库");

		inventorys = repertoryBL.inventoryStockTaking();
		
		setLayout(null);
		add(searchField);
		add(leaveRepertoryLabel);

		addListener();
		setBaseInfos();
	}

	
	public void addListener(){
		searchField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				searchui();
			}
		});
		
		leaveRepertoryLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				leaveui();
			}
		});
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		searchField.setBounds((int) (width * 20.070422535211268 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 1.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		exwarehouseMessageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 2.8053571428571428 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 12.53571428571428 / 20));
		leaveRepertoryLabel.setBounds((int) (width * 10.979513444302176 / 25), (int) (height * 15.410714285714285 / 20),
				(int) (width * 2.592829705505762 / 25), (int) (height * 1.4285714285714286 / 20));
	}

	private void setBaseInfos(){
		String[] head = {"订单号", "所在区名", "行号", "架号", "位号", "进入本仓库时间"};
		int[] widths = {150, 100, 60, 60, 60, 163};
		
		exwarehouseMessageTable = new MyTable(head, getInfos(), widths, true);
		add(exwarehouseMessageTable);
	}
	
	private ArrayList<String[]> getInfos(){
		inventorys = repertoryBL.inventoryStockTaking();
		ArrayList<String[]> infos = new ArrayList<String[]>();
		
		for(InventoryVO inventoryvo : inventorys){
			goodsID = inventoryvo.good.Order_ID;
			String time = goodsBL.getEnterSpecificRepertoryDate(goodsID, repertoryID);
			infos.add(new String[]{inventoryvo.good.Order_ID, blockName(inventoryvo.blockNum), inventoryvo.rowNum+"", inventoryvo.shelfNum+"", inventoryvo.digitNum+"", time});
		}
		return infos;
	}
	
	public String blockName(int blockNum){
		String[] blockNameList = {"飞机","火车","汽车","机动"};
		return blockNameList[blockNum];
	}
	
	public void searchui(){
		
	}
	
	public void leaveui(){
		ArrayList<Integer> selectedIndexs = exwarehouseMessageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的仓库管理员，选中某一个或某一些货物后再进行出库操作哦！", "没有选择货物", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else{
			int returnNum = 0;
			for(int i : selectedIndexs){
				String orderID = inventorys.get(i).good.Order_ID;
				returnNum += repertoryBL.leaveRepertory(orderID);
			}
			if(returnNum == 0)
				successLeave();
			else
				failedLeave();
		}
	}
	
	public void updateui(int i){//i用来说明调用完WarehousingMainPanel的updateui方法后，自己就不需要再update自己了
		exwarehouseMessageTable.setInfos(getInfos());
		if(i == 1){
			repertoryFrame.warehousingMainPanel.updateui(2);
		}
	}
	
	public void successLeave(){
		updateui(1);
		JOptionPane.showMessageDialog(null, "出库成功(●'◡'●)", "出库成功", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void failedLeave(){
		updateui(1);
		JOptionPane.showMessageDialog(null, "出库失败(T_T)", "出库失败", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
