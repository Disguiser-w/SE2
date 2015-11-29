package vo;

public class GoodsVO {
	private String Order_ID;
	private String departurePlace, destination;
	private String enterDate[], leaveDate[];

	public GoodsVO(String Order_ID, String departurePlace, String destination) {
		this.Order_ID = Order_ID;
		this.departurePlace = departurePlace;
		this.destination = destination;
		enterDate = new String[4];
		leaveDate = new String[4];
	}

	public GoodsVO(String Order_ID, String departurePlace, String destination,
			String[] enterDate, String[] leaveDate) {
		this.Order_ID = Order_ID;
		this.departurePlace = departurePlace;
		this.destination = destination;
		this.enterDate = enterDate;
		this.leaveDate = leaveDate;
	}

}
