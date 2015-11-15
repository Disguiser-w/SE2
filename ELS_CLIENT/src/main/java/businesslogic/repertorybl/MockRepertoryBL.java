package businesslogic.repertorybl;

import java.util.ArrayList;

import vo.GoodsVO;
import vo.InventoryVO;
import vo.RepertoryVO;

public class MockRepertoryBL extends RepertoryBL{
	RepertoryVO repertory;
	InventoryVO inventory;
	
	public MockRepertoryBL(){
		repertory = new RepertoryVO("-25-0","CK-00001",0,0,0,0);
		inventory = new InventoryVO();
	}
	
	public int inventoryInitialization(String repertoryID, int maxRow, int maxShelf, int maxDigit, int warningRatio){
		repertory.setMaxRow(maxRow);
		repertory.setMaxShelf(maxShelf);
		repertory.setMaxDigit(maxDigit);
		repertory.setWarningRatio(warningRatio);
		System.out.println("库存信息初始化成功，最大行数为 "+maxRow+"，最大架数为 "+maxShelf+"，最大位数为 "+maxDigit+"，警戒比例为"+warningRatio);
		return 0;
	}
	
	public int enterRepertory(String repertoryID, String JJD_ID, int blockNum, int rowNum, int shelfNum, int digitNum, String date){
		GoodsVO goods = new GoodsVO(JJD_ID, blockNum, rowNum, shelfNum,digitNum,date,"");
		inventory.addGoods(goods);
		System.out.println("入库成功，订单号为 "+JJD_ID+",所在区号为 "+blockNum+"，所在行号为 "+rowNum+"，所在架号为 "+shelfNum+"，所在位号为 "+digitNum+",入库时间为 "+date);
		
		return 0;
	}
	
	public int leaveRepertory(String repertoryID, String JJD_ID, int transType, String date){
		for(GoodsVO goods:inventory.Goods_List){
			if(goods.getOrder_ID().equals(JJD_ID)){
				goods.setLeaveDate(date);
				inventory.deleteGoods(goods);
				System.out.println("出库成功，订单号为 "+JJD_ID+",出库时间为 "+date);
				break;
			}
		}
		
		return 0;
	}
	
	public ArrayList<GoodsVO> inventoryCheck(String repertoryID, String beginDate, String endDate){
		return inventory.Goods_List;
	}
	
	public ArrayList<GoodsVO> inventoryStockTaking(String repertoryID){
		return inventory.Goods_List;
	}
	
	public int getMaxRow() {
		return this.repertory.getMaxRow();
	}

	public int getMaxShelf() {
		return this.repertory.getMaxShelf();
	}

	public int getMaxDigit() {
		return this.repertory.getMaxDigit();
	}

	public int getWarningRatio() {
		return this.repertory.getWarningRatio();
	}
	
	public InventoryVO getInventory(){
		return this.inventory;
	}
}
