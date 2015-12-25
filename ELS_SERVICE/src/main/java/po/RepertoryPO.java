package po;

import java.io.Serializable;
import java.util.ArrayList;

public class RepertoryPO implements Serializable{

	private static final long serialVersionUID = 141250148L;
	
	private String repertoryID, ownerID;
	private int maxRow,  maxShelf, maxDigit, warningRadio;
	private int stockNum[];
	private ArrayList<InventoryPO> inventoryList; //记录仓库中库存的列表（只包括目前在仓库里的库存信息）
	private ArrayList<String> inventoryHistoryList;//记录仓库中历史库存的列表，包括曾经入库出库的货物的订单号
	private String lastCreateEnterReceiptTime;
	private String lastCreateLeaveReceiptTime;
	
	public RepertoryPO(String repertoryID, String ownerID){
		this.repertoryID = repertoryID;
		this.ownerID = ownerID;
		this.maxRow = 10;
		this.maxShelf = 10;
		this.maxDigit = 10;
		this.warningRadio = 80;
		stockNum = new int [4];
		inventoryList = new ArrayList<InventoryPO>(); 
		inventoryHistoryList = new ArrayList<String>();
		lastCreateEnterReceiptTime = "";
		lastCreateLeaveReceiptTime = "";
	}
	
	public RepertoryPO(String repertoryID, String ownerID, int maxRow, int maxShelf, int maxDigit, int warningRatio, int stockNum[]) {
		// 025-0-CK CK-00001
		this.repertoryID = repertoryID;
		this.ownerID = ownerID;
		this.maxRow = maxRow;
		this.maxShelf = maxShelf;
		this.maxDigit = maxDigit;
		this.warningRadio = warningRatio;
		this.stockNum = stockNum;
	}
	
	
	public String getRepertoryID(){
		return this.repertoryID;
	}
	
	public void setRepertoryID(String repertoryID){
		this.repertoryID = repertoryID;
	}
	
	public String getOwnerID(){
		return this.ownerID;
	}
	
	public void setOwnerID(String ownerID){
		this.ownerID = ownerID;
	}
	
	public int getMaxRow(){
		return this.maxRow;
	}
	
	public void setMaxRow(int maxRow){
		this.maxRow = maxRow;
	}
	
	public int getMaxShelf(){
		return this.maxShelf;
	}
	
	public void setMaxShelf(int maxShelf){
		this.maxShelf = maxShelf;
	}
	
	public int getMaxDigit(){
		return this.maxDigit;
	}
	
	public void setMaxDigit(int maxDigit){
		this.maxDigit = maxDigit;
	}
	
	public int getWarningRatio(){
		return this.warningRadio;
	}
	
	public void setWarningRatio(int warningRatio){
		this.warningRadio = warningRatio;
	}
	
	public void setStockNumArray(int[] stockNum){
		this.stockNum = stockNum;
	}
	
	public int[] getStockNumArray(){
		return this.stockNum;
	}
	
	public int getStockNum(int block){
		return this.stockNum[block];
	}
	
	public void stockNumPlus(int block){
		this.stockNum[block]++;
	}
	
	public void stockNumSub(int block){
		this.stockNum[block]--;
	}
	
	public ArrayList<InventoryPO> getInventoryList(){
		return this.inventoryList;
	}
	
	public ArrayList<String> getInventoryHistoryList(){
		return this.inventoryHistoryList;
	}
	
	public String getLastCreateEnterReceiptTime(){
		return this.lastCreateEnterReceiptTime;
	}

	public void setLastCreateEnterReceiptTime(String newTime){
		this.lastCreateEnterReceiptTime = newTime;
	}
	
	public String getLastCreateLeaveReceiptTime(){
		return this.lastCreateLeaveReceiptTime;
	}
	
	public void setLastCreateLeaveReceiptTime(String newTime){
		this.lastCreateLeaveReceiptTime = newTime;
	}
	
}
