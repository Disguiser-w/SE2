package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.ReceiptState;
import type.ReceiptType;

public class CostIncomeReceiptPO extends ReceiptPO implements Serializable{

	private static final long serialVersionUID = 7845997179584179464L;

	
	ArrayList<CollectionReceiptPO> cpo=new ArrayList<CollectionReceiptPO>();
	ArrayList<PaymentReceiptPO> ppo=new ArrayList<PaymentReceiptPO>();
	double cost;
	double income;
	double profit;
	String date;
	
	public CostIncomeReceiptPO(String ID,String userID,ReceiptType type,ReceiptState state,double cost,double income,double profit){
		super(ID,userID,ReceiptType.COSTINCOMERECEPTION,state);
		this.cost=cost;
		this.income=income;
		this.profit=profit;
	}
	
	public CostIncomeReceiptPO(){
		
	}
	
	public ArrayList<CollectionReceiptPO> getCollection(){
		return cpo;
	}
	
	public ArrayList<PaymentReceiptPO> getPayment(){
		return ppo;
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

}
