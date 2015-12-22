package presentation.managerui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import businesslogic.managebl.ReviewReceiptBL;

import type.ReceiptState;
import vo.CollectionReceiptVO;
import vo.DistributeReceiptVO;
import vo.EnIntermediateReceiptVO;
import vo.EnVehicleReceiptVO;
import vo.GatheringReceiptVO;
import vo.OrderAcceptReceiptVO;
import vo.PaymentReceiptVO;
import vo.TransferingReceiptVO;

//import presentation.commonui.LocationHelper;

public class CheckReceiptPanel extends OperationPanel {
	
	private static final long serialVersionUID = 16L;
	
	private ReviewReceiptBL receiptBL;
	
	private MyLabel detailedInfoLabel;
	private MyLabel modifyLabel;
	private MyLabel approveLabel;

	private MyTable messageTable;
	private MyTable currentTable;

	private String[] receiptCategoryList = {"全部", "收款单","合计收款单","付款单","中转中心装车单","中转中心到达单","营业厅装车单","营业厅到达单","派件单","入库单","出库单"};
	private MyTextLabel receiptCategoryLabel;
	private MyComboBox<String> receiptCategoryChoose;

	ArrayList<GatheringReceiptVO> gatheringList;
	ArrayList<CollectionReceiptVO> collectionList;
	ArrayList<PaymentReceiptVO> paymentList;
	ArrayList<EnIntermediateReceiptVO> enIntermediateList;
	ArrayList<TransferingReceiptVO> transferingList;
	ArrayList<EnVehicleReceiptVO> enVehicleList;
	ArrayList<OrderAcceptReceiptVO> orderAcceptList;
	ArrayList<DistributeReceiptVO> distributeList;
	
	private int tableWidth;
	private int tableHeight;
	
//	private LocationHelper helper;

	public CheckReceiptPanel() {

		receiptBL = new ReviewReceiptBL();
		
		modifyLabel = new MyLabel("修改");
		approveLabel = new MyLabel("通过");
		detailedInfoLabel = new MyLabel("查看详情");
		
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
		receiptCategoryChoose.addItem(receiptCategoryList[10]);
		
		gatheringList = receiptBL.getAllSubmittedGatheringReceipt();
		collectionList = receiptBL.getAllSubmittedCollectionReceipt();
		paymentList = receiptBL.getAllSubmittedPaymentReceipt();
		enIntermediateList = receiptBL.getAllSubmittedEnIntermediateReceipt();
		transferingList = receiptBL.getAllSubmittedTransferingReceipt();
		enVehicleList = receiptBL.getAllSubmittedEnVehicleReceipt();
		orderAcceptList = receiptBL.getAllSubmittedOrderAcceptReceipt();
		distributeList = receiptBL.getAllSubmittedDistributeReceipt();

		receiptCategoryChoose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int chosen = receiptCategoryChoose.getSelectedIndex();
				System.out.println(chosen);
				setBaseInfos(chosen);
			}
		});
		
		setLayout(null);
		
		add(modifyLabel);
		add(approveLabel);
		add(detailedInfoLabel);
		add(receiptCategoryLabel);
		add(receiptCategoryChoose);

//		helper = new LocationHelper(this);
		setBaseInfos(2);
		/*给setBaseInfos加上参数，不同的int值表示MyTable加载不同内容
		0代表All
		1代表Gathering， 2代表Collection，3代表Payment，4代表EnIntermediateList
		5代表transfering,6代表enVehicle,7代表orderAccept,8代表distribute
		9代表enterRepertory,10代表leaveRepertory*/
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		this.tableWidth = width;
		this.tableHeight = height;
		
		detailedInfoLabel.setBounds((int) (width * 4.522407170294494 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 1.6965428937259923 / 25), (int) (height * 1.3392857142857142 / 20));
		modifyLabel.setBounds((int) (width * 7.755441741357235 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 1.824583866837388 / 25), (int) (height * 1.3392857142857142 / 20));
		approveLabel.setBounds((int) (width * 10.084507042253522 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 1.824583866837388 / 25), (int) (height * 1.3392857142857142 / 20));
		receiptCategoryLabel.setBounds((int) (width * 14.505121638924457 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 3.2 / 25), (int) (height * 1.3392857142857142 / 20));
		receiptCategoryChoose.setBounds((int) (width * 18.005121638924457 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 4.353393085787452 / 25), (int) (height * 1.3392857142857142 / 20));
		messageTable.setLocationAndSize((int) (width * 1.9846350832266326 / 25), (int) (height * 3.464285714285714 / 20),
				(int) (width * 22.02304737516005 / 25), (int) (height * 15.723214285714286 / 20));
	}

	
	private void setBaseInfos(int num){
		String[] head = new String[]{"单据编号", "单据类型", "提交人/机构编号", "提交人类型", "提交时间", "单据状态"};
		int[] widths = new int[]{130, 80, 100, 80, 100, 70};
		currentTable = new MyTable(head, getInfos(num), widths, true);
		changeTable(currentTable);
	}
	
	private ArrayList<String[]> getInfos(int num){
		ArrayList<String[]> infos = new ArrayList<String[]>();
		switch(num){
		case 0:
			break;
		case 1:
			for(GatheringReceiptVO gatheringvo : gatheringList)
				infos.add(new String[]{gatheringvo.receiptID, "收款单", gatheringvo.businesshall.name, "营业厅业务员", gatheringvo.time, stateName(gatheringvo.receiptState)});
			break;
		case 2:
			for(CollectionReceiptVO collectionvo : collectionList)
				infos.add(new String[]{collectionvo.ID, "合计收款单", collectionvo.userID, "财务人员", collectionvo.createTime, stateName(collectionvo.state)});
				break;
		case 3:
			for(PaymentReceiptVO paymentvo : paymentList)
				infos.add(new String[]{paymentvo.ID, "付款单", paymentvo.userID, "财务人员", paymentvo.createTime, stateName(paymentvo.state)});
				break;
		case 4:
			for(EnIntermediateReceiptVO enIntermediatevo : enIntermediateList)
				infos.add(new String[]{enIntermediatevo.ID, "中转中心装车单", enIntermediatevo.userID, "中转中心业务员", enIntermediatevo.createTime, stateName(enIntermediatevo.state)});
				break;
		case 5:
			for(TransferingReceiptVO transferingvo : transferingList)
				infos.add(new String[]{transferingvo.ID, "中转单", transferingvo.userID, "中转中心业务员", transferingvo.createTime, stateName(transferingvo.state)});
				break;
		case 6:
			for(EnVehicleReceiptVO enVehiclevo : enVehicleList)
				infos.add(new String[]{enVehiclevo.receiptID, "营业厅装车单", enVehiclevo.placeOfDeparture.name, "营业厅业务员", enVehiclevo.time, stateName(enVehiclevo.receiptState)});
				break;
		case 7:
			for(OrderAcceptReceiptVO orderAcceptvo : orderAcceptList)
				infos.add(new String[]{orderAcceptvo.receiptID, "营业厅接收单", orderAcceptvo.local.name, "营业厅业务员", orderAcceptvo.time, stateName(orderAcceptvo.receiptState)});
				break;		
		case 8:
			for(DistributeReceiptVO distributevo : distributeList)
				infos.add(new String[]{distributevo.ID, "营业厅派件单", "营业厅", "营业厅业务员", distributevo.time, stateName(distributevo.receiptState)});
				break;		
		case 9:
			for(PaymentReceiptVO paymentvo : paymentList)
				infos.add(new String[]{paymentvo.ID, "合计收款单", paymentvo.userID, "财务人员", paymentvo.createTime, stateName(paymentvo.state)});
				break;
		case 10:
			for(PaymentReceiptVO paymentvo : paymentList)
				infos.add(new String[]{paymentvo.ID, "合计收款单", paymentvo.userID, "财务人员", paymentvo.createTime, stateName(paymentvo.state)});
				break;		
		default:
				break;
		}
		return infos;
	}
	
	public void changeTable(MyTable currentTable){
		if(messageTable != null){
			remove(messageTable);
		}
		messageTable = currentTable;
		messageTable.setLocationAndSize((int) (tableWidth * 1.9846350832266326 / 25), (int) (tableHeight * 3.464285714285714 / 20),
				(int) (tableWidth * 22.02304737516005 / 25), (int) (tableHeight * 15.723214285714286 / 20));
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
