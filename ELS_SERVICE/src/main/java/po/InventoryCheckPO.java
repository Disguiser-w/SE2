package po;

import java.io.Serializable;

public class InventoryCheckPO implements Serializable{
	
	private static final long serialVersionUID = 141250184L;

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
	
	public InventoryCheckPO(int enterTotal, int leaveTotal, double enterFeeTotal, double leaveFeeTotal, int[] stockNum){
		this.enterTotal = enterTotal;
		this.leaveTotal = leaveTotal;
		this.enterFeeTotal = enterFeeTotal;
		this.leaveFeeTotal = leaveFeeTotal;
		this.stockNum = stockNum;
	}
	
	public void enterTotalPlus(){
		this.enterTotal += 1; ;
	}
	
	public int getEnterTotal(){
		return this.enterTotal;
	}
	
	public void leaveTotalPlus(){
		this.leaveTotal += 1;
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
