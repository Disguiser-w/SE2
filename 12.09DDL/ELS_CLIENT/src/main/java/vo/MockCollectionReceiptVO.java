package vo;

public class MockCollectionReceiptVO {
	double totalMoney;
	String date;
	
	public MockCollectionReceiptVO(double totalMoney, String date){
		this.totalMoney = totalMoney;
		this.date = date;
	}
	
	public double getTotalMoney(){
		return this.totalMoney;
	}
	
	public String getDate(){
		return this.date;
	}
}
