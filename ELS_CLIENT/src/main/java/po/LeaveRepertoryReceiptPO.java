package po;

import java.io.Serializable;

import po.ReceiptPO;
import type.ReceiptState;
import type.ReceiptType;

public class LeaveRepertoryReceiptPO extends ReceiptPO  implements Serializable{

	private static final long serialVersionUID = 141250190L;

	private String repertoryID;		//本仓库编号
	private String date; 			//一天生成一张出库单
	private String[] expressIDList; //离开仓库的货物ID
	private String[] timeList;		//货物离开仓库的时间
	
	public LeaveRepertoryReceiptPO(String receiptID, String userID, ReceiptType type, ReceiptState state){
		this.ID = receiptID;
		this.userID = userID;
		this.type = type;
		this.state = state;
	}
	
	public LeaveRepertoryReceiptPO(String receiptID, String userID, ReceiptType type, ReceiptState state,
			String repertoryID, String date, String[] expressIDList, String[] timeList){
		this(receiptID, userID, type, state);
		this.setRepertoryID(repertoryID);
		this.date = date;
		this.setExpressIDList(expressIDList);
		this.timeList = timeList;
	}
	
	public String getReceiptID(){
		return this.ID;
	}
	
	public void setReceiptID(String receiptID){
		this.ID = receiptID;
	}
	
	public String getUserID(){
		return this.userID;
	}
	
	public void setUserID(String userID){
		this.userID = userID;
	}

	public String getRepertoryID() {
		return repertoryID;
	}
	
	public void setRepertoryID(String repertoryID) {
		this.repertoryID = repertoryID;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String[] getExpressIDList() {
		return expressIDList;
	}

	public void setExpressIDList(String[] expressIDList) {
		this.expressIDList = expressIDList;
	}
	
	public String[] getTimeList(){
		return this.timeList;
	}
	
	public void setTimeList(String[] timeList){
		this.timeList = timeList;
	}
	
	
}
