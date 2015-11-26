package vo;

public class TruckVO {
	public final String ID;
	public final String destination;
	public final double farePrice;

	public TruckVO(double farePrice, String ID, String destination) {
		this.farePrice = farePrice;
		this.ID = ID;
		this.destination = destination;
	}
}
