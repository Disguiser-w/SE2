package vo;

import java.util.ArrayList;

import type.ReceiptState;
import type.ReceiptType;

public class CostIncomeReceiptVO extends ReceiptVO{
	public ArrayList<CollectionReceiptVO> cvo=new ArrayList<CollectionReceiptVO>();
	public ArrayList<PaymentReceiptVO> pvo=new ArrayList<PaymentReceiptVO>();
	public double cost;
	public double income;
	public double profit;
	public String date;
	public ReceiptType type;
	public ReceiptState state;
	public String ID;
	public String userID;
	
	public CostIncomeReceiptVO(String ID,String userID,ReceiptType type,ReceiptState state,double cost,double income,double profit){
//		super(ID,userID,ReceiptType.COSTINCOMERECEPTION,state);
		this.cost=cost;
		this.income=income;
		this.profit=income-cost;
		this.ID=ID;
		this.userID=userID;
		this.state=state;
		this.type=ReceiptType.COSTINCOMERECEPTION;
		
	}
	



//	public ArrayList<CollectionReceiptVO> getCollection(){
//		return cvo;
//	}
//	
//	public ArrayList<PaymentReceiptVO> getPayment(){
//		return pvo;
//	}
//	
//	public double getCost(){
//		return cost;
//	}
//	
//	public double getIncome(){
//		return income;
//	}
//	
//	public double getProfit(){
//		return profit;
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
//	public ReceiptState getState(){
//		return state;
//	}
	}
