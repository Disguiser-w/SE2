package businesslogic.repertorybl.controller;

import java.util.ArrayList;

import businesslogic.repertorybl.RepertoryBL;
import businesslogicservice.repertoryblservice.RepertoryBLService;
import vo.GoodsVO;
import vo.InventoryCheckVO;
import vo.InventoryVO;

public class RepertoryController implements RepertoryBLService{

	private RepertoryBL repertoryBL;
	
	public RepertoryController(String stockManID){
		repertoryBL = new RepertoryBL(stockManID);
	}
	
	public int inventoryInitialization(int maxRow, int maxShelf, int maxDigit,int warningRatio){
		return repertoryBL.inventoryInitialization(maxRow, maxShelf, maxDigit, warningRatio);
	}

	public int enterRepertory(String JJD_ID, int blockNum, int rowNum,int shelfNum, int digitNum){
		return repertoryBL.enterRepertory(JJD_ID, blockNum, rowNum, shelfNum, digitNum);
	}

	public int leaveRepertory(String JJD_ID){
		return repertoryBL.leaveRepertory(JJD_ID);
	}

	public String inventoryWarning(){
		return repertoryBL.inventoryWarning();
	}

	public InventoryCheckVO inventoryCheck(String beginDate, String endDate) {
		return repertoryBL.inventoryCheck(beginDate, endDate);
	}

	public ArrayList<InventoryVO> inventoryStockTaking(){
		return repertoryBL.inventoryStockTaking();
	}
	
	public InventoryVO findInventory(String goodsID){
		return repertoryBL.findInventory(goodsID);
	}
	
	public String searchVacantLocation(int blockNum){
		return repertoryBL.searchVacantLocation(blockNum); 
	}
	
	public int getMaxRow(){
		return repertoryBL.getMaxRow();
	}

	public int getMaxShelf(){
		return repertoryBL.getMaxShelf();
	}

	public int getMaxDigit(){
		return repertoryBL.getMaxDigit();
	}

	public int getMaxRatio(){
		return repertoryBL.getMaxRatio();
	}

	public String repertoryName(String repertoryID){
		return repertoryBL.repertoryName(repertoryID);
	}

	public String getLastEnterRepertoryTime(){
		return repertoryBL.getLastEnterRepertoryTime();
	}

	public String getLastLeaveRepertoryTime(){
		return repertoryBL.getLastLeaveRepertoryTime();
	}

	public ArrayList<GoodsVO> getEnterRepertoryGoods(){
		return repertoryBL.getEnterRepertoryGoods();
	}
	
	public ArrayList<GoodsVO> getLeaveRepertoryGoods(){
		return repertoryBL.getLeaveRepertoryGoods();
	}
	
}
