package po;

import java.io.Serializable;

public class TrainPO implements Serializable{
	private String ID;
	private String destination;
	private final double farePrice = 0.002;

	public TrainPO(String ID, String destination) {
		this.ID = ID;
		this.destination = destination;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getFarePrice() {
		return farePrice;
	}
}
