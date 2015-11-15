package vo;

public class TruckVO {
	public final String ID;
	public final double farePrice;

	public TruckVO(double farePrice, String ID) {
		this.farePrice = farePrice;
		this.ID = ID;
	}
}
