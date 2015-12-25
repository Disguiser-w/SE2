package vo;

public class GoodsVO {
	
	public String Order_ID;
	public double fee;
	public String departurePlace, destination;
	public String enterDate[], leaveDate[];
	public boolean inRepertory;

	public GoodsVO(String Order_ID, double fee, String departurePlace, String destination) {
		this.Order_ID = Order_ID;
		this.fee = fee;
		this.departurePlace = departurePlace;
		this.destination = destination;
		enterDate = new String[4];
		leaveDate = new String[4];
		inRepertory = false;
	}

	public GoodsVO(String Order_ID, double fee, String departurePlace, String destination,
			String[] enterDate, String[] leaveDate) {
		this.Order_ID = Order_ID;
		this.fee = fee;
		this.departurePlace = departurePlace;
		this.destination = destination;
		this.enterDate = enterDate;
		this.leaveDate = leaveDate;
	}

	public GoodsVO(String Order_ID, double fee, String departurePlace, String destination,
			String[] enterDate, String[] leaveDate, boolean b) {
		this.Order_ID = Order_ID;
		this.fee = fee;
		this.departurePlace = departurePlace;
		this.destination = destination;
		this.enterDate = enterDate;
		this.leaveDate = leaveDate;
		this.inRepertory = b;
	}
	
}
