package vo;

public class TruckVO {
	 String ID;
	    double farePrice;
	    
	    public TruckVO(){  	
	    }
	    
	    public TruckVO(double farePrice){
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
