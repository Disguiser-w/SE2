package vo;

import type.ReceiptState;
import type.ReceiptType;


public class ReceiptVO {
	
	public String receiptID;
	public String userID;
	public ReceiptType type;
	public ReceiptState state;

	public ReceiptVO(){
		
	}
	
	public ReceiptVO(String ID, String userID, ReceiptType type, ReceiptState state) {
		this.receiptID = ID;
		this.userID = userID;
		this.type = type;
		this.state = state;
	}
	
}
