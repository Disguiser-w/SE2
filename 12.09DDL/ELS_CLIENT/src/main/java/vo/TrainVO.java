package vo;

public class TrainVO {	
	public final String ID;
	public final double farePrice = 0.002;
	public final String destination;

	public TrainVO(String ID,String destination) {
		this.ID = ID;
		this.destination = destination;
	}
}
