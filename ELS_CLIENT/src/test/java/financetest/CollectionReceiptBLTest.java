package financetest;

import java.util.ArrayList;

import type.ReceiptState;
import type.ReceiptType;
import vo.CollectionReceiptVO;
import vo.GatheringReceiptVO;
import businesslogic.businessbl.Gathering;
import businesslogic.financebl.CollectionReceiptBL;
import junit.framework.TestCase;

public class CollectionReceiptBLTest extends TestCase{
	CollectionReceiptBL collectionReceiptBL;
	Gathering gathering;
	public void testTotalMoney(){
		
		collectionReceiptBL = new CollectionReceiptBL();
//		CollectionReceiptVO vo1 = new CollectionReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT
//				,ReceiptState.SUBMIT, 3000, "20151220", "鼓楼");
//		CollectionReceiptVO vo2 = new CollectionReceiptVO("HJSKD-20151219", "CW-00001", ReceiptType.COLLECTIONRECEIPT
//				,ReceiptState.SUBMIT, 4000, "20151219", "鼓楼");
//		collectionReceiptBL.add(vo1);
//		collectionReceiptBL.add(vo2);
		
		ArrayList<GatheringReceiptVO> gathering = collectionReceiptBL.getGathering("20151220");
		assertEquals(1, gathering.size());
		ArrayList<CollectionReceiptVO> cvo = collectionReceiptBL.getAllCollection();
		assertEquals(4, cvo.size());
		
//		CollectionReceiptVO result = collectionReceiptBL.getCollection("HJSKD-20151219");
//		assertEquals(3000.0, result.totalMoney);
	}

}
