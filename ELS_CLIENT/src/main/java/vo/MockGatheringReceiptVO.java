package vo;

public class MockGatheringReceiptVO extends GatheringReceiptVO {
	String HallID;
	String Time;
	double money;
	String GatheringID;

	public MockGatheringReceiptVO(String h, String t, double m, String g) {
		HallID = h;
		Time = t;
		money = m;
		GatheringID = g;
	}

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
