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
}
