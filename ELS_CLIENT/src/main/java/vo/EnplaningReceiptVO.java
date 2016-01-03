package vo;

import java.util.ArrayList;

import type.ReceiptState;

public class EnplaningReceiptVO extends EnIntermediateReceiptVO {
	public PlaneVO plane;

	public EnplaningReceiptVO(OrganizationVO intermediateCentre, PlaneVO plane,
			ArrayList<OrderVO> orderList, double fare, String ID, String date,
			ReceiptState receiptState) {
		super(intermediateCentre, orderList, fare, ID, date, receiptState);
		this.plane = plane;
	}
}
