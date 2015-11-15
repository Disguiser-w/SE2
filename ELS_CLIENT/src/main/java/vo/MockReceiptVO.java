package vo;

import po.ReceiptPO.ReceiptState;
import type.ReceiptType;

public class MockReceiptVO {
		String receiptID;
		String creatorID;
		ReceiptType type;
		ReceiptState state;
		
		public MockReceiptVO(String receiptID,String creatorID,ReceiptType type,ReceiptState state){
			this.receiptID = receiptID;
			this.creatorID = creatorID;
			this.type=type;
			this.state=state;
		}
		
		public void setState(ReceiptState state){
			this.state=state;
		}
		
}
