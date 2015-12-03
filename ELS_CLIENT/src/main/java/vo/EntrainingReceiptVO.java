package vo;

import java.util.ArrayList;

import type.ReceiptState;

public class EntrainingReceiptVO extends EnIntermediateReceiptVO {
	public OrganizationVO intermediateCentre;
	public TrainVO train;

	public ArrayList<OrderVO> orderList;

	public double fare;
	public String ID;
	public String date;

	public ReceiptState receiptState;

	public EntrainingReceiptVO(OrganizationVO intermediateCentre,
			TrainVO train, ArrayList<OrderVO> orderList, double fare,
			String ID, String date, ReceiptState receiptState) {
		super(intermediateCentre, orderList, fare, ID, date, receiptState);
		this.train = train;
	}
}
