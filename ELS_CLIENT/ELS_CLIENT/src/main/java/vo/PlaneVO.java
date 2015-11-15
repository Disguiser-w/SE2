package vo;

public class PlaneVO {
    public final String ID;
    public final double farePrice;
    
    public PlaneVO(double farePrice,String ID){
    	this.farePrice = farePrice;
    	this.ID = ID;
    }
}
