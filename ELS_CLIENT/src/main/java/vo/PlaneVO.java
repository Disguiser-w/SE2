package vo;

public class PlaneVO {
	public final String ID;
	public final String destination;
    public final double farePrice;
    
    public PlaneVO(double farePrice,String ID,String destination){
    	this.farePrice = farePrice;
    	this.ID = ID;
        this.destination = destination;
    }
}
