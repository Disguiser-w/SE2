package vo;

public class MockPaymentReceiptVO {
		double money;
		String date;
		
		public MockPaymentReceiptVO(double money, String date){
			this.money = money;
			this.date = date;
		}
		
		public double getMoney(){
			return money;
		}
		
		public String getDate(){
			return date;
		}
		
}
