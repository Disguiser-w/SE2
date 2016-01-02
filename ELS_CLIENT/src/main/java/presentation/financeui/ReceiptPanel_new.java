package presentation.financeui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.financebl.controller.PaymentReceiptBLController;
import businesslogic.managebl.controller.OrganizationManageController;
import businesslogic.receiptbl.GetDate;
import common.FileExporter;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import type.ReceiptState;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;
import vo.UserVO;

public class ReceiptPanel_new extends  OperationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private MyLabel sendButton;
	private MyLabel printButton;
	private MyLabel collectionReceiptButton_new;
	private MyLabel paymentReceiptButton_new;
	

//	private JLabel function;
	private MyLabel collectionReceiptInfo;
	private MyLabel paymentReceiptInfo;
	
	private MyTable currentTable;
	private MyTable collectionTable;
	private MyTable paymentTable;
	
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	ArrayList<CollectionReceiptVO> collectionReceiptVOs;
	ArrayList<PaymentReceiptVO> paymentReceiptVOs;
	
	 public CollectionReceiptBLController collectionController;
	 public PaymentReceiptBLController paymentReceiptBLController;
	 public OrganizationManageController organizationController;
	 public FinanceFrame financeFrame;
	 public UserVO user;
	 
	 public AccountManagementPanel_main accountManagementPanel_main;
		public CostIncomeReceiptPanel_new costIncomeReceiptPanel_new;
		public LogDiaryPanel logDiaryPanel;

	public ReceiptPanel_new(CollectionReceiptBLController collectionController, PaymentReceiptBLController paymentReceiptBLController
			            ,FinanceFrame parent,UserVO user,OrganizationManageController organizationController,AccountManagementPanel_main accountManagementPanel_main,
						CostIncomeReceiptPanel_new costIncomeReceiptPanel_new,LogDiaryPanel logDiaryPanel) {
		this.collectionController=collectionController;
		this.paymentReceiptBLController=paymentReceiptBLController;
		this.financeFrame=parent;
		this.user = user;
		this.organizationController = organizationController;
		
		this.logDiaryPanel=logDiaryPanel;
		
		this.accountManagementPanel_main = accountManagementPanel_main;
		this.costIncomeReceiptPanel_new = costIncomeReceiptPanel_new;
		
		sendButton = new MyLabel("发送");
		printButton = new MyLabel("导出");
		collectionReceiptButton_new = new MyLabel("新建入款单");
		paymentReceiptButton_new = new MyLabel("新建付款单");
//		function = new JLabel("新建表单");
		collectionReceiptInfo = new MyLabel("入款单");
		paymentReceiptInfo = new MyLabel("付款单");
		

		addListener();

		setLayout(null);

		add(sendButton);
		add(printButton);
	
		add(collectionReceiptButton_new);
		add(paymentReceiptButton_new);
	

//		add(function);
		add(collectionReceiptInfo);
		add(paymentReceiptInfo);
		setCollectionBaseInfo();
		currentTable = collectionTable;
		add(currentTable);

		setCmpLocation(currentTable);
		setVisible(true);

	}

	

	public void setCmpLocation(MyTable table){
		printButton.setBounds((int)(PANEL_WIDTH * 21.01019387755102/25),(int)(PANEL_HEIGHT *  0.93052837573385516/20),(int)(PANEL_WIDTH * 2.7683673469387754 /25),(int)(PANEL_HEIGHT *  1.4090019569471623/20));
		collectionReceiptButton_new.setBounds((int)(PANEL_WIDTH * 12.258928571428571/25),(int)(PANEL_HEIGHT *  0.93052837573385516/20),(int)(PANEL_WIDTH *  3.1364795918367347 /25),(int)(PANEL_HEIGHT *  1.4090019569471623/20));
		paymentReceiptButton_new.setBounds((int)(PANEL_WIDTH * 15.746173469387756/25),(int)(PANEL_HEIGHT *  0.93052837573385516/20),(int)(PANEL_WIDTH *  3.1364795918367347 /25),(int)(PANEL_HEIGHT *  1.4090019569471623/20));
//		function.setBounds((int)(PANEL_WIDTH * 1.2755102040816326/25),(int)(PANEL_HEIGHT * 0.7045009784735812/20),(int)(PANEL_WIDTH *  5.133928571428571 /25),(int)(PANEL_HEIGHT *  2.0743639921722115/20));
		collectionReceiptInfo.setBounds((int)(PANEL_WIDTH * 1.371173469387755/25),(int)(PANEL_HEIGHT * 0.93052837573385516/20),(int)(PANEL_WIDTH * 2.7683673469387754 /25),(int)(PANEL_HEIGHT *  1.4090019569471623/20));
		paymentReceiptInfo.setBounds((int)(PANEL_WIDTH * 4.865816326530612/25),(int)(PANEL_HEIGHT *  0.93052837573385516/20),(int)(PANEL_WIDTH *  2.7683673469387754 /25),(int)(PANEL_HEIGHT *  1.4090019569471623/20));
		 table.setLocationAndSize((int)(PANEL_WIDTH * 1.071173469387755/25),(int)(PANEL_HEIGHT * 2.683757338551859/20),(int)(PANEL_WIDTH *  23.651785714285715 /25),(int)(PANEL_HEIGHT *  19.819960861056751/22));
	}
	
	public void setBounds(int x, int y, int width, int height,MyTable table) {
		super.setBounds(x, y, width, height);
		super.setBounds(x, y, PANEL_WIDTH, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation(table);
	}
	
	
	/**
	 * 设置收款单表格基础信息
	 * */
	public void setCollectionBaseInfo(){
		String[] head = new String[]{"收款单编号","收款日期","收款金额","收款人","账户","单据状态"};
		int[] widths = { 160, 100, 70, 120, 81,100  };
		
		collectionReceiptVOs = collectionController.getAllCollection();
		collectionTable = new MyTable(head, getCollectionInfos(collectionReceiptVOs), widths,false);		
	}
	/**
	 * 获取收款单信息
	 * */
	public ArrayList<String[]> getCollectionInfos(ArrayList<CollectionReceiptVO> cvos){
		ArrayList<String[]> lineInfos = new ArrayList<String[]>();
		if(cvos!=null){
		for(CollectionReceiptVO v : cvos){
			String state = null;
			if(v.state == ReceiptState.APPROVE){
				state = "审批通过";
			}
			else if(v.state == ReceiptState.DISAPPROVE){
				state = "审批未通过";
			}
			else if(v.state == ReceiptState.DRAFT){
				state = "草稿状态";
			}
			else{
				state = "提交状态";
			}
			lineInfos.add(new String[]{v.ID,v.date,v.totalMoney+"",v.userID,v.account,state});
		}
		return lineInfos;
		}
		else{
			return new ArrayList<String[]>();
		}
	}
	
	/**
	 * 设置付款单表格基本信息
	 * */
	public void setPaymentBaseInfo(){
		String[] head = new String[]{"付款单编号","付款日期","金额","付款人","账户","单据状态"};
		int[] widths = { 160, 100, 70, 120, 81,100 };
		paymentReceiptVOs = paymentReceiptBLController.getAllPaymentReceipt();
		paymentTable = new MyTable(head, getPaymentInfos(paymentReceiptVOs), widths, false);		
	}
	/**
	 * 获取付款单信息
	 * */
	public ArrayList<String[]> getPaymentInfos(ArrayList<PaymentReceiptVO> pvos){
		ArrayList<String[]> lineInfos = new ArrayList<String[]>();
		if(pvos!=null){
		for(PaymentReceiptVO v : pvos){
			String state = null;
			if(v.state == ReceiptState.APPROVE){
				state = "审批通过";
			}
			else if(v.state == ReceiptState.DISAPPROVE){
				state = "审批未通过";
			}
			else if(v.state == ReceiptState.DRAFT){
				state = "草稿状态";
			}
			else{
				state = "提交状态";
			}
			lineInfos.add(new String[]{v.ID,v.date,v.cost+"",v.userID,v.account,state});
		}
		return lineInfos;
		}
		else{
			return new ArrayList<String[]>();
		}
	}
	/**
	 * 表格跳转
	 * */
	public void changeTable(MyTable table){
		if(currentTable!=null){
			remove(currentTable);
		}
			currentTable = table;
			add(currentTable);
			setCmpLocation(table);
			currentTable.repaint();
	}

	public void addListener(){
		/**
		 * 显示所有入款单
		 * */
		collectionReceiptInfo.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				showCollection();
				
			}
		});
		
		/**
		 * 显示所有付款单
		 * */
		paymentReceiptInfo.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				showPayment();
			}
		});
		sendButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				sendui();
			}
		});

		printButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
			printui();
			}
		});


		collectionReceiptButton_new.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
			new1ui();
			}
		});

		paymentReceiptButton_new.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
			new2ui();
			}
		});

		
	}

	/**
	 * 刷新付款单table
	 * */
	public void showCollection(){
		setCollectionBaseInfo();
		changeTable(collectionTable);
		collectionTable.repaint();
	}
	
	/**
	 * 刷新入款单table
	 * */
	public void showPayment(){
		setPaymentBaseInfo();
		changeTable(paymentTable);
		paymentTable.repaint();
	}
	/**
	 * 不需要了，因为一旦生成单据就自动提交给总经理
	 * */
	public void sendui() {

	}

	/**
	 * 导出入款单和付款单的方法
	 * */
	public void printui() {
		//导出入款单
		if(currentTable == collectionTable){
			collectionReceiptVOs = collectionController.getAllCollection();
			String[][] collectionExcel= new String[collectionReceiptVOs.size()][6];
			int temp =0;
			for(CollectionReceiptVO v : collectionReceiptVOs){
				String state = null;
				if(v.state == ReceiptState.APPROVE){
					state = "审批通过";
				}
				else if(v.state == ReceiptState.DISAPPROVE){
					state = "审批未通过";
				}
				else if(v.state == ReceiptState.DRAFT){
					state = "草稿状态";
				}
				else{
					state = "提交状态";
				}
				collectionExcel[temp][0] = v.ID;
				collectionExcel[temp][1] = v.date;
				collectionExcel[temp][2] = v.totalMoney+"";
				collectionExcel[temp][3] = v.userID;
				collectionExcel[temp][4] = v.account;
				collectionExcel[temp][5] = state;
				
				temp++;
			}
			String[] head = new String[]{"收款单编号","收款日期","收款金额","收款人","账户","单据状态"};
			try {
				FileExporter.exportExcel("collection.xls", head, collectionExcel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//导出付款单
		else{
			paymentReceiptVOs = paymentReceiptBLController.getAllPaymentReceipt();
			String[][] paymentExcel= new String[paymentReceiptVOs.size()][6];
			int temp =0;
			for(PaymentReceiptVO v : paymentReceiptVOs){
				String state = null;
				if(v.state == ReceiptState.APPROVE){
					state = "审批通过";
				}
				else if(v.state == ReceiptState.DISAPPROVE){
					state = "审批未通过";
				}
				else if(v.state == ReceiptState.DRAFT){
					state = "草稿状态";
				}
				else{
					state = "提交状态";
				}
				paymentExcel[temp][0] = v.ID;
				paymentExcel[temp][1] = v.date;
				paymentExcel[temp][2] = v.cost+"";
				paymentExcel[temp][3] = v.userID;
				paymentExcel[temp][4] = v.account;
				paymentExcel[temp][5] = state;
				temp++;
			}
			String[] head = new String[]{"付款单编号","付款日期","付款金额","付款人","账户","单据状态"};
			try {
				FileExporter.exportExcel(GetDate.getTime()+"payment.xls", head, paymentExcel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	public void new1ui() {
		collectionReceiptButton_new.reSet();
		financeFrame.changePanel(new CollectionReceiptPanel(collectionController, financeFrame,user,organizationController,accountManagementPanel_main
				,costIncomeReceiptPanel_new,logDiaryPanel,this));
	}

	public void new2ui() {
		paymentReceiptButton_new.reSet();
		financeFrame.changePanel(new PaymentReceiptPanel(paymentReceiptBLController,financeFrame,user,accountManagementPanel_main
				,costIncomeReceiptPanel_new,logDiaryPanel,this));
	}

}