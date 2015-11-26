package po;

public class TruckPO {
	private String ID;
	private String destination;
    private double farePrice;
    
	public TruckPO(double farePrice,String ID,String destination){
    	this.farePrice = farePrice;
    	this.ID = ID;
    	this.destination = destination;
    }

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
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
