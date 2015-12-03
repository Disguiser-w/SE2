package vo;

import java.util.ArrayList;

import type.ReceiptState;

public class TransferingReceiptVO extends ReceiptVO {
	public final OrganizationVO interdiateCentre;

	public final ArrayList<OrderVO> orderList;

	public final String ID;
	public final String date;

	public ReceiptState receiptState;

	public TransferingReceiptVO(OrganizationVO intermediateCentre,
			ArrayList<OrderVO> orderList, String ID, String date,
			ReceiptState receiptState) {
		this.orderList = orderList;
		this.ID = ID;
		this.date = date;
		this.interdiateCentre = intermediateCentre;
		this.receiptState = receiptState;
	}
}
