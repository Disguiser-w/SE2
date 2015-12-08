package presentation.financeui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import businesslogic.financebl.controller.AccountBLController;
import presentation.commonui.UserFrame;

public class FinanceFrame extends UserFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FinanceFrame(){
		super();
		
		setMessage("本宝宝", "CW-00001");
//		addFuncLabel(new AccountManagementPanel_main());
//		addFuncLabel(new CollectionReceiptPanel());
//		addFuncLabel(new PaymentReceiptPanel());
//		addFuncLabel(new CostIncomeReceiptPanel_new());
//		addFuncLabel(new BusinessStateReceiptPanel());
//		addFuncLabel(new InitialStockPanel_main());
//		
//		showFrame();
		
	}
	
	/**
	 * 界面跳转
	 * */
	public void setPanel(JPanel panel){
		panel.setSize(800, 550);
		panel.setVisible(true);
		panel.setLayout(new BorderLayout());
		
		
	}
	
	
	
	
//	public static void main(String[] args){
//		new FinanceFrame();
//	}
	
	

}
