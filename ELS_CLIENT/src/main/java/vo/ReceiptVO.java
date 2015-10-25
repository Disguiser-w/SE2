package vo;

import po.ReceiptPO.ReceiptState;
import type.ReceiptType;
public class ReceiptVO {
	//编号
	String ID;
	//用户
	String userID;
	//类型
	ReceiptType type;
	//状态
	ReceiptState state;
	
	public ReceiptVO(String ID,String userID,ReceiptType type,ReceiptState state){
		this.ID=ID;
		this.userID=userID;
		this.type=type;
		this.state=state;
	}
	
public ReceiptVO(){
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
