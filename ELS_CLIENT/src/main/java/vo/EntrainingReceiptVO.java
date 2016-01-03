package vo;

import java.util.ArrayList;

import type.ReceiptState;

public class EntrainingReceiptVO extends EnIntermediateReceiptVO {
	public TrainVO train;

	public EntrainingReceiptVO(OrganizationVO intermediateCentre,
			TrainVO train, ArrayList<OrderVO> orderList, double fare,
			String ID, String date, ReceiptState receiptState) {
		super(intermediateCentre, orderList, fare, ID, date, receiptState);
		this.train = train;
	}
}
