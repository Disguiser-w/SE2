package po;

import java.io.Serializable;

import po.GoodsPO;

public class InventoryPO implements Serializable{
	
	private static final long serialVersionUID = 141250150L;
	
	private GoodsPO good;
	private int blockNum, rowNum, shelfNum, digitNum;
	
	public InventoryPO(GoodsPO good, int blockNum, int rowNum, int shelfNum, int digitNum){
		this.good = good;
		this.blockNum = blockNum;
		this.rowNum = rowNum;
		this.shelfNum  = shelfNum;
		this.digitNum = digitNum;
	}
	
	public GoodsPO getGood(){
		return this.good;
	}
	
	public void setBlockNum(int blockNum){
		this.blockNum = blockNum;
	}
	
	public int getBlockNum(){
		return this.blockNum;
	}
	
	public void setRowNum(int rowNum){
		this.rowNum = rowNum;
	}
	
	public int getRowNum(){
		return this.rowNum;
	}
	
	public void setShelfNum(int shelfNum){
		this.shelfNum  = shelfNum;
	}
	
	public int getShelfNum(){
		return this.shelfNum;
	}
	
	public void setDigitNum(int digitNum){
		this.digitNum = digitNum;
	} 
	
	public int getDigitNum(){
		return this.digitNum;
	}
	
}
