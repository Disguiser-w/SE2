package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntrainingReceiptVO {
	public final OrganizationVO intermediateCentre;
	public final TrainVO train;
	
	public final ArrayList<OrderVO> entrainingReceipt;
	
	public final String time;
	public final String ID;
	public final double fare;

	public EntrainingReceiptVO(ArrayList<OrderVO> entrainingReceipt,
			String time, String ID, String intermediateCentre, TrainVO train,double fare) {
		this.entrainingReceipt = entrainingReceipt;
		this.time = time;
		this.ID = ID;
		this.intermediateCentre = this.intermediateCentre;
		this.train = train;
		this.fare = fare;
	}
}
