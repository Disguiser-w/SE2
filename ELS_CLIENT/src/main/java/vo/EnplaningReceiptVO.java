package vo;

import java.util.ArrayList;

public class EnplaningReceiptVO extends EnIntermediateReceiptVO{
	public  OrganizationVO intermediateCentre;
	public  PlaneVO plane;

	public  ArrayList<OrderVO> orderList;

	public  double fare;
	public  String ID;
	public  String date;

	public EnplaningReceiptVO(OrganizationVO intermediateCentre, PlaneVO plane,
			ArrayList<OrderVO> orderList, double fare, String ID, String date) {
		super(intermediateCentre, orderList, fare, ID, date);
		this.plane = plane;
	}
}
