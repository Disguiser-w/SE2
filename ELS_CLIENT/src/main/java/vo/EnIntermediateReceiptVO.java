package vo;

import java.util.ArrayList;

public class EnIntermediateReceiptVO {
	public final OrganizationVO intermediateCentre;

	public final ArrayList<OrderVO> orderList;

	public final double fare;
	public final String ID;
	public final String date;

	public EnIntermediateReceiptVO(OrganizationVO intermediateCentre,
			ArrayList<OrderVO> orderList, double fare, String ID, String date) {
		this.orderList = orderList;
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.fare = fare;
		this.date = date;
	}
}
