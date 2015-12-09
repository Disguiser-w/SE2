//package financetest;
//
//import businesslogic.financebl.CollectionReceiptBL;
//import junit.framework.TestCase;
//import vo.MockGatheringReceiptVO;
//
//public class CollectionReceiptBLTest extends TestCase{
//	public void testTotalMoney(){
//		MockGatheringReceiptVO mgrpo1=new MockGatheringReceiptVO("025-000", "2015/11/15", 300, "SDK-20151115-00000");
//		MockGatheringReceiptVO mgrpo2=new MockGatheringReceiptVO("025-001", "2015/11/15", 400, "SDK-20151115-00001");
//		MockGatheringReceiptVO mgrpo3=new MockGatheringReceiptVO("025-002", "2015/11/15", 300, "SDK-20151115-00002");
//		
//		CollectionReceiptBL crbl=new CollectionReceiptBL();
//		String time=crbl.getTime(mgrpo1);
//		double totalMoney=0;
//		crbl.addGatheringItem(mgrpo1);
//		crbl.addGatheringItem(mgrpo2);
//		crbl.addGatheringItem(mgrpo3);
//		totalMoney=crbl.getTotalMoney();
//		
//		assertEquals(1000.0,totalMoney);
//		assertEquals("2015/11/15",time);
//		
//		
//	}
//
//}
