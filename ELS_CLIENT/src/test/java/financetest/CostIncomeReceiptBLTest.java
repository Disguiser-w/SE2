package financetest;

import businesslogic.financebl.CostIncomeReceiptBL;
import junit.framework.TestCase;
import vo.CostIncomeReceiptVO;

public class CostIncomeReceiptBLTest extends TestCase{
	CostIncomeReceiptVO vo;
	CostIncomeReceiptBL costIncomeReceiptBL;
	
	public void setUp(){
		costIncomeReceiptBL = new CostIncomeReceiptBL();
	}
	
	public void test1_1(){
		double cost = costIncomeReceiptBL.getCost();
		double income = costIncomeReceiptBL.getIncome();
		double profit = costIncomeReceiptBL.getProfit(income, cost);
		assertEquals(32000.0, cost);
		assertEquals(1750.0, income);
		assertEquals(-30250.0, profit);
	}
}
