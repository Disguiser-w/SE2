package vo;

public class InventoryCheckVO {

	public int enterTotal, leaveTotal;
	public double enterFeeTotal, leaveFeeTotal;
	public int[] stockNum;
	
	public InventoryCheckVO(){
		this.enterTotal = 0;
		this.leaveTotal = 0;
		this.enterFeeTotal = 0.0;
		this.leaveFeeTotal = 0.0;
		this.stockNum = new int[4];
	}
	
	public InventoryCheckVO(int enterTotal, int leaveTotal, double enterFeeTotal, double leaveFeeTotal, int[] stockNum){
		this.enterTotal = enterTotal;
		this.leaveTotal = leaveTotal;
		this.enterFeeTotal = enterFeeTotal;
		this.leaveFeeTotal = leaveFeeTotal;
		this.stockNum = stockNum;
	}
	
	public void enterTotalPlus(){
		this.enterTotal++;
	}
	
	public void leaveTotalPlus(){
		this.leaveTotal++;
	}
	
	public void enterFeeTotalPlus(double fee){
		this.enterFeeTotal += fee;
	}
	
	public void leaveFeeTotalPlus(double fee){
		this.leaveFeeTotal += fee;
	}
	
	public void setStockNumArray(int[] stockNum){
		this.stockNum = stockNum;
	}
	
	public int[] getStockNumArray(){
		return this.stockNum;
	}
	
}
