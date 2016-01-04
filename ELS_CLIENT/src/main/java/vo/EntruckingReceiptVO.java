package vo;

import java.util.ArrayList;

import type.ReceiptState;

public class EntruckingReceiptVO extends EnIntermediateReceiptVO {
	public TruckVO truck;

	public EntruckingReceiptVO(OrganizationVO intermediateCentre,
			TruckVO truck, ArrayList<OrderVO> orderList, double fare,
			String ID, String date, ReceiptState receiptState) {
		super(intermediateCentre, orderList, fare, ID, date, receiptState);
		this.truck = truck;
	}
}
