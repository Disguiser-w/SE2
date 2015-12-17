package vo;

import java.util.ArrayList;

import type.ReceiptState;

public class EnIntermediateReceiptVO extends ReceiptVO {
	public final OrganizationVO intermediateCentre;

	public final ArrayList<OrderVO> orderList;

	public final double fare;
	public final String ID;
	public final String date;

	public ReceiptState receiptState;

	public EnIntermediateReceiptVO(OrganizationVO intermediateCentre,
			ArrayList<OrderVO> orderList, double fare, String ID, String date,
			ReceiptState receiptState) {
		this.orderList = orderList;
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.fare = fare;
		this.date = date;
		this.receiptState = receiptState;
	}
}
