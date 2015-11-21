package vo;

import java.util.ArrayList;

import po.ReceiptPO.ReceiptState;
import type.ReceiptType;

public class CollectionReceiptVO extends ReceiptVO {
	public  ArrayList<GatheringReceiptVO> gvo;
	public  double[] money;
	public  double totalMoney;
	public  String hallID;
	public  String date;
	
	public CollectionReceiptVO(String ID,String userID,ReceiptState state,double totalMoney,String date){
		super(ID,userID,ReceiptType.COLLECTIONRECEIPT,state);
		this.totalMoney = totalMoney;
		this.date = date;
	}
    //account还需不需要呢？？？
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
	
	public double getIncome(){
		return totalMoney;
	}
	
	public double totalMoney(){
		return totalMoney;
	}
	
	


}
