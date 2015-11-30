package vo;

import java.util.ArrayList;

import type.CheckState;

public class EnIntermediateReceiptVO {
	public final OrganizationVO intermediateCentre;

	public final ArrayList<OrderVO> orderList;

	public final double fare;
	public final String ID;
	public final String date;

	public CheckState checkState;

	public EnIntermediateReceiptVO(OrganizationVO intermediateCentre,
			ArrayList<OrderVO> orderList, double fare, String ID, String date,
			CheckState checkState) {
		this.orderList = orderList;
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.fare = fare;
		this.date = date;
		this.checkState = checkState;
	}
}
