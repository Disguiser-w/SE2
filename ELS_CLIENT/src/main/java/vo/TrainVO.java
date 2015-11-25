package vo;

public class TrainVO {
	public final EntrainingReceiptVO entrainingReceipt;
	
	public final String ID;
	public final double farePrice;
	public final String destination = null;

	public TrainVO(double farePrice, String ID,EntrainingReceiptVO entraiingReceipt) {
		this.farePrice = farePrice;
		this.ID = ID;
		this.entrainingReceipt = entraiingReceipt;
	}
}
