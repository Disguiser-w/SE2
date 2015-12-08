package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.ReceiptState;

public class EntruckingReceiptPO extends EnIntermediateReceiptPO implements Serializable{
	private OrganizationPO intermediateCentre;
	private TruckPO truck;

	private ArrayList<OrderPO> orderList;

	private final long orderNum_max = 1000;
	private double fare;
	private String date;
	private String ID;

	private ReceiptState receiptState;

	public EntruckingReceiptPO(OrganizationPO intermediateCentre,
			TruckPO truck, ArrayList<OrderPO> orderList, double fare, String ID) {
		super(intermediateCentre, orderList, ID);
		this.truck = truck;
		this.fare = truck.getFarePrice() * orderNum_max;
		this.receiptState = ReceiptState.SUBMIT;
	}

	public ArrayList<OrderPO> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<OrderPO> orderList) {
		this.orderList = orderList;
	}

	public ReceiptState getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(ReceiptState receiptState) {
		this.receiptState = receiptState;
	}

	public long getOrderNum_max() {
		return orderNum_max;
	}

	public ArrayList<OrderPO> getEnplaningReceipt() {
		return orderList;
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

	public TruckPO getTruck() {
		return truck;
	}

	public void setTruck(TruckPO truck) {
		this.truck = truck;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
}
