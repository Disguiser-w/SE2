package vo;

import java.util.ArrayList;

import type.CheckState;

public class EnplaningReceiptVO extends EnIntermediateReceiptVO {
	public OrganizationVO intermediateCentre;
	public PlaneVO plane;

	public ArrayList<OrderVO> orderList;

	public double fare;
	public String ID;
	public String date;

	public CheckState checkState;

	public EnplaningReceiptVO(OrganizationVO intermediateCentre, PlaneVO plane,
			ArrayList<OrderVO> orderList, double fare, String ID, String date,
			CheckState checkState) {
		super(intermediateCentre, orderList, fare, ID, date, checkState);
		this.plane = plane;
	}
}
