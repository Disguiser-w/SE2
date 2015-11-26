package vo;

public class TruckVO {
	public final String ID;
	public final String destination;
	public final double farePrice = 0.02;

	public TruckVO(String ID, String destination) {
		this.ID = ID;
		this.destination = destination;
	}
}
