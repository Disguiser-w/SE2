package po;

import vo.EnplaningReceiptVO;

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
