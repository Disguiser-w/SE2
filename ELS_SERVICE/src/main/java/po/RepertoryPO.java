package po;

import java.io.Serializable;
import java.util.ArrayList;

public class RepertoryPO implements Serializable{

	private static final long serialVersionUID = 141250148L;

	private String repertoryID, ownerID;
	private int maxRow,  maxShelf, maxDigit, warningRadio;
	//private int planeBlockStockNum, trainBlockStockNum, truckBlockStockNum, defaultBlockStockNum;
	private int stockNum[];
	private ArrayList<InventoryPO> inventoryList;
	
	public RepertoryPO(String repertoryID, String ownerID){
		this.repertoryID = repertoryID;
		this.ownerID = ownerID;
		this.maxRow = 10;
		this.maxShelf = 10;
		this.maxDigit = 10;
		this.warningRadio = 80;
		stockNum = new int [4];
		inventoryList = new ArrayList<InventoryPO>(); 
		/*this.planeBlockStockNum = 0;
		this.trainBlockStockNum = 0;
		this.truckBlockStockNum = 0;
		this.defaultBlockStockNum = 0; */
	}
	
	public RepertoryPO(String repertoryID, String ownerID, int maxRow, int maxShelf, int maxDigit, int warningRatio, int stockNum[]) {
		// 仓库编号 对应仓库管理员编号 最多多少排 最多多少架 最多多少位 警戒比例
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
	
	/*public int getPlaneBlockStockNum(){
		return this.planeBlockStockNum;
	}
	
	public void planeBlockStockNumPlus(){
		this.planeBlockStockNum++;
	}
	
	public void planeBlockStockNumSub(){
		this.planeBlockStockNum--;
	}
	
	public int getTrainBlockStockNum(){
		return this.trainBlockStockNum;
	}
	
	public void trainBlockStockNumPlus(){
		this.trainBlockStockNum++;
	}
	
	public void trainBlockStockNumSub(){
		this.trainBlockStockNum--;
	}
	
	public int getTruckBlockStockNum(){
		return this.truckBlockStockNum;
	}
	
	public void truckBlockStockNumPlus(){
		this.truckBlockStockNum++;
	}
	
	public void truckBlockStockNumSub(){
		this.truckBlockStockNum--;
	}
	
	public int getDefaultBlockStockNum(){
		return this.defaultBlockStockNum;
	}
	
	public void defaultBlockStockNumPlus(){
		this.defaultBlockStockNum++;
	}
	
	public void defaultBlockStockNumSub(){
		this.defaultBlockStockNum--;
	}*/
	
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
}
