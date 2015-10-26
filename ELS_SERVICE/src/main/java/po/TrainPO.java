package po;

public class TrainPO {
	 String ID;
	    double farePrice;
	    
	    public TrainPO(){  	
	    }
	    
	    public TrainPO(double farePrice){
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
