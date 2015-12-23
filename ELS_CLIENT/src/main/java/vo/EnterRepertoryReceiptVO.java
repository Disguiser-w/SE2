package vo;

import type.ReceiptState;
import type.ReceiptType;

public class EnterRepertoryReceiptVO extends ReceiptVO{

	public String repertoryID;		//本仓库编号
	public String[] expressIDList; //进入仓库的货物ID
	public String[] timeList;		//货物进入仓库的时间
	
	public EnterRepertoryReceiptVO(String ID, String userID, String time, ReceiptType type, ReceiptState state){
		super(ID, userID, time, type, state);
	}
	
	public EnterRepertoryReceiptVO(String ID, String userID, String time, ReceiptType type, ReceiptState state, 
			String repertoryID, String[] expressIDList, String[] timeList){
		super(ID, userID, time, type, state);
		this.repertoryID = repertoryID;
		this.expressIDList = expressIDList;
		this.timeList = timeList;
	}
	
}
