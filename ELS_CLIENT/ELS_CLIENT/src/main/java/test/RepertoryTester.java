package test;

import businesslogic.repertorybl.MockRepertoryBL;
import vo.GoodsVO;
import vo.InventoryVO;

import junit.framework.TestCase;
import java.util.ArrayList;

public class RepertoryTester extends TestCase {
	
	public void testRepertory(){
		
		MockRepertoryBL repertoryBL = new MockRepertoryBL();
		InventoryVO inventory = repertoryBL.getInventory();
		ArrayList<GoodsVO> goodsList = inventory.Goods_List;
		
		repertoryBL.inventoryInitialization("025-0-CK", 100, 10, 10, 90);
		assertEquals(100,repertoryBL.getMaxRow());
		assertEquals(10,repertoryBL.getMaxShelf());
		assertEquals(10,repertoryBL.getMaxDigit());
		assertEquals(90,repertoryBL.getWarningRatio());
		
		repertoryBL.enterRepertory("025-0-CK", "20151001-00001", 0, 01, 01, 01,"20151003");
		repertoryBL.enterRepertory("025-0-CK", "20151001-00010", 1, 02, 02, 02,"20151004");
		repertoryBL.enterRepertory("025-0-CK", "20151002-00003", 1, 03, 03, 03,"20151004");
		
		repertoryBL.leaveRepertory("025-0-CK", "20151001-00001", 1,"20151005");
		
		goodsList = repertoryBL.inventoryCheck("025-0-CK", "20151001", "20151003");
		for(GoodsVO good : goodsList){
			System.out.println(good.getOrder_ID()+" "+good.getBlcokNum()+" "+good.getRowNum()+" "+good.getShelfNum()+" "
					+good.getDigitNum()+" "+good.getEnterDate()+" "+good.getLeaveDate());
		}
		
		goodsList = repertoryBL.inventoryStockTaking("025-0-CK");
		for(GoodsVO good : goodsList){
			System.out.println(good.getOrder_ID()+" "+good.getBlcokNum()+" "+good.getRowNum()+" "+good.getShelfNum()+" "
					+good.getDigitNum()+" "+good.getEnterDate()+" "+good.getLeaveDate());
		}
		
		assertEquals(0,0);
	}
	
}
