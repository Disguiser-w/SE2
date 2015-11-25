package vo;

public class PlaneVO {
	public final String ID;
	public final String destination;
    public final double farePrice;
    public final EnplaningReceiptVO enplaningReceipt;
    
    public PlaneVO(double farePrice,String ID,EnplaningReceiptVO enplaningReceipt,String destination){
    	this.farePrice = farePrice;
    	this.ID = ID;
        this.enplaningReceipt = enplaningReceipt;
        this.destination = destination;
    }
}
