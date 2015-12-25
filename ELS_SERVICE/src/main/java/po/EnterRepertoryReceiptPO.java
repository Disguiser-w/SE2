package po;

import java.io.Serializable;

import type.ReceiptState;
import type.ReceiptType;

public class EnterRepertoryReceiptPO extends ReceiptPO  implements Serializable{

	private static final long serialVersionUID = 141250191L;

	private String createTime; 		//单据创建时间
	private String repertoryID;		//本仓库编号
	private String[] expressIDList; //离开仓库的货物ID
	private String[] timeList;		//货物离开仓库的时间
	
	public EnterRepertoryReceiptPO(String ID, String userID,ReceiptState state){
		super(ID, userID, ReceiptType.ENTERREPERTORYRECEIPT, state);
	}
	
	public EnterRepertoryReceiptPO(String receiptID, String userID, String time, ReceiptState state,
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
