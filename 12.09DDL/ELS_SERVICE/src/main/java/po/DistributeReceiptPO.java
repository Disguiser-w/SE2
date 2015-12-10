package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.ReceiptState;

public class DistributeReceiptPO implements Serializable {
	private String ID;
	private ArrayList<String> distributeInfo;
	private String time;
	private ReceiptState receiptState;

	public DistributeReceiptPO(String ID, ArrayList<String> distributeInfo, String time,ReceiptState receiptState) {
		this.ID = ID;
		this.distributeInfo = distributeInfo;
		this.time = time;
		this.receiptState = receiptState;
	}

	public ReceiptState getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(ReceiptState receiptState) {
		this.receiptState = receiptState;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<String> getDistributeInfo() {
		return distributeInfo;
	}

	public void setDistributeInfo(ArrayList<String> distributeInfo) {
		this.distributeInfo = distributeInfo;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
