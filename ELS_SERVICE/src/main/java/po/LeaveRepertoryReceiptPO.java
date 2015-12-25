package po;

import java.io.Serializable;

import po.ReceiptPO;
import type.ReceiptState;
import type.ReceiptType;

public class LeaveRepertoryReceiptPO extends ReceiptPO implements Serializable{

	private static final long serialVersionUID = 141250190L;

	private String createTime; 		//单据创建时间
	private String repertoryID;		//本仓库编号
	private String[] expressIDList; //离开仓库的货物ID
	private String[] timeList;		//货物离开仓库的时间
	
	public LeaveRepertoryReceiptPO(String receiptID, String userID, ReceiptState state){
		super(receiptID, userID, ReceiptType.LEAVEREPERTORYRECEIPT, state);
	}
	
	public LeaveRepertoryReceiptPO(String receiptID, String userID, String time, ReceiptState state,
			String repertoryID, String[] expressIDList, String[] timeList){
		this(receiptID, userID, state);
		this.setCreateTime(time);
		this.setRepertoryID(repertoryID);
		this.setExpressIDList(expressIDList);
		this.timeList = timeList;
	}
	
	public String getReceiptID(){
		return this.receiptID;
	}
	
	public void setReceiptID(String receiptID){
		this.receiptID = receiptID;
	}
	
	public String getUserID(){
		return this.userID;
	}
	
	public void setUserID(String userID){
		this.userID = userID;
	}
	
	public String getCreateTime(){
		return this.createTime;
	}
	
	public void setCreateTime(String time){
		this.createTime = time;
	}

	public String getRepertoryID() {
		return repertoryID;
	}
	
	public void setRepertoryID(String repertoryID) {
		this.repertoryID = repertoryID;
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
