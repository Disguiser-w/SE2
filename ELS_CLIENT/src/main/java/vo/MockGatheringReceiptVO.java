package vo;

import java.util.ArrayList;

public class MockGatheringReceiptVO extends GatheringReceiptVO {
	public MockGatheringReceiptVO(OrganizationVO businesshall, String time,
			ArrayList<String> expressList, ArrayList<Double> money,
			double totalmoney, String receiptID) {
		super(businesshall, time, expressList, money, totalmoney, receiptID);
		// TODO Auto-generated constructor stub
	}

	String HallID;
	String Time;
	double money;
	String GatheringID;

/*	public MockGatheringReceiptVO(String h, String t, double m, String g) {
		HallID = h;
		Time = t;
		money = m;
		GatheringID = g;
	}
*/
	public String getHallID() {
		return HallID;
	}

	public String getTime() {
		return Time;
	}

	public double getTotalmoney() {
		return money;
	}

	public String getGatheringID() {
		return GatheringID;
	}

}
