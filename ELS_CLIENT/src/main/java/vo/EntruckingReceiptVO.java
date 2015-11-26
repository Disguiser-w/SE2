package vo;

import java.util.ArrayList;

public class EntruckingReceiptVO extends EnIntermediateReceiptVO {
	public OrganizationVO intermediateCentre;
	public TruckVO truck;

	public ArrayList<OrderVO> orderList;

	public double fare;
	public String date;
	public String ID;

	public EntruckingReceiptVO(OrganizationVO intermediateCentre,
			TruckVO truck, ArrayList<OrderVO> orderList, double fare,
			String ID, String date) {
		super(intermediateCentre, orderList, fare, ID, date);
		this.truck = truck;
	}
}
