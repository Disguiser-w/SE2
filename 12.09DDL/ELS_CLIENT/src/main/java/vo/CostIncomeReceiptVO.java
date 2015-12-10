package vo;

import java.util.ArrayList;

import type.ReceiptState;
import type.ReceiptType;

public class CostIncomeReceiptVO extends ReceiptVO{
	ArrayList<CollectionReceiptVO> cvo=new ArrayList<CollectionReceiptVO>();
	ArrayList<PaymentReceiptVO> pvo=new ArrayList<PaymentReceiptVO>();
	double cost;
	double income;
	double profit;
	String date;
	
	public CostIncomeReceiptVO(String ID,String userID,ReceiptType type,ReceiptState state,double cost,double income,double profit){
		super(ID,userID,ReceiptType.COSTINCOMERECEPTION,state);
		this.cost=cost;
		this.income=income;
		this.profit=profit;
	}
	



	public ArrayList<CollectionReceiptVO> getCollection(){
		return cvo;
	}
	
	public ArrayList<PaymentReceiptVO> getPayment(){
		return pvo;
	}
	
	public double getCost(){
		return cost;
	}
	
	public double getIncome(){
		return income;
	}
	
	public double getProfit(){
		return profit;
	}
	
	public String getID(){
		return ID;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public ReceiptState getState(){
		return state;
	}
	}
