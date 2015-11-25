package vo;

public class TruckVO {
	public final EntruckingReceiptVO entruckingReceipt;
	
	public final String ID;
	public final String destination;
	public final double farePrice;

	public TruckVO(double farePrice, String ID,EntruckingReceiptVO entruckingReceipt,String destination) {
		this.farePrice = farePrice;
		this.ID = ID;
		this.entruckingReceipt = entruckingReceipt;
		this.destination = destination;
	}
}
