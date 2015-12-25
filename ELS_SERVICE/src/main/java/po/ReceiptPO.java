package po;

import java.io.Serializable;

import type.ReceiptState;
import type.ReceiptType;

public class ReceiptPO implements Serializable{

private static final long serialVersionUID = 1L;
	
	public String receiptID;	//单据编号
	public String userID;		//制单人编号
	public ReceiptType type;	//单据类型
	public ReceiptState state;	//单据状态
	
	public ReceiptPO(String ID, String userID,  ReceiptType type, ReceiptState state){
		this.receiptID = ID;
		this.userID = userID;
		this.type = type;
		this.state = state;
	}
	
	public ReceiptPO(){
		
	}
	
	public String getID(){
		return this.receiptID;
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
	
	public void setState(ReceiptState state){
		this.state = state;
	}
	
	//获取时间（单据ID）
	public String getDate(){
		String[] buffer = receiptID.split("-");
		String s = buffer[1];
		String year = s.substring(0,4);
		String month = s.substring(4,6);
		String day = s.substring(6,8);
		return year + "/" + month + "/"+day;
	}
	
	
}
