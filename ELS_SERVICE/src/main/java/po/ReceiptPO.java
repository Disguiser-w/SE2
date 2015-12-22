package po;

import java.io.Serializable;

import type.ReceiptState;
import type.ReceiptType;

public class ReceiptPO implements Serializable{

	private static final long serialVersionUID = 1L;

	String ID;
	String userID;
	ReceiptType type;
	ReceiptState state;
	
	public ReceiptPO(String ID,String userID,ReceiptType type,ReceiptState state){
		this.ID = ID;
		this.userID = userID;
		this.type = type;
		this.state = state;
	}
	
	public ReceiptPO(){
		
	}
	
	public String getID(){
		return ID;
	}
	
	public String getUserID(){
		return userID;
	}
	
	public ReceiptType getType(){
		return type;
	}
	
	public ReceiptState getState(){
		return state;
	}
	
	//获取时间（单据ID）
	public String getDate(){
		String[] buffer=ID.split("-");
		String s=buffer[1];
		String year=s.substring(0,4);
		String month=s.substring(4,6);
		String day=s.substring(6,8);
		return year+"/"+month+"/"+day;
	}
	
	public void setState(ReceiptState state){
		this.state=state;
	}
	
	
	
	
	

}
