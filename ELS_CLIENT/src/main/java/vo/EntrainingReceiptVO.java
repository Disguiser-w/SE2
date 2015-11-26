package vo;

import java.util.ArrayList;

public class EntrainingReceiptVO extends EnIntermediateReceiptVO {
	public OrganizationVO intermediateCentre;
	public TrainVO train;

	public ArrayList<OrderVO> orderList;

	public double fare;
	public String ID;
	public String date;

	public EntrainingReceiptVO(OrganizationVO intermediateCentre,
			TrainVO train, ArrayList<OrderVO> orderList, double fare,
			String ID, String date) {
		super(intermediateCentre, orderList, fare, ID, date);
		this.train = train;
	}
}
