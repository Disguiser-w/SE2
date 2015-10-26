package vo;

public class TrainVO {
	 String ID;
	    double farePrice;
	    
	    public TrainVO(){  	
	    }
	    
	    public TrainVO(double farePrice){
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
