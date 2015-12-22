package vo;

import type.ReceiptState;
import type.ReceiptType;

public class LeaveRepertoryReceiptVO extends ReceiptVO{

	public String repertoryID;		//本仓库编号
	public String date; 			//一天生成一张出库单
	public String[] expressIDList; //离开仓库的货物ID
	public String[] timeList;		//货物离开仓库的时间
	
	public LeaveRepertoryReceiptVO(String ID, String userID, String time, ReceiptType type, ReceiptState state){
		super(ID, userID, time, type, state);
	}
	
	public LeaveRepertoryReceiptVO(){
		
	}
}
