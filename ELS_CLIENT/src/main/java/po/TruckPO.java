package po;

public class TruckPO {
	private String ID;
	private String destination;
    private final double farePrice = 0.02;
    
	public TruckPO(String ID,String destination){
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
}
