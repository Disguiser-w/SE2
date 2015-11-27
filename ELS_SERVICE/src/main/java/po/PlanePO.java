package po;

public class PlanePO {
	private String ID;
	private String destination;
<<<<<<< HEAD
	private double farePrice;

	    public PlanePO(double farePrice,String ID,String destination){
	    	this.farePrice = farePrice;
	    	this.ID = ID;
	    	this.destination = destination;
	    }
=======
	private final double farePrice = 0.2;
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364

	public PlanePO(String ID, String destination) {
		this.ID = ID;
		this.destination = destination;
	}

<<<<<<< HEAD
		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public void setID(String iD) {
			ID = iD;
		}
=======
	public String getID() {
		return ID;
	}
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364

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
