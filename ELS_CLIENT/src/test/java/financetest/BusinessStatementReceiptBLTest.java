package financetest;

import java.util.ArrayList;

import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import junit.framework.TestCase;
import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;

public class BusinessStatementReceiptBLTest extends TestCase{
	BusinessStatementReceiptBLController businessStatementReceiptBL;
	
	public void setUp(){
		businessStatementReceiptBL = new BusinessStatementReceiptBLController();
	}
	
	public void test1_1(){
		BusinessStatementReceiptVO vo = businessStatementReceiptBL.showBSList("20101010", "20160101");
		ArrayList<CollectionReceiptVO> cvos = vo.cvos;
		ArrayList<PaymentReceiptVO> pvos = vo.pvos;
		assertEquals(4, cvos.size());
		assertEquals(8, pvos.size());
		}
}
