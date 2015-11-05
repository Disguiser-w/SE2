package vo;

public class TrainVO {
	public final String ID;
	public final double farePrice;

	public TrainVO(double farePrice, String ID) {
		this.farePrice = farePrice;
		this.ID = ID;
	}
}
