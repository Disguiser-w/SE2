package po;

public class PlanePO {
	private String ID;
	private String destination;
	private double farePrice;

	    public PlanePO(double farePrice,String ID,String destination){
	    	this.farePrice = farePrice;
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

		public void setFarePrice(double farePrice) {
			this.farePrice = farePrice;
		}
	    
}
