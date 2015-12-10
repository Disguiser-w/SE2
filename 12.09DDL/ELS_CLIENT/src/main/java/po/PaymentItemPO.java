package po;

public class PaymentItemPO {
	String account;
	String clause;
	double money;
	
	public PaymentItemPO(String clause,String account,double money){
		this.account=account;
		this.clause=clause;
		this.money=money;
	}
	
	public double getMoney(){
		return money;
	}
	
	public String getClause(){
		return clause;
	}
	
	public String getAccount(){
		return account;
	}

}

