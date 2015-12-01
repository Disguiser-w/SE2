package vo;

import java.util.ArrayList;

import type.CheckState;

public class DistributeReceiptVO {
	public final String ID;
	public final ArrayList<String> distributeInfo;
	public final String time;
	public final CheckState checkState;

	public DistributeReceiptVO(String ID, ArrayList<String> distributeInfo, String time,CheckState checkState) {
		this.ID = ID;
		this.distributeInfo = distributeInfo;
		this.time = time;
		this.checkState = checkState;
	}
}