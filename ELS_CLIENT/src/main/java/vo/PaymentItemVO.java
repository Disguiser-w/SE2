package vo;

/**
 * 这个VO作为每一次付款的记录
 *只记录账户和金额,唔，条目也记一下把
 *那每个月的付款最后都反映到这里
 * */
public class PaymentItemVO {
	String account;
	double money;
	String clause;
	
	public PaymentItemVO(String clause,String account,double money){
		this.account=account;
		this.money=money;
		this.clause=clause;
	}
	
	public double getMoney(){
		return money;
	}

	
	public String getAccount(){
		return account;
	}

	public String getClause(){
		return clause;
	}
}
