package vo;

public class MockCostIncomeReceiptVO {
	
	double cost;
	double income;
	double profit;
	
	public MockCostIncomeReceiptVO(double cost, double income, double profit){
		this.cost = cost;
		this.income = income;
		this.profit = profit;
	}
	
	public double getCost(){
		return this.cost;
	}
	
	public double getIncome(){
		return this.income;
	}
	
	public double getProfit(){
		return this.profit;
	}
	
}
