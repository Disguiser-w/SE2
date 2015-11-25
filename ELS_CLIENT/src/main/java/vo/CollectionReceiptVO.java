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
	public String ID;
	private String userID;
	private ReceiptState state;
	private String account;
	public CollectionReceiptVO(String ID,String userID,ReceiptType type,ReceiptState state,double totalMoney,String date,String account){
		super(ID,userID,ReceiptType.COLLECTIONRECEIPT,state);
		this.totalMoney = totalMoney;
		this.date = date;
		this.ID=ID;
		this.userID=userID;
		this.state=state;
		this.account=account;
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
	
	
	public String getID(){
		return ID;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public ReceiptState getState(){
		return state;
	}
	
	public ReceiptType getType(){
		return type;
	}
	
	public String getAccount(){
		return account;
	}


}
