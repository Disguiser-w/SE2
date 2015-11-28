package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EnIntermediateReceiptPO {
	private OrganizationPO intermediateCentre;

	private ArrayList<OrderPO> orderList;

	private double fare;
	private String ID;
	private String date;

	public EnIntermediateReceiptPO(OrganizationPO intermediateCentre,
			ArrayList<OrderPO> orderList, double fare, String ID) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setDate(format.format(date).substring(0, 10));
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.orderList = orderList;
		this.fare = fare;
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

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
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
}