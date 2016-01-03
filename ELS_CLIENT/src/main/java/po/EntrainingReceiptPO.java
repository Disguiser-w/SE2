package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.ReceiptState;

@SuppressWarnings("serial")
public class EntrainingReceiptPO extends EnIntermediateReceiptPO implements
		Serializable {
	private TrainPO train;

	private final long orderNum_max = 200000;

	private ReceiptState receiptState;

	public EntrainingReceiptPO(OrganizationPO intermediateCentre,
			TrainPO train, ArrayList<OrderPO> orderList, String ID) {
		super(intermediateCentre, orderList, ID);
		this.train = train;
		this.fare = train.getFarePrice() * orderNum_max;
	}

	public ArrayList<OrderPO> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<OrderPO> orderList) {
		this.orderList = orderList;
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

	public TrainPO getTrain() {
		return train;
	}

	public void setTrain(TrainPO train) {
		this.train = train;
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

	public ReceiptState getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(ReceiptState receiptState) {
		this.receiptState = receiptState;
	}

}
