package vo;

import java.util.ArrayList;

import type.CheckState;

public class EntruckingReceiptVO extends EnIntermediateReceiptVO {
	public OrganizationVO intermediateCentre;
	public TruckVO truck;

	public ArrayList<OrderVO> orderList;

	public double fare;
	public String date;
	public String ID;

	public CheckState checkState;

	public EntruckingReceiptVO(OrganizationVO intermediateCentre,
			TruckVO truck, ArrayList<OrderVO> orderList, double fare,
			String ID, String date, CheckState checkState) {
		super(intermediateCentre, orderList, fare, ID, date, checkState);
		this.truck = truck;
	}
}
