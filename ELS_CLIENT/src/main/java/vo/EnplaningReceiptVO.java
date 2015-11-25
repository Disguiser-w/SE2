package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EnplaningReceiptVO {
	public final double fare;
	public final ArrayList<OrderVO> orderList;
	public final String time;
	public final String ID;
	public final OrganizationVO intermediateCentre;
	public final PlaneVO plane;

	public EnplaningReceiptVO(ArrayList<OrderVO> orderList, String time,
			String ID, OrganizationVO intermediateCentre, PlaneVO plane,double fare) {
		this.orderList = orderList;
		this.time = time;
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.plane = plane;
		this.fare = fare;
	}
}
