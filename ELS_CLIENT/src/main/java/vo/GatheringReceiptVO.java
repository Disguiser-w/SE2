/*
 * 表示收款单数据------与盛盛的名字要一样哒
 * */
package vo;

import java.util.ArrayList;

import type.CheckState;

public class GatheringReceiptVO {
	public final OrganizationVO businesshall;
	public final String time;
	public final ArrayList<String> expressList;
	public final ArrayList<Double> money;
	public final double totalmoney;
	public final String receiptID;
	public final CheckState checkState;

	public GatheringReceiptVO(OrganizationVO businesshall, String time,
			ArrayList<String> expressList, ArrayList<Double> money,
			double totalmoney, String receiptID,CheckState checkState) {
		super();
		this.businesshall = businesshall;
		this.time = time;
		this.expressList = expressList;
		this.money = money;
		this.totalmoney = totalmoney;
		this.checkState = checkState;
		this.receiptID = receiptID;
	}

}
