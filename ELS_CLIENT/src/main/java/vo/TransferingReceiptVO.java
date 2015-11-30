package vo;

import java.util.ArrayList;

import type.CheckState;

public class TransferingReceiptVO extends ReceiptVO {
	public final OrganizationVO interdiateCentre;

	public final ArrayList<OrderVO> orderList;

	public final String ID;
	public final String date;

	public CheckState checkState;

	public TransferingReceiptVO(OrganizationVO intermediateCentre,
			ArrayList<OrderVO> orderList, String ID, String date,
			CheckState checkState) {
		this.orderList = orderList;
		this.ID = ID;
		this.date = date;
		this.interdiateCentre = intermediateCentre;
		this.checkState = checkState;
	}
}
