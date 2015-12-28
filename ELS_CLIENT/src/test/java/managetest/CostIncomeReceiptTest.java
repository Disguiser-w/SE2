package managetest;
import junit.framework.TestCase;
import vo.MockCostIncomeReceiptVO;

public class CostIncomeReceiptTest extends TestCase {

	public void testCostIncomeReceipt(){
		MockCostIncomeReceiptVO mcir = new MockCostIncomeReceiptVO(10000,20000,10000);
		
		assertEquals(10000.0,mcir.getCost());
		assertEquals(20000.0,mcir.getIncome());
		assertEquals(10000.0,mcir.getProfit());
		
	}
}
