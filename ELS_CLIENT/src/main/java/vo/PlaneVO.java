package vo;

public class PlaneVO {
	public final String ID;
	public final String destination;
    public final double farePrice = 0.2;
    
    public PlaneVO(String ID,String destination){
    	this.ID = ID;
        this.destination = destination;
    }
}
