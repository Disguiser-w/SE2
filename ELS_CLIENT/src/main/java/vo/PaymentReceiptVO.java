package vo;

import java.util.ArrayList;

import type.ReceiptState;
import type.ReceiptType;

public class PaymentReceiptVO extends ReceiptVO{
//	//条目
//	String clause;
	//付款金额
	public double cost;
	//租金
	public double rent;
	//运费
	public double fare;
	//工资
	public double salary;
	//付款日期
	public String date;
	//付款账户
	public String account;
	//付款人
	public String name;
	//付款记录
	public ArrayList<PaymentItemVO> paymentItemVOs;
	
	public String ID;
	public String userID;
	public ReceiptType type;
	public ReceiptState state;
	
	public PaymentReceiptVO(String ID,String userID,ReceiptType type,ReceiptState state,double rent,double fare,double salary,String date,String account,String name ){
//		super(ID,userID,ReceiptType.PAYMENTRECEIPT,state);
		this.rent= rent;
		this.fare=fare;
		this.salary=salary;
		this.date=date;
		this.account=account;
		this.name=name;
		this.ID=ID;
		this.userID=userID;
		this.type=ReceiptType.PAYMENTRECEIPT;
		this.state=state;
		this.cost=rent+fare+salary;
	}

	


//	public String getClause(){
//		return clause;
//	}
	
	//
//	public double getCost(){
//		return rent+fare+salary;
//	}
//	
//	public double getRent(){
//		return rent;
//	}
//	
//	public double getFare(){
//		return fare;
//	}
//	
//	public double getSalary(){
//		return salary;
//	}
//	public String getDate(){
//		return date;
//	}
//	
//	public String getAccount(){
//		return account;
//	}
//	
//	public String getName(){
//		return name;
//	}
//	
//	public String getID(){
//		return ID;
//	}
//	
//	public String getUserID(){
//		return userID;
//	}
//	
//	public ReceiptType getType(){
//		return type;
//	}
//	public ReceiptState getState(){
//		return state;
//	}
//
//
//
//
//	public ArrayList<PaymentItemVO> getPaymentItems() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
