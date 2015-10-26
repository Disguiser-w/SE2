package vo;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptState;
import type.ReceiptType;

public class CollectionReceiptVO extends ReceiptVO {

	ArrayList<GatheringReceiptVO> gvo=new ArrayList<GatheringReceiptVO> ();
	double[] money;
	double totalMoney;
	String hallID;
	String date;
	
	public CollectionReceiptVO(String ID,String userID,ReceiptType type,ReceiptState state,double m){
		super(ID,userID,ReceiptType.COLLECTIONRECEIPT,state);
		totalMoney=m;
	}
	public CollectionReceiptVO(){
		
	}
	
	public ArrayList<GatheringReceiptVO> getGathering(){
		return gvo;
	}
	
	public String getHallID(){
		return hallID;
		}
	
	public String  getDate(){
		return date;
	}
	
	public double[] getMoney(){
		return money;
	}
	
	public double totalMoney(){
		return totalMoney;
	}
	
	
	
    //account还需不需要呢？？？


}
