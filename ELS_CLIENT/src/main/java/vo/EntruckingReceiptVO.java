package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntruckingReceiptVO {
	public final ArrayList<OrderVO> enplaningReceipt;
	public final String time;
	public final String ID;
	public final OrganizationVO intermediateCentre;
	public final TruckVO truck;

	public EntruckingReceiptVO(ArrayList<OrderVO> enplaningReceipt,
			String time, String ID, OrganizationVO intermediateCentre,
			TruckVO truck) {
		this.enplaningReceipt = enplaningReceipt;
		this.time = time;
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.truck = truck;
	}
}
