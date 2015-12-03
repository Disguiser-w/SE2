package vo;

import java.util.ArrayList;

import type.ReceiptState;

public class EntruckingReceiptVO extends EnIntermediateReceiptVO {
	public OrganizationVO intermediateCentre;
	public TruckVO truck;

	public ArrayList<OrderVO> orderList;

	public double fare;
	public String date;
	public String ID;

	public ReceiptState receiptState;

	public EntruckingReceiptVO(OrganizationVO intermediateCentre,
			TruckVO truck, ArrayList<OrderVO> orderList, double fare,
			String ID, String date, ReceiptState receiptState) {
		super(intermediateCentre, orderList, fare, ID, date, receiptState);
		this.truck = truck;
	}
}
