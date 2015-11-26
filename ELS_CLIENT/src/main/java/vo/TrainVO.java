package vo;

public class TrainVO {	
	public final String ID;
	public final double farePrice;
	public final String destination;

	public TrainVO(double farePrice, String ID,String destination) {
		this.farePrice = farePrice;
		this.ID = ID;
		this.destination = destination;
	}
}
