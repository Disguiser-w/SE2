package presentation.managerui;

import javax.swing.JLabel;
import javax.swing.JTextField;

import presentation.commonui.OperationPanel;
import businesslogic.financebl.controller.CostIncomeReceiptBLController;
import businesslogic.receiptbl.getDate;

public class CheckIncomePanel extends OperationPanel {
	
	private static final long serialVersionUID = 1L;
	
	private CostIncomeReceiptBLController costIncomeReceiptController;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private JLabel function;
	
	private JLabel costIncomeReceipt_ID;
	private JLabel startDate;
	private JLabel endDate;
	private JLabel totalIncome;
	private JLabel totalCost;
	private JLabel totalProfit;
	
	private JTextField costIncomeReceipt_ID_Input;
	private JTextField startDate_Input;
	private JTextField endDate_Input;
	private JTextField totalIncome_Input;
	private JTextField totalCost_Input;
	private JTextField totalProfit_Input;
	
	private String beginTime; 
	private String endTime;
	private String ID;

	public CheckIncomePanel(){
		
		this.costIncomeReceiptController = new CostIncomeReceiptBLController();
		
		function = new JLabel("查看成本收益表");
		costIncomeReceipt_ID = new JLabel("成本收益表编号");
		beginTime = "2010-01-01";
		endTime = getDate.getdate().substring(0, 4)+"-"+getDate.getdate().substring(4,6)+"-"+getDate.getdate().substring(6);
		ID = costIncomeReceiptController.getCostIncomeListID();
		
		startDate = new JLabel("开始时间");
		endDate = new JLabel("结束时间");
		totalCost = new JLabel("总收入");
		totalIncome = new JLabel("总支出");
		totalProfit = new JLabel("总利润");

		costIncomeReceipt_ID_Input = new JTextField(ID);
		costIncomeReceipt_ID_Input.setEditable(false);
		startDate_Input = new JTextField(beginTime);
		startDate_Input.setEditable(false);
		endDate_Input = new JTextField(endTime);
		endDate_Input.setEditable(false);
		double income = costIncomeReceiptController.getIncome();
		totalCost_Input = new JTextField(income+"");
		totalCost_Input.setEditable(false);
		double cost = costIncomeReceiptController.getCost();
		totalIncome_Input = new JTextField(cost+"");
		totalIncome_Input.setEditable(false);
		double profit = costIncomeReceiptController.getProfit(income, cost);
		totalProfit_Input = new JTextField(profit+"");
		totalProfit_Input.setEditable(false);
		
		
		//把组件加到Panel上
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
		
		//位置设置
		setCmpLocation();
	}

	//设置位置
	public void setCmpLocation() {
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		
		costIncomeReceipt_ID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT / 4,
				PANEL_WIDTH * 7 / 20, PANEL_HEIGHT / 18);
		startDate.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		endDate.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 11 / 24,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		totalIncome.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 9 / 16,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		totalCost.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 2 / 3,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		totalProfit.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 37 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		
		costIncomeReceipt_ID_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT / 4,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		startDate_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 17 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		endDate_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 11 / 24,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		totalIncome_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 9 / 16,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		totalCost_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 2 / 3,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		totalProfit_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 37 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		PANEL_WIDTH = width;
		PANEL_HEIGHT = height;
		setCmpLocation();
		repaint();
	}

	
}
