package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.CheckState;

public class DistributeReceiptPO implements Serializable{
	private String ID;
	private ArrayList<String> distributeInfo;
	private String time;
	private CheckState checkState;

	public DistributeReceiptPO(String ID, ArrayList<String> distributeInfo, String time) {
		this.ID = ID;
		this.distributeInfo = distributeInfo;
		this.time = time;
		this.checkState = CheckState.UNCHECKED;
	}

	public CheckState getCheckState() {
		return checkState;
	}

	public void setCheckState(CheckState checkState) {
		this.checkState = checkState;
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
