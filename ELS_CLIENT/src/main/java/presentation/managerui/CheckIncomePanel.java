package presentation.managerui;

import businesslogic.financebl.controller.CostIncomeReceiptBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;


public class CheckIncomePanel extends OperationPanel {
	
	private static final long serialVersionUID = 1L;
	
	private CostIncomeReceiptBLController costIncomeReceiptControl;
	
	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;
	
	private MyTextLabel costIncomeReceipt_ID;
	private MyTextLabel startDate;
	private MyTextLabel endDate;
	private MyTextLabel totalIncome;
	private MyTextLabel totalCost;
	private MyTextLabel totalProfit;
	
	private MyTextField costIncomeReceipt_ID_Input;
	private MyTextField startDate_Input;
	private MyTextField endDate_Input;
	private MyTextField totalIncome_Input;
	private MyTextField totalCost_Input;
	private MyTextField totalProfit_Input;
	
	private String beginTime; 
	private String endTime;
	private String ID;

	public CheckIncomePanel(CostIncomeReceiptBLController costIncomeControl){
		
		this.costIncomeReceiptControl = costIncomeControl;
		
		costIncomeReceipt_ID = new MyTextLabel("成本收益表编号");
		beginTime = "2010-01-01";
		endTime = GetDate.getdate();
		ID = costIncomeReceiptControl.getCostIncomeListID();
		
		startDate = new MyTextLabel("开始时间");
		endDate = new MyTextLabel("结束时间");
		totalCost = new MyTextLabel("总收入");
		totalIncome = new MyTextLabel("总支出");
		totalProfit = new MyTextLabel("总利润");

		costIncomeReceipt_ID_Input = new MyTextField();
		costIncomeReceipt_ID_Input.setText(ID);
		costIncomeReceipt_ID_Input.setEditable(false);
		startDate_Input = new MyTextField();
		startDate_Input.setText(beginTime);
		startDate_Input.setEditable(false);
		endDate_Input = new MyTextField();
		endDate_Input.setText(endTime);
		endDate_Input.setEditable(false);
		double income = costIncomeReceiptControl.getIncome();
		totalCost_Input = new MyTextField();
		totalCost_Input.setText(income+"");
		totalCost_Input.setEditable(false);
		double cost = costIncomeReceiptControl.getCost();
		totalIncome_Input = new MyTextField();
		totalIncome_Input.setText(cost+"");
		totalIncome_Input.setEditable(false);
		double profit = costIncomeReceiptControl.getProfit(income, cost);
		totalProfit_Input = new MyTextField();
		totalProfit_Input.setText(profit+"");
		totalProfit_Input.setEditable(false);
		
		
		//把组件加到Panel上
		setLayout(null);

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
		costIncomeReceipt_ID.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 10 / 48,
				PANEL_WIDTH * 7 / 20, PANEL_HEIGHT / 18);
		startDate.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 15 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		endDate.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 6, PANEL_HEIGHT / 16);
		totalIncome.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		totalCost.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		totalProfit.setBounds(PANEL_WIDTH / 9, PANEL_HEIGHT * 35 / 48,
				PANEL_WIDTH / 8, PANEL_HEIGHT / 16);
		
		costIncomeReceipt_ID_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 10 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		startDate_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 15 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		endDate_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 20 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		totalIncome_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 25 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		totalCost_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 30 / 48,
				PANEL_WIDTH / 3, PANEL_HEIGHT / 16);
		totalProfit_Input.setBounds(PANEL_WIDTH / 2, PANEL_HEIGHT * 35 / 48,
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
