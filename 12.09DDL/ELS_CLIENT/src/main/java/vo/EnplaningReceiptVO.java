package vo;

import java.util.ArrayList;

import type.ReceiptState;

public class EnplaningReceiptVO extends EnIntermediateReceiptVO {
	public OrganizationVO intermediateCentre;
	public PlaneVO plane;

	public ArrayList<OrderVO> orderList;

	public double fare;
	public String ID;
	public String date;

	public ReceiptState receiptState;

	public EnplaningReceiptVO(OrganizationVO intermediateCentre, PlaneVO plane,
			ArrayList<OrderVO> orderList, double fare, String ID, String date,
			ReceiptState receiptState) {
		super(intermediateCentre, orderList, fare, ID, date, receiptState);
		this.plane = plane;
	}
}
