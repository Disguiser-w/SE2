package vo;

import type.ReceiptState;
import type.ReceiptType;


public class ReceiptVO {
	public String receiptID;
	public String userID;
	public String createTime;
	public ReceiptType type;
	public ReceiptState state;

	public ReceiptVO(){
		
	}
	
	public ReceiptVO(String ID, String userID, String time, ReceiptType type, ReceiptState state) {
		this.receiptID = ID;
		this.userID = userID;
		this.createTime = time;
		this.type = type;
		this.state = state;
	}
}
