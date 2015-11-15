package po;

import java.io.Serializable;

import type.ReceiptType;

public class ReceiptPO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	//单据类型的枚举类,放到type包里去了
//	public enum ReceiptType implements Serializable{
//		//快递员单据
//		//营业厅单据
//		//库存单据
//		//财务单据
//		COLLECTIONRECEIPT,PAYMENTRECEIPT,COSTINCOMERECEPTION;
//	}
	
	public enum ReceiptState implements Serializable{
		//草稿，提交，审批后
		DRAFT,SUBMIT,APPROVE;
	}
	
	//单据的编号
	String ID;
	//单据的用户
	String userID;
	//单据的类型
	ReceiptType type;
	//单据状态（草稿，提交，审批后）
	ReceiptState state;
	public ReceiptPO(String ID,String userID,ReceiptType type,ReceiptState state){
		this.ID=ID;
		this.userID=userID;
		this.type=type;
		this.state=state;
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
