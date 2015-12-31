package presentation.repertoryui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.repertorybl.RepertoryBL;
import businesslogic.repertorybl.controller.EnterRepertoryReceiptController;
import businesslogic.repertorybl.controller.GoodsController;
import businesslogic.repertorybl.controller.LeaveRepertoryReceiptController;
import businesslogic.repertorybl.controller.RepertoryController;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import vo.GoodsVO;
import vo.UserVO;

public class CreateReceiptPanel extends OperationPanel{

	private static final long serialVersionUID = 4635802896951041367L;
	
	private RepertoryFrame repertoryFrame;
	
	private RepertoryController repertoryControl;
	private GoodsController goodsControl;
	private EnterRepertoryReceiptController enterRepertoryReceiptControl;
	private LeaveRepertoryReceiptController leaveRepertoryReceiptControl;
	
	private MyTextLabel promptLabel;
	private MyLabel createEnterReceiptLabel;
	private MyLabel createLeaveReceiptLabel;
	
	private MyTable messageTable;
	private MyTable currentTable;
	int patternNum;
	
	private MyLabel createReceiptLabel;
	
	private ArrayList<GoodsVO> goods;
	
	private int tableWidth;
	private int tableHeight;
	
	private String[] goodsIDList;
	private String[] timeList;
	
	private String nowTime;
	private String lastTime;
	
	private String repertoryID;
	private String userID;
	
	public CreateReceiptPanel(RepertoryFrame repertoryFrame, RepertoryController repertoryController, GoodsController goodsController, EnterRepertoryReceiptController enterRepertoryReceiptController, LeaveRepertoryReceiptController leaveRepertoryReceiptController, UserVO userVO){
		
		this.repertoryFrame = repertoryFrame;
		
		repertoryControl = repertoryController;
		goodsControl = goodsController;
		enterRepertoryReceiptControl = enterRepertoryReceiptController;
		leaveRepertoryReceiptControl = leaveRepertoryReceiptController;
		
		repertoryID = userVO.organization;
		userID = userVO.userID;
		
		nowTime = RepertoryBL.getTimeNow();
		lastTime = repertoryControl.getLastEnterRepertoryTime();
		
		promptLabel = new MyTextLabel("上次生成入库单据的时间: "+lastTime+"     当前时间: "+nowTime);
		createEnterReceiptLabel = new MyLabel("入库货物");
		createLeaveReceiptLabel = new MyLabel("出库货物");
		createReceiptLabel = new MyLabel("生成单据");
		
		setLayout(null);
		
		add(promptLabel);
		add(createEnterReceiptLabel);
		add(createLeaveReceiptLabel);
		add(createReceiptLabel);
		
		patternNum = 1;
		addListener();
		setBaseInfos(patternNum);
		
	}

	public void addListener(){
		createEnterReceiptLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 1;
				setBaseInfos(1);
			}	
		});
		
		createLeaveReceiptLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				patternNum = 2;
				setBaseInfos(2);
			}	
		});
		
		createReceiptLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(patternNum == 1){	//生成入库单
					int returnNum = enterRepertoryReceiptControl.addEnterRepertoryReceipt(repertoryID, userID, goodsIDList, timeList);
					if(returnNum == 0)
						successCreateReceipt(1);
					else
						failedCreateReceipt(1);
				}
				else{		//生成出库单
					int returnNum = leaveRepertoryReceiptControl.addLeaveRepertoryReceipt(repertoryID, userID, goodsIDList, timeList);
					if(returnNum == 0)
						successCreateReceipt(2);
					else
						failedCreateReceipt(2);
				}
			}	
		});
		
	}
	
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		tableWidth = width;
		tableHeight = height;
		
		promptLabel.setBounds((int) (width * 2.370422535211268 / 25), (int) (height * 1.2053571428571428 / 20),
				(int) (width * 20.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		createEnterReceiptLabel.setBounds((int) (width * 6.070422535211268 / 25), (int) (height * 2.80053571428571428 / 20),
				(int) (width * 4.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		createLeaveReceiptLabel.setBounds((int) (width * 14.200422535211268 / 25), (int) (height * 2.80053571428571428 / 20),
				(int) (width * 4.6645326504481435 / 25), (int) (height * 1.2053571428571428 / 20));
		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25), (int) (height * 4.5053571428571428 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 12.53571428571428 / 20));
		createReceiptLabel.setBounds((int) (width * 11.329513444302176 / 25), (int) (height * 17.410714285714285 / 20),
				(int) (width * 2.592829705505762 / 25), (int) (height * 1.4285714285714286 / 20));
	}
	
	private void setBaseInfos(int patternNum){
		String[] head;
		if(patternNum == 1)
			head = new String[]{"订单号","进入本仓库时间"};
		else
			head = new String[]{"订单号","离开本仓库时间"};
		
		int[] widths = {296, 325};
		
		currentTable = new MyTable(head, getInfos(patternNum), widths, false);
		changeTable(currentTable);
	}
	
	private ArrayList<String[]> getInfos(int patterNum){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		
		if(patternNum == 1){
			lastTime = repertoryControl.getLastEnterRepertoryTime();
			promptLabel.setText("上次生成入库单据的时间: "+lastTime+"      当前时间: "+nowTime);
			
			goods = repertoryControl.getEnterRepertoryGoods();
			
			int size = goods.size();
			goodsIDList = new String[size];
			timeList = new String[size];
			
			int i = 0;			
			for(GoodsVO goodsvo: goods){
				goodsIDList[i] = goodsvo.Order_ID;
				timeList[i] = goodsControl.getEnterSpecificRepertoryDate(goodsvo.Order_ID, repertoryID);
				i++;
				infos.add(new String[]{goodsvo.Order_ID, goodsControl.getEnterSpecificRepertoryDate(goodsvo.Order_ID, repertoryID)});
			}
		}
		else{
			lastTime = repertoryControl.getLastLeaveRepertoryTime();
			promptLabel.setText("上次生成出库单据的时间: "+lastTime+"     当前时间: "+nowTime);
			
			goods = repertoryControl.getLeaveRepertoryGoods();
			System.out.println(goods.size());
			
			int size = goods.size();
			goodsIDList = new String[size];
			timeList = new String[size];
			
			int i = 0;	
			for(GoodsVO goodsvo: goods){
				goodsIDList[i] = goodsvo.Order_ID;
				timeList[i] = goodsControl.getLeaveSpecificRepertoryDate(goodsvo.Order_ID, repertoryID);
				i++;
				infos.add(new String[]{goodsvo.Order_ID, goodsControl.getLeaveSpecificRepertoryDate(goodsvo.Order_ID, repertoryID)});
			}
		}
		
		return infos;
	}
	
	
	public void changeTable(MyTable currentTable){
		if(messageTable != null){
			remove(messageTable);
		}
		messageTable = currentTable;
		messageTable.setLocationAndSize((int) (tableWidth * 1.0243277848911652 / 25), (int) (tableHeight * 4.5053571428571428 / 20),
				(int) (tableWidth * 22.98335467349552 / 25), (int) (tableHeight * 12.53571428571428 / 20));
		add(messageTable);
		repaint();
		updateUI();
	}
	
	public void updateui(boolean updateOthers){
		messageTable.setInfos(getInfos(1));
		messageTable.setInfos(getInfos(2));
		messageTable.setInfos(getInfos(patternNum));
		if(updateOthers == true){
			repertoryFrame.warehousingMainPanel.updateui(false);
			repertoryFrame.warehousingMainPanel.updateui(false);
			repertoryFrame.inventoryVertificationPanel.updateui(false);
			repertoryFrame.lookReceiptPanel.updateui(false);
		}
	}
	
	public void successCreateReceipt(int patternNum){
		if(patternNum == 1){
			setBaseInfos(1);
			JOptionPane.showMessageDialog(null, "生成入库单成功(●'◡'●)", "生成单据成功", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			setBaseInfos(2);
			JOptionPane.showMessageDialog(null, "生成出库单成功(●'◡'●)", "生成单据成功", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void failedCreateReceipt(int patternNum){
		if(patternNum == 1){
			setBaseInfos(1);
			JOptionPane.showMessageDialog(null, "生成入库单失败(T_T)", "生成单据失败", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			setBaseInfos(2);
			JOptionPane.showMessageDialog(null, "生成出库单失败(T_T)", "生成单据失败", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
