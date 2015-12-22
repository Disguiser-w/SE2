package presentation.repertoryui;

import java.util.ArrayList;

import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;

import businesslogic.repertorybl.RepertoryBL;
import vo.InventoryVO;
import vo.UserVO;

public class InventoryVerificationPanel extends OperationPanel {
	
	private static final long serialVersionUID = 38L;
	
	private RepertoryBL repertoryBL;
	
	private MyTextLabel endTimeLabel;
	private MyTextLabel timeLabel;

	private MyTable messageTable;

	ArrayList<InventoryVO> inventoryList;

	public InventoryVerificationPanel(UserVO uservo){
		
		repertoryBL = new RepertoryBL(uservo.userID);
		
		String time = RepertoryBL.getTimeNow();
		endTimeLabel = new MyTextLabel("截止到");
		timeLabel = new MyTextLabel(time);

		setLayout(null);
		
		add(endTimeLabel);
		add(timeLabel);

		inventoryList = repertoryBL.inventoryStockTaking();
		
		setBaseInfos();
	}

	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		endTimeLabel.setBounds((int) (width * 8.162612035851472 / 25), (int) (height * 1.6071428571428572 / 20),
				(int) (width * 2.848911651728553 / 25), (int) (height * 1.5178571428571428 / 20));
		timeLabel.setBounds((int) (width * 11.503841229193341 / 25), (int) (height * 1.6071428571428572 / 20),
				(int) (width * 6.353393085787452 / 25), (int) (height * 1.5178571428571428 / 20));
		messageTable.setLocationAndSize((int) (width * 1.2163892445582587 / 25), (int) (height * 4.285714285714286 / 20),
				(int) (width * 22.823303457106274 / 25), (int) (height * 14.125 / 20));
	}
	
	
	private void setBaseInfos(){
		String[] head = {"订单号", "入库日期", "目的地", "区名", "排号", "架号", "位号"};
		int[] widths = {120, 120, 90, 80, 60, 60, 60};
		
		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}
	
	private ArrayList<String[]> getInfos(){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		for(InventoryVO inventoryvo : inventoryList)
			infos.add(new String[]{inventoryvo.good.Order_ID, inventoryvo.good.enterDate[0], inventoryvo.good.destination, blockName(inventoryvo.blockNum),
					inventoryvo.rowNum+"", inventoryvo.shelfNum+"", inventoryvo.digitNum+""});
		return infos;
	}
	
	
	public String blockName(int blockNum){
		String[] blockNameList = {"飞机区", "火车区", "汽车区", "机动区"};
		return blockNameList[blockNum];
	}
	
	
}



