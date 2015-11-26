package po;

public class PlanePO {
	private String ID;
	private String destination;
	private final double farePrice = 0.2;

	public PlanePO(String ID, String destination) {
		this.ID = ID;
		this.destination = destination;
	}

	public String getID() {
		return ID;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public double getFarePrice() {
		return farePrice;
	}
}
