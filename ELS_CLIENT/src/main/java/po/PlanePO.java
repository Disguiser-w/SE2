package po;

public class PlanePO {
	 String ID;
	    double farePrice;
	    
	    public PlanePO(){  	
	    }
	    
	    public PlanePO(double farePrice){
	    	this.farePrice = farePrice;
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
	    
}
