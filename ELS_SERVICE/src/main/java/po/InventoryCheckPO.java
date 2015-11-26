package po;

public class InventoryCheckPO {
	private int enterTotal, leaveTotal;
	private double enterFeeTotal, leaveFeeTotal;
	private int[] stockNum;
	
	public InventoryCheckPO(){
		this.enterTotal = 0;
		this.leaveTotal = 0;
		this.enterFeeTotal = 0.0;
		this.leaveFeeTotal = 0.0;
		this.stockNum = new int[4];
	}
	
	public void enterTotalPlus(){
		this.enterTotal++;
	}
	
	public int getEnterTotal(){
		return this.enterTotal;
	}
	
	public void leaveTotalPlus(){
		this.leaveTotal++;
	}
	
	public int getLeaveTotal(){
		return this.leaveTotal;
	} 
	
	public void enterFeeTotalPlus(double fee){
		this.enterFeeTotal += fee;
	}
	
	public double getEnterFeeTotal(){
		return this.enterFeeTotal;
	}
	
	public void leaveFeeTotalPlus(double fee){
		this.leaveFeeTotal += fee;
	}
	
	public double getLeaveFeeTotal(){
		return this.leaveFeeTotal;
	}
	
	public void setStockNumArray(int[] stockNum){
		this.stockNum = stockNum;
	}
	
	public int[] getStockNumArray(){
		return this.stockNum;
	}
	
}
