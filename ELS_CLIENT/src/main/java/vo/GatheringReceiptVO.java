/*
 * 表示收款单数据------与盛盛的名字要一样哒
 * */
package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GatheringReceiptVO {
	public final OrganizationVO businesshall;
	public final String time;
	public final ArrayList<String> expressList;
	public final ArrayList<Double> money;
	public final double totalmoney;
	public final String receiptID;

	public GatheringReceiptVO(OrganizationVO businesshall, String time, ArrayList<String> expressList,
			ArrayList<Double> money, double totalmoney, String receiptID) {
		super();
		this.businesshall = businesshall;
		this.time = time;
		this.expressList = expressList;
		this.money = money;
		this.totalmoney = totalmoney;
		this.receiptID = receiptID;
	}

}
