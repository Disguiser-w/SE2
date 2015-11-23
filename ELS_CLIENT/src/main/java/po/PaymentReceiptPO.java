package po;

import java.io.Serializable;

import type.ReceiptType;

public class PaymentReceiptPO extends ReceiptPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public PaymentReceiptPO(String ID,String userID,ReceiptType type,ReceiptState state,String clause,double money,String date,String account,String name ){
		super(ID,userID,ReceiptType.PAYMENTRECEIPT,state);
		this.clause=clause;
		this.money=money;
		this.date=date;
		this.account=account;
		this.name=name;
	}
	public PaymentReceiptPO(){
		
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
	
	public void setClause(String str){
		clause=str;
	}
	
	public void setDate(String d){
		date=d;
	}
	
	public void account(String a){
		account=a;
	}
	
	public void setName(String n){
		name=n;
	}
	
	public ReceiptState getState(){
		return state;
	}
	
	public ReceiptType getType(){
		return type;
	}
	
	public String getID(){
		return ID;
	}
	
	public String getUserID(){
		return userID;
	}
	

}
