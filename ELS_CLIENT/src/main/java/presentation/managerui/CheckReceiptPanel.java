package presentation.managerui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.managebl.controller.ReviewReceiptController;
import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import type.ReceiptState;
import vo.CollectionReceiptVO;
import vo.DistributeReceiptVO;
import vo.EnIntermediateReceiptVO;
import vo.EnVehicleReceiptVO;
import vo.EnterRepertoryReceiptVO;
import vo.GatheringReceiptVO;
import vo.LeaveRepertoryReceiptVO;
import vo.OrderAcceptReceiptVO;
import vo.PaymentReceiptVO;
import vo.TransferingReceiptVO;

//import presentation.commonui.LocationHelper;

public class CheckReceiptPanel extends OperationPanel {
	
	private static final long serialVersionUID = 16L;
	
	private ReviewReceiptController receiptControl;

	private String[] receiptCategoryList = {"收款单","合计收款单","付款单","中转中心装车单","中转中心到达单","营业厅装车单","营业厅到达单","派件单","入库单","出库单"};
	private MyTextLabel receiptCategoryLabel;
	private MyComboBox<String> receiptCategoryChoose;

	//private MyLabel detailedInfoLabel;
	private MyLabel approveLabel;
	private MyLabel updateLabel;
	
	private MyTable messageTable;
	private MyTable currentTable;

	ArrayList<GatheringReceiptVO> gatheringList;
	ArrayList<CollectionReceiptVO> collectionList;
	ArrayList<PaymentReceiptVO> paymentList;
	ArrayList<EnIntermediateReceiptVO> enIntermediateList;
	ArrayList<TransferingReceiptVO> transferingList;
	ArrayList<EnVehicleReceiptVO> enVehicleList;
	ArrayList<OrderAcceptReceiptVO> orderAcceptList;
	ArrayList<DistributeReceiptVO> distributeList;
	ArrayList<EnterRepertoryReceiptVO> enterRepertoryList;
	ArrayList<LeaveRepertoryReceiptVO> leaveRepertoryList;
	
	private int tableWidth;
	private int tableHeight;
	
	private int patternNum;
//	private LocationHelper helper;

	public CheckReceiptPanel(ReviewReceiptController reviewReceiptController){

		receiptControl = reviewReceiptController;
		
		receiptCategoryLabel = new MyTextLabel("单据类型选择");
		receiptCategoryChoose = new MyComboBox<String>();
		receiptCategoryChoose.addItem(receiptCategoryList[0]);
		receiptCategoryChoose.addItem(receiptCategoryList[1]);
		receiptCategoryChoose.addItem(receiptCategoryList[2]);
		receiptCategoryChoose.addItem(receiptCategoryList[3]);
		receiptCategoryChoose.addItem(receiptCategoryList[4]);
		receiptCategoryChoose.addItem(receiptCategoryList[5]);
		receiptCategoryChoose.addItem(receiptCategoryList[6]);
		receiptCategoryChoose.addItem(receiptCategoryList[7]);
		receiptCategoryChoose.addItem(receiptCategoryList[8]);
		receiptCategoryChoose.addItem(receiptCategoryList[9]);
		
		//detailedInfoLabel = new MyLabel("查看详情");
		approveLabel = new MyLabel("审批通过");
		updateLabel = new MyLabel("刷新");
	
		gatheringList = receiptControl.getAllSubmittedGatheringReceipt();
		collectionList = receiptControl.getAllSubmittedCollectionReceipt();
		paymentList = receiptControl.getAllSubmittedPaymentReceipt();
		enIntermediateList = receiptControl.getAllSubmittedEnIntermediateReceipt();
		transferingList = receiptControl.getAllSubmittedTransferingReceipt();
		enVehicleList = receiptControl.getAllSubmittedEnVehicleReceipt();
		orderAcceptList = receiptControl.getAllSubmittedOrderAcceptReceipt();
		distributeList = receiptControl.getAllSubmittedDistributeReceipt();
		enterRepertoryList = receiptControl.getAllSubmittedEnterRepertoryReceipt();
		leaveRepertoryList = receiptControl.getAllSubmittedLeaveRepertoryReceipt();

		receiptCategoryChoose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int chosen = receiptCategoryChoose.getSelectedIndex();
				patternNum = chosen;
				setBaseInfos(chosen);
			}
		});
		
		approveLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae){
				approveui();
			}
		});
		
		updateLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae){
				updateui();
			}
		});
		
		setLayout(null);
		
		//add(detailedInfoLabel);
		add(approveLabel);
		add(updateLabel);
		add(receiptCategoryLabel);
		add(receiptCategoryChoose);

//		helper = new LocationHelper(this);
		patternNum = 1;
		setBaseInfos(1);
		/*给setBaseInfos加上参数，不同的int值表示MyTable加载不同内容
		0代表Gathering， 1代表Collection，2代表Payment，3代表EnIntermediateList
		4代表transfering,5代表enVehicle,6代表orderAccept,7代表distribute
		8代表enterRepertory,9代表leaveRepertory*/
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		this.tableWidth = width;
		this.tableHeight = height;
		
		receiptCategoryLabel.setBounds((int) (width * 2.224839948783611 / 25), (int) (height * 1.060714285714286 / 20),
				(int) (width * 3.253393085787452 / 25), (int) (height * 1.3392857142857142 / 20));
		receiptCategoryChoose.setBounds((int) (width * 6.084507042253522 / 25), (int) (height * 1.060714285714286 / 20),
				(int) (width * 3.324583866837388 / 25), (int) (height * 1.3392857142857142 / 20));
		//detailedInfoLabel.setBounds((int) (width * 2.522407170294494 / 25), (int) (height * 1.060714285714286 / 20),
		//	(int) (width * 4.6965428937259923 / 25), (int) (height * 1.3392857142857142 / 20));
		approveLabel.setBounds((int) (width * 16.505121638924457 / 25), (int) (height * 1.060714285714286 / 20),
				(int) (width * 3 / 25), (int) (height * 1.3392857142857142 / 20));
		updateLabel.setBounds((int) (width * 20.505121638924457 / 25), (int) (height * 1.060714285714286 / 20),
				(int) (width * 1.5 / 25), (int) (height * 1.3392857142857142 / 20));
		messageTable.setLocationAndSize((int) (width * 0.5846350832266326 / 25), (int) (height * 2.851785714285714 / 20),
				(int) (width * 23.52304737516005 / 25), (int) (height * 15.723214285714286 / 20));
	}

	
	private void setBaseInfos(int num){
		String[] head = new String[]{"单据编号", "单据类型", "提交人/机构", "提交人类型", "提交时间", "单据状态"};
		int[] widths = new int[]{160, 90, 100, 90, 100, 70};
		currentTable = new MyTable(head, getInfos(num), widths, true);
		changeTable(currentTable);
	}
	
	private ArrayList<String[]> getInfos(int num){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		switch(num){
		case 0:
			for(GatheringReceiptVO gatheringvo : gatheringList)
				infos.add(new String[]{gatheringvo.receiptID, "收款单", gatheringvo.businesshall.name, "营业厅业务员", gatheringvo.time, stateName(gatheringvo.receiptState)});
			break;
		case 1:
			for(CollectionReceiptVO collectionvo : collectionList)
				infos.add(new String[]{collectionvo.ID, "合计收款单", collectionvo.userID, "财务人员", collectionvo.date, stateName(collectionvo.state)});
			break;
		case 2:
			for(PaymentReceiptVO paymentvo : paymentList)
				infos.add(new String[]{paymentvo.ID, "付款单", paymentvo.userID, "财务人员", paymentvo.date, stateName(paymentvo.state)});
			break;
		case 3:
			for(EnIntermediateReceiptVO enIntermediatevo : enIntermediateList)
				infos.add(new String[]{enIntermediatevo.ID, "中转中心装车单", enIntermediatevo.userID, "中转中心业务员", enIntermediatevo.date, stateName(enIntermediatevo.state)});
			break;
		case 4:
			for(TransferingReceiptVO transferingvo : transferingList)
				infos.add(new String[]{transferingvo.ID, "中转单", transferingvo.userID, "中转中心业务员", transferingvo.date, stateName(transferingvo.receiptState)});
			break;
		case 5:
			for(EnVehicleReceiptVO enVehiclevo : enVehicleList)
				infos.add(new String[]{enVehiclevo.receiptID, "营业厅装车单", enVehiclevo.placeOfDeparture.name, "营业厅业务员", enVehiclevo.time, stateName(enVehiclevo.receiptState)});
			break;
		case 6:
			for(OrderAcceptReceiptVO orderAcceptvo : orderAcceptList)
				infos.add(new String[]{orderAcceptvo.receiptID, "营业厅接收单", orderAcceptvo.local.name, "营业厅业务员", orderAcceptvo.time, stateName(orderAcceptvo.receiptState)});
			break;	
		case 7:
			for(DistributeReceiptVO distributevo : distributeList)
				infos.add(new String[]{distributevo.ID, "营业厅派件单", "YYT-00001", "营业厅业务员", distributevo.time, stateName(distributevo.receiptState)});
			break;		
		case 8:
			for(EnterRepertoryReceiptVO entervo : enterRepertoryList)
				infos.add(new String[]{entervo.receiptID, "入库单", entervo.userID, "财务人员", entervo.createTime, stateName(entervo.state)});
			break;
		case 9:
			for(LeaveRepertoryReceiptVO leavevo : leaveRepertoryList)
				infos.add(new String[]{leavevo.receiptID, "出库单", leavevo.userID, "财务人员", leavevo.createTime, stateName(leavevo.state)});
			break;	
		default:
				break;
		}
		return infos;
	}
	
	public void approveui(){
		ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
		int size = selectedIndexs.size();
		
		System.out.println(patternNum);
		if(size == 0){
			JOptionPane.showMessageDialog(null, "亲爱的总经理，选中某一个或某一些单据后再进行审批哦！", "没有选择用户", JOptionPane.WARNING_MESSAGE);
			return;
		}
		else{
			int returnNum = 0;
			
			if(patternNum == 0){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(gatheringList.get(i).receiptID, gatheringList.get(i));
			}
			else if(patternNum == 1){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(collectionList.get(i).receiptID, collectionList.get(i));
			}
			else if(patternNum == 2){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(paymentList.get(i).receiptID, paymentList.get(i));
			}
			else if(patternNum == 3){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(enIntermediateList.get(i).receiptID, enIntermediateList.get(i));
			}
			else if(patternNum == 4){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(transferingList.get(i).receiptID, transferingList.get(i));
			}
			else if(patternNum == 5){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(enVehicleList.get(i).receiptID, enVehicleList.get(i));
			}
			else if(patternNum == 6){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(orderAcceptList.get(i).receiptID, orderAcceptList.get(i));
			}
			else if(patternNum == 7){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(distributeList.get(i).ID, distributeList.get(i));
			}
			else if(patternNum == 8){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(enterRepertoryList.get(i).receiptID, enterRepertoryList.get(i));
				System.out.println("jiemian"+"    shenpi");
			}
			else if(patternNum == 9){
				for(int i :selectedIndexs)
					returnNum += receiptControl.approve(leaveRepertoryList.get(i).receiptID, leaveRepertoryList.get(i));
				System.out.println("jiemian"+"    shenpi");
			}

			if(returnNum == 0)
				successCheck();
			else
				failedCheck();
		}
	}
	
	public void updateui(){
		messageTable.setInfos(getInfos(patternNum));
	}
	
	public void successCheck(){
		updateui();
		JOptionPane.showMessageDialog(null, "审批成功(●'◡'●)", "审批单据成功", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//修改失败时返回上一级界面，同时给用户提示信息
	public void failedCheck(){
		updateui();
		JOptionPane.showMessageDialog(null, "审批失败(T_T)", "审批单据失败", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void changeTable(MyTable currentTable){
		if(messageTable != null){
			remove(messageTable);
		}
		messageTable = currentTable;
		messageTable.setLocationAndSize((int) (tableWidth * 0.5846350832266326 / 25), (int) (tableHeight * 3.464285714285714 / 20),
				(int) (tableWidth * 23.52304737516005 / 25), (int) (tableHeight * 15.723214285714286 / 20));
		add(messageTable);
		repaint();
		updateUI();
	}
	
	public String receiptTypeName(String receiptID){
		if(receiptID.startsWith("SKD"))
			return "收款单";
		if(receiptID.startsWith("HJSKD"))
			return "合计收款单";
		if(receiptID.startsWith("FKD"))
			return "付款单";
		if(receiptID.startsWith("ZZZXZCD"))
			return "中转中心装车单";
		if(receiptID.startsWith("ZZZXDDD"))
			return "中转中心到达单";
		if(receiptID.startsWith("YYTZCD"))
			return "营业厅装车单";
		if(receiptID.startsWith("YYTDDD"))
			return "营业厅到达单";
		if(receiptID.startsWith("PJD"))
			return "派件单";
		if(receiptID.startsWith("RKD"))
			return "入库单";
		if(receiptID.startsWith("CKD"))
			return "出库单";
		else
			return "";
	}

	public String stateName(ReceiptState state){
		if(state.equals(ReceiptState.DRAFT))
			return "草稿";
		if(state.equals(ReceiptState.SUBMIT))
			return "待审批";
		if(state.equals(ReceiptState.APPROVE))
			return "已通过";
		if(state.equals(ReceiptState.DISAPPROVE))
			return "未通过";
		else
			return "";
	}
	
}
