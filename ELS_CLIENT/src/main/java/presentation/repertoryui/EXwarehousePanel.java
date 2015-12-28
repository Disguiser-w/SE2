package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.repertorybl.controller.GoodsController;
import businesslogic.repertorybl.controller.RepertoryController;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import presentation.special_ui.MySearchField;
import vo.InventoryVO;
import vo.UserVO;

public class EXwarehousePanel extends OperationPanel {
	
	private static final long serialVersionUID = 23L;
	
	private RepertoryFrame repertoryFrame;
	
	private RepertoryController repertoryControl;
	private GoodsController goodsControl;
	
	private MySearchField searchField;

	public MyTable exwarehouseMessageTable;
	
	private MyLabel leaveRepertoryLabel;
	
	private ArrayList<InventoryVO> inventorys;
	
	private String goodsID;
	private String repertoryID;
	
	public EXwarehousePanel(RepertoryFrame repertoryFrame, RepertoryController repertoryController, GoodsController goodsController, UserVO userVO) {
		
		this.repertoryFrame = repertoryFrame;
		
		repertoryControl = repertoryController;
		goodsControl = goodsController;
		
		repertoryID = userVO.organization;
		
		searchField = new MySearchField();

		leaveRepertoryLabel = new MyLabel("出库");

		inventorys = repertoryControl.inventoryStockTaking();
		
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
		
		searchField.setBounds((int) (width * 17.677336747759284 / 25), (int) (height * 1.0007142857142858 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.5003571428571428 / 22));
		exwarehouseMessageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 2.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 14.535714285714286 / 20));
		leaveRepertoryLabel.setBounds((int) (width * 11.224839948783611 / 25), (int) (height * 17.7607142857142858 / 20),
				(int) (width * 2.8303571428571428 / 25), (int) (height * 1.503571428571428 / 22));
	}

	private void setBaseInfos(){
		String[] head = {"订单号", "所在区名", "行号", "架号", "位号", "进入本仓库时间"};
		int[] widths = {150, 100, 60, 60, 60, 163};
		
		exwarehouseMessageTable = new MyTable(head, getInfos(), widths, true);
		add(exwarehouseMessageTable);
	}
	
	private ArrayList<String[]> getInfos(){
		inventorys = repertoryControl.inventoryStockTaking();
		ArrayList<String[]> infos = new ArrayList<String[]>();
		
		for(InventoryVO inventoryvo : inventorys){
			goodsID = inventoryvo.good.Order_ID;
			String time = goodsControl.getEnterSpecificRepertoryDate(goodsID, repertoryID);
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
				returnNum += repertoryControl.leaveRepertory(orderID);
			}
			if(returnNum == 0)
				successLeave();
			else
				failedLeave();
		}
	}
	
	public void updateui(boolean updateOthers){
		exwarehouseMessageTable.setInfos(getInfos());
		if(updateOthers == true){
			repertoryFrame.warehousingMainPanel.updateui(false);
			repertoryFrame.inventoryVertificationPanel.updateui(false);
			repertoryFrame.createReceiptPanel.updateui(false);
			repertoryFrame.lookReceiptPanel.updateui(false);
		}
	}
	
	public void successLeave(){
		updateui(true);
		JOptionPane.showMessageDialog(null, "出库成功(●'◡'●)", "出库成功", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void failedLeave(){
		updateui(true);
		JOptionPane.showMessageDialog(null, "出库失败(T_T)", "出库失败", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
