package presentation.financeui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import businesslogic.financebl.controller.CostIncomeReceiptBLController;
import businesslogic.receiptbl.getDate;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;

public class CostIncomeReceiptPanel_new extends OperationPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;


	private JLabel function;
	private JLabel costIncomeReceipt_ID;
	private JLabel startDate;
	private JLabel endDate;
	private JLabel totalIncome;
	private JLabel totalCost;
	private JLabel totalProfit;
	
	private MyLabel refreshLabel;

	private MyTextField costIncomeReceipt_ID_Input;
	private MyTextField startDate_Input;
	private MyTextField endDate_Input;
	private MyTextField totalIncome_Input;
	private MyTextField totalCost_Input;
	private MyTextField totalProfit_Input;

	private String beginTime;
	private String endTime;
	private String ID;
	public CostIncomeReceiptBLController controller;
	FinanceFrame financeFrame;

	public CostIncomeReceiptPanel_new(CostIncomeReceiptBLController controller, FinanceFrame parent) {
		this.controller = controller;
		this.financeFrame = parent;

		beginTime = "2010-01-01";
		endTime = getDate.getdate().substring(0, 4) + "-" + getDate.getdate().substring(4, 6) + "-"
				+ getDate.getdate().substring(6);
		ID = controller.getCostIncomeListID();
		function = new JLabel("成本收益表");
		costIncomeReceipt_ID = new JLabel("成本收益表编号");
		startDate = new JLabel("开始时间");
		endDate = new JLabel("结束时间");
		totalCost = new JLabel("总收入");
		totalIncome = new JLabel("总支出");
		totalProfit = new JLabel("总利润");
		
		refreshLabel = new MyLabel("刷新");

		costIncomeReceipt_ID_Input = new MyTextField(ID);
		costIncomeReceipt_ID_Input.setEditable(false);
		startDate_Input = new MyTextField(beginTime);
		startDate_Input.setEditable(false);
		endDate_Input = new MyTextField(endTime);
		endDate_Input.setEditable(false);
		double income = controller.getIncome();
		totalCost_Input = new MyTextField(income + "");
		totalCost_Input.setEditable(false);
		double cost = controller.getCost();
		totalIncome_Input = new MyTextField(cost + "");
		totalIncome_Input.setEditable(false);
		double profit = controller.getProfit(income, cost);
		totalProfit_Input = new MyTextField(profit + "");
		totalProfit_Input.setEditable(false);
		setCmpLocation();


		setLayout(null);

		add(function);
		add(costIncomeReceipt_ID);
		add(startDate);
		add(endDate);
		add(totalCost);
		add(totalIncome);
		add(totalProfit);
		add(costIncomeReceipt_ID_Input);
		add(startDate_Input);
		add(endDate_Input);
		add(totalCost_Input);
		add(totalIncome_Input);
		add(totalProfit_Input);
		add(refreshLabel);

		
             refreshLabel.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				refresh();
			}
		});
	}

	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24, PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		costIncomeReceipt_ID.setBounds(PANEL_WIDTH *2/ 9, PANEL_HEIGHT *5/ 32, PANEL_WIDTH * 7 / 20, PANEL_HEIGHT / 18);
		startDate.setBounds(PANEL_WIDTH *2/ 9, PANEL_HEIGHT * 8/ 32, PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		endDate.setBounds(PANEL_WIDTH *2/ 9, PANEL_HEIGHT * 11 / 32, PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		totalIncome.setBounds(PANEL_WIDTH *2/ 9, PANEL_HEIGHT * 14 / 32, PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		totalCost.setBounds(PANEL_WIDTH *2/ 9, PANEL_HEIGHT * 17 / 32, PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		totalProfit.setBounds(PANEL_WIDTH *2/ 9, PANEL_HEIGHT * 20 / 32, PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		costIncomeReceipt_ID_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT*5/ 32, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		startDate_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 8/ 32, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		endDate_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT  * 11 / 32, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		totalIncome_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 14 / 32, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		totalCost_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT  * 17 / 32, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		totalProfit_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 20 / 32, PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		refreshLabel.setBounds(PANEL_WIDTH *20/ 36, PANEL_HEIGHT / 24, PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
	}

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}
	

	public void refresh(){
		double income = controller.getIncome();
		double cost = controller.getCost();
		double profit = income-cost;
		totalCost_Input = new MyTextField(income + "");
		totalCost_Input.setEditable(false);
		totalIncome_Input = new MyTextField(cost + "");
		totalIncome_Input.setEditable(false);
		totalProfit_Input = new MyTextField(profit + "");
		totalProfit_Input.setEditable(false);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		refresh();
	}
}
