import junit.framework.TestCase;
import vo.MockBusinessStatementReceiptVO;
import vo.MockCollectionReceiptVO;
import vo.MockPaymentReceiptVO;

public class BusinessStatementReceiptTester extends TestCase {

	public void testBusinessStatementReceipt(){
		
		MockBusinessStatementReceiptVO mbsr = new MockBusinessStatementReceiptVO(
				new MockCollectionReceiptVO(20000,"20151001"),new MockPaymentReceiptVO(10000,"20151001"));
		
		assertEquals(20000.0, mbsr.getMockCollectionReceipt().getTotalMoney());
		assertEquals("20151001", mbsr.getMockCollectionReceipt().getDate());
		assertEquals(10000.0, mbsr.getMockPaymentReceipt().getMoney());
		assertEquals("20151001", mbsr.getMockPaymentReceipt().getDate());
		
	}
	
}
