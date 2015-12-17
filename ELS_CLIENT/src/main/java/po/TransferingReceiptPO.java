package po;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import type.ReceiptState;

public class TransferingReceiptPO extends ReceiptPO implements Serializable {
	private OrganizationPO interdiateCentre;

	private ArrayList<OrderPO> orderList;

	private String ID;
	private String date;

	private ReceiptState receiptState;

	public TransferingReceiptPO(OrganizationPO intermediateCentre,
			ArrayList<OrderPO> orderList, String ID) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setDate(format.format(date));
		this.orderList = orderList;
		this.interdiateCentre = intermediateCentre;
		this.ID = ID;
		this.setReceiptState(ReceiptState.SUBMIT);
	}

	public OrganizationPO getInterdiateCentre() {
		return interdiateCentre;
	}

	public void setInterdiateCentre(OrganizationPO interdiateCentre) {
		this.interdiateCentre = interdiateCentre;
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

	public void setDate(String time) {
		this.date = time;
	}

	public ArrayList<OrderPO> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<OrderPO> orderList) {
		this.orderList = orderList;
	}

	public OrganizationPO getInterdiatehall() {
		return interdiateCentre;
	}

	public void setInterdiatehall(OrganizationPO interdiatehall) {
		this.interdiateCentre = interdiatehall;
	}

	public ReceiptState getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(ReceiptState receiptState) {
		this.receiptState = receiptState;
	}
}
