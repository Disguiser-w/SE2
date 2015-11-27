package po;

public class TruckPO {
	private String ID;
	private String destination;
<<<<<<< HEAD
    private double farePrice;
    
	public TruckPO(double farePrice,String ID,String destination){
    	this.farePrice = farePrice;
=======
    private final double farePrice = 0.02;
    
	public TruckPO(String ID,String destination){
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364
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
}
