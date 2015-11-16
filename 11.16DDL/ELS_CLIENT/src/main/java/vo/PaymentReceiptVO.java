package vo;

import po.ReceiptPO.ReceiptState;
import type.ReceiptType;

public class PaymentReceiptVO extends ReceiptVO{
	//条目
	String clause;
	//付款金额
	double money;
	//付款日期
	String date;
	//付款账户
	String account;
	//付款人
	String name;
	
	public PaymentReceiptVO(String ID,String userID,ReceiptType type,ReceiptState state,String a,double b,String c,String d,String e ){
		super(ID,userID,ReceiptType.PAYMENTRECEIPT,state);
		clause=a;
		money=b;
		date=c;
		account=d;
		name=e;
	}

	


	public String getClause(){
		return clause;
	}
	
	public double getMoney(){
		return money;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getAccount(){
		return account;
	}
	
	public String getName(){
		return name;
	}
	

}
