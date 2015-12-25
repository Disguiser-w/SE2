package vo;

import type.ReceiptState;
import type.ReceiptType;

public class LeaveRepertoryReceiptVO extends ReceiptVO{

	public String createTime;		//单据创建时间
	public String repertoryID;		//本仓库编号
	public String[] expressIDList; //离开仓库的货物ID
	public String[] timeList;		//货物离开仓库的时间
	
	public LeaveRepertoryReceiptVO(String ID, String userID, String time, ReceiptState state){
		super(ID, userID, ReceiptType.LEAVEREPERTORYRECEIPT, state);
	}
	
	public LeaveRepertoryReceiptVO(String ID, String userID, String time, ReceiptState state, 
			String repertoryID, String[] expressIDList, String[] timeList){
		super(ID, userID, ReceiptType.LEAVEREPERTORYRECEIPT, state);
		this.createTime = time;
		this.repertoryID = repertoryID;
		this.expressIDList = expressIDList;
		this.timeList = timeList;
	}
	
}
