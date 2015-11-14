package vo;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptState;
import type.ReceiptType;

public class CostIncomeReceiptVO extends ReceiptVO{
	ArrayList<CollectionReceiptVO> cvo=new ArrayList<CollectionReceiptVO>();
	ArrayList<PaymentReceiptVO> pvo=new ArrayList<PaymentReceiptVO>();
	double cost;
	double income;
	double profit;
	String date;
	
	public CostIncomeReceiptVO(String ID,String userID,ReceiptType type,ReceiptState state,ArrayList<CollectionReceiptVO> vo1,
			ArrayList<PaymentReceiptVO> vo2,double a,double b,double c){
		super(ID,userID,ReceiptType.COSTINCOMERECEPTION,state);
		cost=a;
		income=b;
		profit=c;
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
	
	}
