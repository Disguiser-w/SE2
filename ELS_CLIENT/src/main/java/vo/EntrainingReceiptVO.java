package vo;

import java.util.ArrayList;

import type.CheckState;

public class EntrainingReceiptVO extends EnIntermediateReceiptVO {
	public OrganizationVO intermediateCentre;
	public TrainVO train;

	public ArrayList<OrderVO> orderList;

	public double fare;
	public String ID;
	public String date;

	public CheckState checkState;

	public EntrainingReceiptVO(OrganizationVO intermediateCentre,
			TrainVO train, ArrayList<OrderVO> orderList, double fare,
			String ID, String date, CheckState checkState) {
		super(intermediateCentre, orderList, fare, ID, date, checkState);
		this.train = train;
	}
}
