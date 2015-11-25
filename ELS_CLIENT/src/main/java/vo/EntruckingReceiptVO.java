package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntruckingReceiptVO {
	public final OrganizationVO intermediateCentre;
	public final TruckVO truck;
	
	public final ArrayList<OrderVO> entruckingReceipt;
	
	public final String time;
	public final String ID;
	public final double fare = 0;

	public EntruckingReceiptVO(ArrayList<OrderVO> entruckingReceipt,
			String time, String ID, OrganizationVO intermediateCentre,
			TruckVO truck) {
		this.entruckingReceipt = entruckingReceipt;
		this.time = time;
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.truck = truck;
	}
}
