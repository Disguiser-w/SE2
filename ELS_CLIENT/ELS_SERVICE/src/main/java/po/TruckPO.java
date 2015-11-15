package po;

public class TruckPO {
	String ID;
    double farePrice;
    
    public TruckPO(){  	
    }
    
	public TruckPO(double farePrice){
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
