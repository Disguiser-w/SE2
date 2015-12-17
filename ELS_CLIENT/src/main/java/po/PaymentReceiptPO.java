package po;

import java.io.Serializable;

import type.ReceiptState;
import type.ReceiptType;

public class PaymentReceiptPO extends ReceiptPO implements Serializable{

	private static final long serialVersionUID = 382688175146232382L;
//	//条目
//	String clause;
	//付款金额
	double cost;
	double salary;
	double fare;
	double rent;
	//付款日期
	String date;
	//付款账户
	String account;
	//付款人
	String name;
	
	public PaymentReceiptPO(String ID,String userID,ReceiptType type,ReceiptState state,double rent,double fare,double salary,String date,String account,String name ){
		super(ID,userID,ReceiptType.PAYMENTRECEIPT,state);
		this.rent= rent;
		this.fare=fare;
		this.salary=salary;
		this.date=date;
		this.account=account;
		this.name=name;
	}
	public PaymentReceiptPO(){
		
	}
	
	public double getCost(){
		return rent+fare+salary;
	}
	
	public double getRent(){
		return rent;
	}
	
	public double getFare(){
		return fare;
	}
	
	public double getSalary(){
		return salary;
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
	
//	public void setClause(String str){
//		clause=str;
//	}
	
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

/*package po;

import java.io.Serializable;

import type.ReceiptType;

public class PaymentReceiptPO extends ReceiptPO implements Serializable{
	
	private static final long serialVersionUID = 1L;
//	//条目
//	String clause;
	//付款金额
	double cost;
	double salary;
	double fare;
	double rent;
	//付款日期
	String date;
	//付款账户
	String account;
	//付款人
	String name;
	
	public PaymentReceiptPO(String ID,String userID,ReceiptType type,ReceiptState state,double rent,double fare,double salary,String date,String account,String name ){
		super(ID,userID,ReceiptType.PAYMENTRECEIPT,state);
		this.rent= rent;
		this.fare=fare;
		this.salary=salary;
		this.date=date;
		this.account=account;
		this.name=name;
	}
	public PaymentReceiptPO(){
		
	}
	
	public double getCost(){
		return rent+fare+salary;
	}
	
	public double getRent(){
		return rent;
	}
	
	public double getFare(){
		return fare;
	}
	
	public double getSalary(){
		return salary;
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
	
//	public void setClause(String str){
//		clause=str;
//	}
	
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
*/