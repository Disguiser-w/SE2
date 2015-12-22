package vo;

/**
 * 这个VO作为每一次付款的记录
 *只记录账户和金额,唔，条目也记一下把
 *那每个月的付款最后都反映到这里
 * */
public class PaymentItemVO {
	public String date;
	public String account;
	public double money;
	public String clause;
	public String userID;
	
	public PaymentItemVO(String date,String clause,String account,double money,String userID){
		this.date=date;
		this.account=account;
		this.money=money;
		this.clause=clause;
		this.userID=userID;
	}
	
	
}
