package vo;

/**
 * 这个VO作为每一次付款的记录
 * */
public class PaymentItemVO {
	String account;
	String clause;
	double money;
	
	public PaymentItemVO(String clause,String account,double money){
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
