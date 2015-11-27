package po;

public class TrainPO {
<<<<<<< HEAD
	 private String ID;
	 private String destination;
	 private double farePrice;

	    public TrainPO(double farePrice,String ID,String destination){
	    	this.farePrice = farePrice;
	    	this.ID = ID;
	    	this.destination = destination;
	    }
=======
	private String ID;
	private String destination;
	private final double farePrice = 0.002;
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364

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

<<<<<<< HEAD
		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

=======
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public double getFarePrice() {
		return farePrice;
	}
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364
}
