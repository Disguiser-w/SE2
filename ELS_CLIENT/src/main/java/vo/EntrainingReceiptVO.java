package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntrainingReceiptVO {
	public final ArrayList<OrderVO> enplaningReceipt;
	public final String time;
	public final String ID;
	public final OrganizationVO intermediateCentre;
	public final TrainVO train;

	public EntrainingReceiptVO(ArrayList<OrderVO> enplaningReceipt,
			String time, String ID, String intermediateCentre, TrainVO train) {
		this.enplaningReceipt = enplaningReceipt;
		this.time = time;
		this.ID = ID;
		this.intermediateCentre = this.intermediateCentre;
		this.train = train;
	}
}
