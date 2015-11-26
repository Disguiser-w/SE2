package po;

public class TrainPO {
	 private String ID;
	 private String destination;
	 private double farePrice;

	    public TrainPO(double farePrice,String ID,String destination){
	    	this.farePrice = farePrice;
	    	this.ID = ID;
	    	this.destination = destination;
	    }

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}

		public double getFarePrice() {
			return farePrice;
		}

		public void setFarePrice(double farePrice) {
			this.farePrice = farePrice;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

}
