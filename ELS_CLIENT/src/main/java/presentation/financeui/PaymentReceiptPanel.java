package presentation.financeui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businesslogic.financebl.LogDiaryBL;
import businesslogic.financebl.controller.PaymentReceiptBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.DateChooser;
import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import type.ReceiptState;
import type.ReceiptType;
import vo.LogDiaryVO;
import vo.PaymentItemVO;
import vo.PaymentReceiptVO;
import vo.UserVO;

public class PaymentReceiptPanel extends OperationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MyLabel dateChooseLabel;
	private MyLabel dateOKButton;
	private MyLabel cancelButton;
	private MyLabel totalButton;

	private MyTextLabel clauseLabel;
	private MyTextLabel date;

	private MyTextField date_Input;

	private MyTable paymentTable;

	public ArrayList<PaymentReceiptVO> paymentReceiptVOs;
	public ArrayList<PaymentItemVO> paymentItemVOs;

	private MyComboBox<String> clause_choose;

	private int clauseInt;
	private String clause;

	public PaymentReceiptBLController controller;
	public FinanceFrame financeFrame;
	public UserVO user;

	public AccountManagementPanel_main accountManagementPanel_main;
	public CostIncomeReceiptPanel_new costIncomeReceiptPanel_new;
	public LogDiaryPanel logDiaryPanel;
	public ReceiptPanel_new receiptPanel_new;

	public PaymentReceiptPanel(PaymentReceiptBLController controller, FinanceFrame parent, UserVO user,
			AccountManagementPanel_main accountManagementPanel_main,
			CostIncomeReceiptPanel_new costIncomeReceiptPanel_new, LogDiaryPanel logDiaryPanel,
			ReceiptPanel_new receiptPanel_new) {
		this.controller = controller;
		this.financeFrame = parent;
		this.user = user;

		this.accountManagementPanel_main = accountManagementPanel_main;
		this.costIncomeReceiptPanel_new = costIncomeReceiptPanel_new;
		this.logDiaryPanel = logDiaryPanel;
		this.receiptPanel_new = receiptPanel_new;
		dateChooseLabel = new MyLabel("日期");
		dateOKButton = new MyLabel("确认");
		totalButton = new MyLabel("合计");
		cancelButton = new MyLabel("返回");

		clauseLabel = new MyTextLabel("条目");
		date = new MyTextLabel("日期");

		date_Input = new MyTextField();

		clause_choose = new MyComboBox<String>();
		clause_choose.addItem("运费");
		clause_choose.addItem("租金");
		clause_choose.addItem("工资");

		paymentItemVOs = new ArrayList<PaymentItemVO>();
		clauseInt = -1;
		
		clause = "";

		setLayout(null);

		add(dateChooseLabel);
		add(dateOKButton);
		add(totalButton);
		add(cancelButton);
		// add(function);
		add(clauseLabel);
		add(date);
		add(date_Input);
		add(clause_choose);

		dateChooseLabel.setLayout(new BorderLayout());
		new DateChooser(date_Input, dateChooseLabel);
		addListener();
		setBaseInfo();

	}

	public void setBaseInfo() {
		String[] head = new String[] { "日期", "条目", "金额", "付款账户", "操作人" };
		int[] widths = new int[] { 150, 120, 120, 120, 111 };
		paymentTable = new MyTable(head, getInfos(paymentItemVOs), widths, false);
		add(paymentTable);
	}

	public ArrayList<String[]> getInfos(ArrayList<PaymentItemVO> vos) {
		ArrayList<String[]> lineInfo = new ArrayList<String[]>();
		if (vos != null) {
			for (PaymentItemVO v : vos) {
				lineInfo.add(new String[] { v.date, v.clause, v.money + "", v.account, v.userID });
			}
			return lineInfo;
		}
		return new ArrayList<String[]>();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		dateChooseLabel.setBounds((int) (width * 13.174744897959184 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1741682974559686 / 20));
		dateOKButton.setBounds((int) (width * 21.01019387755102 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		clauseLabel.setBounds((int) (width * 2.24234693877551 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 1.594387755102041 / 25), (int) (height * 1.1741682974559686 / 20));
		date.setBounds((int) (width * 7.844387755102041 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 1.4349489795918366 / 25), (int) (height * 1.1741682974559686 / 20));
		date_Input.setBounds((int) (width * 8.948979591836734 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 3.874234693877551 / 25), (int) (height * 1.1741682974559686 / 20));
		clause_choose.setBounds((int) (width * 3.878826530612245 / 25), (int) (height * 0.93052837573385516 / 20),
				(int) (width * 3.072704081632653 / 25), (int) (height * 1.1741682974559686 / 20));
		paymentTable.setLocationAndSize((int) (width * 1.1002551020408165 / 25),
				(int) (height * 2.505479452054795 / 20), (int) (width * 22.92091836734694 / 25),
				(int) (height * 15.921154598825832 / 20));
		totalButton.setBounds((int) (width * 21.01019387755102 / 25), (int) (height * 18.434442270058707 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));
		cancelButton.setBounds((int) (width * 20.139795918367346 / 28), (int) (height * 18.434442270058707 / 20),
				(int) (width * 2.7683673469387754 / 25), (int) (height * 1.1350293542074363 / 20));

	}

	/**
	 * 监听方法
	 */

	public void addListener() {

		dateOKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ok();
			}
		});

		totalButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				totalui();
			}
		});
		cancelButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				cancelui();
			}
		});

		clause_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clauseui();
			}
		});

	}

	public void clauseui() {
		clauseInt = clause_choose.getSelectedIndex();
		if (clauseInt == 0) {
			clause = "运费";
		} else if (clauseInt == 1) {
			clause = "租金";
		} else if (clauseInt == 2) {
			clause = "工资";
		}
	}

	/**
	 * 选取相应的条目和时间从而计算金额（这里需要设定每个条目只能选取一次）
	 */
	public void ok() {
		String time = date_Input.getText();
		if(time.equals("")||clause.equals("")){
			JOptionPane.showMessageDialog(null, "信息输入不全!", "提示", JOptionPane.WARNING_MESSAGE);
		}
		else if(time.compareTo(GetDate.getdate())>0){
			JOptionPane.showMessageDialog(null, "输入的日期还未到!", "提示", JOptionPane.WARNING_MESSAGE);
			return ;
		}
		else{
		if (clause == "运费") {
			PaymentItemVO vo = new PaymentItemVO(time, clause, "总账", controller.getFare(time), user.userID);
			boolean isExsit = false;
			for (PaymentItemVO v : paymentItemVOs) {
				if (v.clause.equals(vo.clause)) {
					isExsit = true;
				}
			}
			if (isExsit == false) {
				paymentItemVOs.add(vo);
			} else {
				JOptionPane.showMessageDialog(null, "该月的运费已经被添加了！", "提示", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (clause == "租金") {
			PaymentItemVO vo = new PaymentItemVO(time, clause, "总账", controller.getRent(time), user.userID);
			boolean isExsit = false;
			for (PaymentItemVO v : paymentItemVOs) {
				if (v.clause.equals(vo.clause)) {
					isExsit = true;
				}
			}
			if (isExsit == false) {
				paymentItemVOs.add(vo);
			} else {
				JOptionPane.showMessageDialog(null, "该月的租金已经被添加了！", "提示", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (clause == "工资") {
			PaymentItemVO vo = new PaymentItemVO(time, clause, "总账", controller.getSalary(time), user.userID);
			boolean isExsit = false;
			for (PaymentItemVO v : paymentItemVOs) {
				if (v.clause.equals(vo.clause)) {
					isExsit = true;
				}
			}
			if (isExsit == false) {
				paymentItemVOs.add(vo);
			} else {
				JOptionPane.showMessageDialog(null, "该月的工资已经被添加了！", "提示", JOptionPane.WARNING_MESSAGE);
			}
		}
		paymentTable.setInfos(getInfos(paymentItemVOs));
	}
	}

	public void totalui() {
		String time = "";
		double rent = 0;
		double fare = 0;
		double salary = 0;
		for (PaymentItemVO v : paymentItemVOs) {
			time = v.date;
			if (v.clause.equals("运费")) {
				fare = v.money;
			}
			if (v.clause.equals("租金")) {
				rent = v.money;
			}
			if (v.clause.equals("工资")) {
				salary = v.money;
			}
		}
		if(fare!=0&&rent!=0&&salary!=0){
		PaymentReceiptVO vo = new PaymentReceiptVO(
				"FKD-" + time, user.userID,
				ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, rent, fare, salary, time, "总账", user.userName);
		int temp = controller.creatPaymentReceipt(vo);
		if (temp == 0) {
			LogDiaryBL bl = new LogDiaryBL();
			LogDiaryVO logvo = new LogDiaryVO(GetDate.getTime(), user, "创建了一张付款单");
			bl.addLogDiary(logvo, GetDate.getTime());
			controller.excute(vo);
			accountManagementPanel_main.refreshui();
			costIncomeReceiptPanel_new.refresh();
			logDiaryPanel.refreshui();
			receiptPanel_new.showPayment();
			JOptionPane.showMessageDialog(null, "创建付款单成功！", "提示", JOptionPane.DEFAULT_OPTION);
		} else {
			JOptionPane.showMessageDialog(null, "该月的付款单已经被创建了！", "提示", JOptionPane.WARNING_MESSAGE);
		}
		}
		else{
			JOptionPane.showMessageDialog(null, "该月份的三项付款条目统计不全！", "提示", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void cancelui() {
		financeFrame.toMainPanel();
	}

}
