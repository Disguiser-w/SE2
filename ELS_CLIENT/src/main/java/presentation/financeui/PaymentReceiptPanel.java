package presentation.financeui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businesslogic.financebl.controller.PaymentReceiptBLController;
import presentation.commonui.DateChooser;
import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.OperationPanel;
import type.ReceiptState;
import type.ReceiptType;
import vo.PaymentItemVO;
import vo.PaymentReceiptVO;
import vo.UserVO;

public class PaymentReceiptPanel extends OperationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private JLabel dateChooseLabel;
	private JButton dateOKButton;
	private JButton cancelButton;
	private MyLabel totalLabel;

	private JLabel function;
	private JLabel clauseLabel;
	private JLabel date;

	private JTextField date_Input;
	
	private MyTable paymentTable;
	
	public ArrayList<PaymentReceiptVO> paymentReceiptVOs;
	public ArrayList<PaymentItemVO> paymentItemVOs;

	private MyComboBox<String> clause_choose;
	
	private int clauseInt;
	private String clause;

	public PaymentReceiptBLController controller;
	public FinanceFrame financeFrame;
	public UserVO user;

	public PaymentReceiptPanel(PaymentReceiptBLController controller,FinanceFrame parent,UserVO user) {
		this.controller=controller;
		this.financeFrame=parent;
		this.user = user;
		dateChooseLabel = new JLabel("日期");
		dateOKButton = new JButton("确认");
		totalLabel = new MyLabel("合计");
		cancelButton =new JButton("取消");
	

		function = new JLabel("新建付款单");
		clauseLabel = new JLabel("条目");
		date = new JLabel("日期");

		date_Input = new JTextField("2015/11", 8);

		clause_choose = new MyComboBox<String>();
		clause_choose.addItem("运费");
		clause_choose.addItem("租金");
		clause_choose.addItem("工资");
		
		paymentItemVOs = new ArrayList<PaymentItemVO>();
		clauseInt=-1;

		

		setLayout(null);

		add(dateChooseLabel);
		add(dateOKButton);
		add(totalLabel);
		add(cancelButton);
		add(function);
		add(clauseLabel);
		add(date);
		add(date_Input);
		add(clause_choose);
		
	
		dateChooseLabel.setLayout(new BorderLayout());
		dateChooseLabel.add(new DateChooser(date_Input),BorderLayout.CENTER);
		addListener();
		setBaseInfo();
		
	}

	
	public void setBaseInfo(){
		String[] head = new String[]{"日期","条目","金额","付款账户","操作人"};
		int[] widths = new int[]{150,100,70,70,70,70,60};
		paymentTable = new MyTable(head, getInfos(paymentItemVOs), widths, false);
		add(paymentTable);
	}

	public ArrayList<String[]> getInfos(ArrayList<PaymentItemVO> vos){
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		if(vos!= null){
			for(PaymentItemVO v : vos){
				lineInfo.add(new String[]{v.date,v.clause,v.money+"",v.account,v.userID});
			}
			return lineInfo;
		}
		return new ArrayList<String[]>();
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		dateChooseLabel.setBounds((int)(width * 13.174744897959184/25),(int)(height * 2.7268101761252447/20),(int)(width *  1.0204081632653061 /25),(int)(height *   1.1741682974559686/20));
		dateOKButton.setBounds((int)(width * 18.752551020408163/25),(int)(height * 2.7268101761252447/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.1741682974559686/20));
		totalLabel.setBounds((int)(width * 21.739795918367346/25),(int)(height * 18.434442270058707/20),(int)(width *  2.072704081632653 /25),(int)(height *   1.1741682974559686/20));
		cancelButton.setBounds((int)(width *  21.739795918367346/25),(int)(height * 2.7268101761252447/20),(int)(width *  2.1364795918367347 /25),(int)(height *  1.1741682974559686/20));
		function.setBounds((int)(width * 1.913265306122449/25),(int)(height * 0.6653620352250489/20),(int)(width *  6.919642857142857 /25),(int)(height *  1.643835616438356/20));
		clauseLabel.setBounds((int)(width * 2.74234693877551/25),(int)(height *  2.7268101761252447/20),(int)(width *  1.594387755102041 /25),(int)(height *   1.1741682974559686/20));
		date.setBounds((int)(width * 7.844387755102041/25),(int)(height * 2.7268101761252447/20),(int)(width *  1.4349489795918366 /25),(int)(height *   1.1741682974559686/20));
		date_Input.setBounds((int)(width * 9.948979591836734/25),(int)(height * 2.7268101761252447/20),(int)(width *  2.2002551020408165 /25),(int)(height *  1.1741682974559686/20));
		clause_choose.setBounds((int)(width * 4.878826530612245/25),(int)(height * 2.7268101761252447/20),(int)(width *  2.072704081632653 /25),(int)(height * 1.1741682974559686/20));
		paymentTable.setLocationAndSize((int)(width * 1.1510204081632653/25),(int)(height * 4.04481409001957/20),(int)(width *  22.92091836734694 /25),(int)(height *  13.95890410958904/20));
		}
	

	/**
	 * 监听方法
	 * */
	
	public void addListener(){

		dateOKButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				ok();
			}
		});
		
		totalLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				totalui();
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancelui();
			}
		});

		
		
		clause_choose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				clauseui();
			}
		});

	
	}
	
	public void clauseui(){
		clauseInt = clause_choose.getSelectedIndex();
		if(clauseInt == 0){
			clause = "运费";
		}
		else if(clauseInt ==1){
			clause = "租金";
		}
		else if(clauseInt == 2){
			clause = "工资";
		}
	}

	
	/**
	 * 选取相应的条目和时间从而计算金额（这里需要设定每个条目只能选取一次）
	 * */
	public void ok() {
		String time =date_Input.getText();
		if(clause == "运费"){
			PaymentItemVO vo = new PaymentItemVO(time, clause, "总账", controller.getFare(time), user.userID);
			paymentItemVOs.add(vo);
		}
		if(clause == "租金"){
			PaymentItemVO vo = new PaymentItemVO(time, clause, "总账", controller.getRent(time), user.userID);
			paymentItemVOs.add(vo);
		}
		if(clause == "工资"){
			PaymentItemVO vo = new PaymentItemVO(time, clause, "总账", controller.getSalary(time), user.userID);
			paymentItemVOs.add(vo);
		}
		paymentTable.setInfos(getInfos(paymentItemVOs));
	}
	
	public void totalui(){
		String time="";
		double rent= 0;
		double fare = 0;
		double salary = 0;
		for(PaymentItemVO v :paymentItemVOs){
			time = v.date;
			if(v.clause.equals("运费")){
				fare=v.money;
			}
			if(v.clause.equals("租金")){
				rent = v.money;
			}
			if(v.clause.equals("工资")){
				salary = v.money;
			}
		}
		PaymentReceiptVO vo = new PaymentReceiptVO(controller.getPaymentReceiptListID(), user.userID, ReceiptType.PAYMENTRECEIPT,
				ReceiptState.DRAFT, rent, fare, salary, time, "总账", user.userName);
		controller.creatPaymentReceipt(vo);
	}
	
	public void cancelui(){
		financeFrame.toMainPanel();
	}

	
}


	
	
