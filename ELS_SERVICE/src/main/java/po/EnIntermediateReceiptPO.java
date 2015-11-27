package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EnIntermediateReceiptPO {
	private OrganizationPO intermediateCentre;

	private ArrayList<OrderPO> orderList;

<<<<<<< HEAD
	private double fare;
=======
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364
	private String ID;
	private String date;

	public EnIntermediateReceiptPO(OrganizationPO intermediateCentre,
<<<<<<< HEAD
			ArrayList<OrderPO> orderList, double fare, String ID) {
=======
			ArrayList<OrderPO> orderList, String ID) {
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setDate(format.format(date).substring(0, 10));
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.orderList = orderList;
<<<<<<< HEAD
		this.fare = fare;
=======
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364
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

<<<<<<< HEAD
	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

=======
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364
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
