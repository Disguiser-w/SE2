package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.ReceiptState;

@SuppressWarnings("serial")
public class EnplaningReceiptPO extends EnIntermediateReceiptPO implements Serializable{
	private PlanePO plane;

	private final long OrderNum_max = 5000;

	public EnplaningReceiptPO(OrganizationPO intermediateCentre, PlanePO plane,
			ArrayList<OrderPO> orderList, String ID) {
		super(intermediateCentre, orderList, ID);
		this.plane = plane;
		this.fare = plane.getFarePrice() * OrderNum_max;
	}

	public ReceiptState getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(ReceiptState receiptState) {
		this.receiptState = receiptState;
	}

	public long getOrderNum_max() {
		return OrderNum_max;
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
