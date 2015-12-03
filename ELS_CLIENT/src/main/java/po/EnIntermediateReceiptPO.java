package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import type.ReceiptState;

public class EnIntermediateReceiptPO {
	private OrganizationPO intermediateCentre;

	private ArrayList<OrderPO> orderList;

	private String ID;
	private String date;

	private ReceiptState receiptState;

	public EnIntermediateReceiptPO(OrganizationPO intermediateCentre,
			ArrayList<OrderPO> orderList, String ID) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setDate(format.format(date).substring(0, 10));
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.orderList = orderList;
		this.setReceiptState(ReceiptState.SUBMIT);
	}

	public OrganizationPO getIntermediateCentre() {
		return intermediateCentre;
	}

	public void setIntermediateCentre(OrganizationPO intermediateCentre) {
		this.intermediateCentre = intermediateCentre;
	}

	public ArrayList<OrderPO> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<OrderPO> orderList) {
		this.orderList = orderList;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ReceiptState getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(ReceiptState receiptState) {
		this.receiptState = receiptState;
	}
}
