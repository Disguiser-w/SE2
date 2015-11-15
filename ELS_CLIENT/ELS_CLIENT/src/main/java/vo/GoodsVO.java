package vo;

public class GoodsVO {
	private String Order_ID;
	private int blockNum, rowNum, shelfNum, digitNum;
	private String enterDate, leaveDate;
	
	public GoodsVO(String Order_ID, int blockNum, int rowNum, int shelfNum, int digitNum, String enterDate, String leaveDate){
		this.Order_ID = Order_ID;
		this.blockNum = blockNum;
		this.rowNum = rowNum;
		this.shelfNum  = shelfNum;
		this.digitNum = digitNum;
		this.enterDate = enterDate;
		this.leaveDate = leaveDate;
	}
	
	public String getOrder_ID(){
		return this.Order_ID;
	}
	
	public void setBlockNum(int blockNum){
		this.blockNum = blockNum;
	}
	
	public int getBlcokNum(){
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
	
	public void setEnterDate(String enterDate){
		this.enterDate = enterDate;
	}
	
	public String getEnterDate(){
		return this.enterDate;
	}
	
	public void setLeaveDate(String leaveDate){
		this.leaveDate  = leaveDate;
	}
	
	public String getLeaveDate(){
		return this.leaveDate;
	}
	
}
