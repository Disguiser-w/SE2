package vo;

import java.util.ArrayList;

public class DistributeReceiptVO {
	public final String ID;
	public final ArrayList<String> distributeInfo;
	public final String time;

	public DistributeReceiptVO(String ID, ArrayList<String> distributeInfo, String time) {
		this.ID = ID;
		this.distributeInfo = distributeInfo;
		this.time = time;
	}
}