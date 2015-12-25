package vo;

import type.ReceiptState;
import type.ReceiptType;

public class EnterRepertoryReceiptVO extends ReceiptVO{

	public String createTime;		//单据创建时间
	public String repertoryID;		//本仓库编号
	public String[] expressIDList; 	//进入仓库的货物ID
	public String[] timeList;		//货物进入仓库的时间
	
	public EnterRepertoryReceiptVO(String ID, String userID, String time, ReceiptState state){
		super(ID, userID, ReceiptType.ENTERREPERTORYRECEIPT, state);
	}
	
	public EnterRepertoryReceiptVO(String ID, String userID, String time, ReceiptState state, 
			String repertoryID, String[] expressIDList, String[] timeList){
		super(ID, userID, ReceiptType.ENTERREPERTORYRECEIPT, state);
		this.createTime = time;
		this.repertoryID = repertoryID;
		this.expressIDList = expressIDList;
		this.timeList = timeList;
	}
	
}
