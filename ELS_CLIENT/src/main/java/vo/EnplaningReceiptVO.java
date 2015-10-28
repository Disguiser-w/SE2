package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EnplaningReceiptVO {
	public final ArrayList<OrderVO> enplaningReceipt;
	public final String time;
	public final String ID;
	public final OrganizationVO intermediateCentre;
	public final PlaneVO plane;

	public EnplaningReceiptVO(ArrayList<OrderVO> enplaningReceipt, String time,
			String ID, OrganizationVO intermediateCentre, PlaneVO plane) {
		this.enplaningReceipt = enplaningReceipt;
		this.time = time;
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.plane = plane;
	}
}
