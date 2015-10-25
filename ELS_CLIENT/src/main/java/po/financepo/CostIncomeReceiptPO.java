package po.financepo;

import java.io.Serializable;
import java.util.ArrayList;

import type.ReceiptType;

public class CostIncomeReceiptPO extends ReceiptPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<CollectionReceiptPO> cpo=new ArrayList<CollectionReceiptPO>();
	ArrayList<PaymentReceiptPO> ppo=new ArrayList<PaymentReceiptPO>();
	double cost;
	double income;
	double profit;
	String date;
	
	public CostIncomeReceiptPO(String ID,String userID,ReceiptType type,ReceiptState state,ArrayList<CollectionReceiptPO> po1,
			ArrayList<PaymentReceiptPO> po2,double a,double b,double c){
		super(ID,userID,ReceiptType.COSTINCOMERECEPTION,state);
		cost=a;
		income=b;
		profit=c;
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
