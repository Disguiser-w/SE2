package presentation.managerui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import businesslogic.managebl.ReviewReceiptBL;
import type.ReceiptState;
import type.ReceiptType;
import vo.CollectionReceiptVO;
import vo.DistributeReceiptVO;
import vo.EnIntermediateReceiptVO;
import vo.EnVehicleReceiptVO;
import vo.EnplaningReceiptVO;
import vo.GatheringReceiptVO;
import vo.OrderAcceptReceiptVO;
import vo.PaymentReceiptVO;
import vo.ReceiptVO;
import vo.TransferingReceiptVO;

//import presentation.commonui.LocationHelper;

public class CheckReceiptPanel extends OperationPanel {
	
	private static final long serialVersionUID = 16L;
	
	private ReviewReceiptBL receiptBL;
	
	private MyLabel seeMoreButton;
	private MyLabel modifyButton;
	private MyLabel passButton;

	private MyTable messageTable;

	private String[] receiptCategoryList = {"收款单","合计收款单","付款单","中转中心装车单","中转中心到达单","营业厅装车单","营业厅到达单","派件单","入库单","出库单","全部"};
	private MyTextLabel receiptCategoryLabel;
	private MyComboBox<String> receiptCategoryChoose;
	private ArrayList<JCheckBox> selectReceipt;

	ArrayList<CollectionReceiptVO> collectionList;
	ArrayList<PaymentReceiptVO> paymentList;
	ArrayList<ReceiptVO> receiptList;
	private int num;
	
//	private LocationHelper helper;

	public CheckReceiptPanel() {

		receiptBL = new ReviewReceiptBL();
		
		modifyButton = new MyLabel("修改");
		passButton = new MyLabel("通过");
		seeMoreButton = new MyLabel("查看详情");
		
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
		
		
		selectReceipt = new ArrayList<JCheckBox>();
		for (int i = 0; i < 8; i++) {
			JCheckBox box = new JCheckBox();
			selectReceipt.add(box);
			add(box);
		}
		
		collectionList = receiptBL.getAllSubmittedCollectionReceipt();
		paymentList = receiptBL.getAllSubmittedPaymentReceipt();
		receiptList = new ArrayList<ReceiptVO>();
		
		receiptCategoryChoose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int chosen = receiptCategoryChoose.getSelectedIndex();
				System.out.println(chosen);
				changeTableMessage(chosen);
			}
		});
		
		setLayout(null);
		
		add(modifyButton);
		add(passButton);
		add(seeMoreButton);
		add(receiptCategoryLabel);
		add(receiptCategoryChoose);
		add(messageTable);

//		helper = new LocationHelper(this);
		setBaseInfos();
		
		changeTableMessage(1);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		seeMoreButton.setBounds((int) (width * 4.522407170294494 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 1.6965428937259923 / 25), (int) (height * 1.3392857142857142 / 20));
		modifyButton.setBounds((int) (width * 7.755441741357235 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 1.824583866837388 / 25), (int) (height * 1.3392857142857142 / 20));
		passButton.setBounds((int) (width * 10.084507042253522 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 1.824583866837388 / 25), (int) (height * 1.3392857142857142 / 20));
		receiptCategoryLabel.setBounds((int) (width * 14.505121638924457 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 3.2 / 25), (int) (height * 1.3392857142857142 / 20));
		receiptCategoryChoose.setBounds((int) (width * 18.005121638924457 / 25), (int) (height * 1.7410714285714286 / 20),
				(int) (width * 4.353393085787452 / 25), (int) (height * 1.3392857142857142 / 20));
		messageTable.setLocationAndSize((int) (width * 1.9846350832266326 / 25), (int) (height * 4.464285714285714 / 20),
				(int) (width * 22.02304737516005 / 25), (int) (height * 12.723214285714286 / 20));
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
	
	public String professionName(String userID){
		if(userID.startsWith("KD"))
			return "快递员";
		if(userID.startsWith("YYT"))
			return "营业厅业务员";
		if(userID.startsWith("ZZZX"))
			return "中转中心业务员";
		if(userID.startsWith("CK"))
			return "仓库管理员";
		if(userID.startsWith("CW"))
			return "财务人员";
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
	
	// 设置table的基本内容，图片，什么的
	private void setBaseInfos() {

	}
	
	public void changeTableMessage(int chosen){
		switch (chosen) {
		case 0: ArrayList<GatheringReceiptVO> gatheringList = receiptBL.getAllSubmittedGatheringReceipt();
				//receiptList.clear();
				/*for(int i=0;i<gatheringList.size();i++){
					receiptList.add((ReceiptVO)gatheringList.get(i));
				}*/
				repaint();
				break;
		case 1: ArrayList<CollectionReceiptVO> collectionList = receiptBL.getAllSubmittedCollectionReceipt();
				receiptList.clear();
				for(int i=0;i<collectionList.size();i++){
					receiptList.add((ReceiptVO)collectionList.get(i));
				}
				repaint();
				break;
		case 2: ArrayList<PaymentReceiptVO> paymentList = receiptBL.getAllSubmittedPaymentReceipt();
				receiptList.clear();
				for(int i=0;i<paymentList.size();i++){
					receiptList.add((ReceiptVO)paymentList.get(i));
				}
				repaint();
				break;
		case 3: ArrayList<EnIntermediateReceiptVO> enIntermediateList = receiptBL.getAllSubmittedEnIntermediateReceipt();
				receiptList.clear();
				for(int i=0;i<enIntermediateList.size();i++){
					receiptList.add((ReceiptVO)enIntermediateList.get(i));
				}
				repaint();
				break;
		case 4: ArrayList<TransferingReceiptVO> transferingList = receiptBL.getAllSubmittedTransferingReceipt();
				receiptList.clear();
				for(int i=0;i<transferingList.size();i++){
					receiptList.add((ReceiptVO)transferingList.get(i));
				}
				repaint();
				break;
		case 5: ArrayList<EnVehicleReceiptVO> enVehicleList = receiptBL.getAllSubmittedEnVehicleReceipt();
				//receiptList.clear();
				/*for(int i=0;i<enVehicleList.size();i++){
					receiptList.add((ReceiptVO)enVehicleList.get(i));
				}*/
				repaint();
				break;
		case 6: ArrayList<OrderAcceptReceiptVO> orderAcceptList = receiptBL.getAllSubmittedOrderAcceptReceipt();
				//receiptList.clear();
				/*for(int i=0;i<orderAcceptList.size();i++){
					receiptList.add((ReceiptVO)orderAcceptList.get(i));
				}*/
				repaint();
				break;
		case 7: ArrayList<DistributeReceiptVO> distributeList = receiptBL.getAllSubmittedDistributeReceipt();
				//receiptList.clear();
				/*for(int i=0;i<distributeList.size();i++){
					receiptList.add((ReceiptVO)distributeList.get(i));
				}*/
				repaint();
				break;
		/*case 8: ArrayList<GatheringReceiptVO> gatheringList = receiptBL.getAllSubmittedGatheringReceipt();
				receiptList.clear();
				for(int i=0;i<gatheringList.size();i++){
					receiptList.add((ReceiptVO)gatheringList.get(i));
				}
				repaint();
				break;
		case 9: ArrayList<CollectionReceiptVO> collectionList = receiptBL.getAllSubmittedCollectionReceipt();
				receiptList.clear();
				for(int i=0;i<gatheringList.size();i++){
					receiptList.add((ReceiptVO)collectionList.get(i));
				}
				repaint();
				break;
		case 10: ArrayList<GatheringReceiptVO> gatheringList = receiptBL.getAllSubmittedGatheringReceipt();
				receiptList.clear();
				for(int i=0;i<gatheringList.size();i++){
					receiptList.add((ReceiptVO)gatheringList.get(i));
				}
				repaint();
				break;*/
		default:
				break;
		}
	}
		
	private class MessageTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 297L;

		public int getRowCount() {
			return 8;
		}

		public int getColumnCount() {
			return 6;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			int index = num * 8 + rowIndex;

			if (index > receiptList.size() - 1)
				return null;

			ReceiptVO receiptvo = receiptList.get(index);

			switch (columnIndex) {
			case 0:
				selectReceipt.get(rowIndex).setVisible(true);
				return receiptvo.receiptID;
			case 1:
				return receiptTypeName(receiptvo.receiptID);
			case 2:
				return receiptvo.userID;
			case 3:
				return professionName(receiptvo.userID);
			case 4:
				return receiptvo.createTime;
			case 5:
				return stateName(receiptvo.state);
			default:
				return null;
			}
		}

		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "单据编号";
			case 1:
				return "单据类型";
			case 2:
				return "提交人编号";
			case 3:
				return "提交人类型";
			case 4:
				return "提交时间";
			case 5:
				return "单据状态";
			default:
				return null;
			}
		}

	}
	
	
}
