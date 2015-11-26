package po;

import java.util.ArrayList;

public class EnplaningReceiptPO extends EnIntermediateReceiptPO{
	private OrganizationPO intermediateCentre;
	private PlanePO plane;

	private ArrayList<OrderPO> orderList;

	private double fare;
	private String date;
	private String ID;

	public EnplaningReceiptPO(OrganizationPO intermediateCentre, PlanePO plane,
			ArrayList<OrderPO> orderList, double fare, String ID) {
		super(intermediateCentre, orderList, fare, ID);
		this.plane = plane;
	}

	public ArrayList<OrderPO> getEnplaningReceipt() {
		return orderList;
	}

	public ArrayList<OrderPO> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<OrderPO> orderList) {
		this.orderList = orderList;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public void setEnplaningReceipt(ArrayList<OrderPO> enplaningReceipt) {
		this.orderList = enplaningReceipt;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public OrganizationPO getIntermediateCentre() {
		return intermediateCentre;
	}

	public void setIntermediateCentre(OrganizationPO intermediateCentre) {
		this.intermediateCentre = intermediateCentre;
	}

	public PlanePO getPlane() {
		return plane;
	}

	public void setPlane(PlanePO plane) {
		this.plane = plane;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
