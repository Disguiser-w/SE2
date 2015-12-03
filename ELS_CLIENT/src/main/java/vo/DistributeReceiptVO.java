package vo;

import java.util.ArrayList;

import type.ReceiptState;

public class DistributeReceiptVO {
	public final String ID;
	public final ArrayList<String> distributeInfo;
	public final String time;
	public final ReceiptState receiptState;

	public DistributeReceiptVO(String ID, ArrayList<String> distributeInfo, String time,ReceiptState receiptState) {
		this.ID = ID;
		this.distributeInfo = distributeInfo;
		this.time = time;
		this.receiptState = receiptState;
	}
}