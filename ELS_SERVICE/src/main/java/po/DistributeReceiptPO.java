package po;

import java.util.ArrayList;

public class DistributeReceiptPO {
	private String ID;
	private ArrayList<String> distributeInfo;
	private String time;

	public DistributeReceiptPO(String ID, ArrayList<String> distributeInfo, String time) {
		this.ID = ID;
		this.distributeInfo = distributeInfo;
		this.time = time;
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
