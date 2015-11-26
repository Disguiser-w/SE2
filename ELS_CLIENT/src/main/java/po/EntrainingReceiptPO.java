package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntrainingReceiptPO extends EnIntermediateReceiptPO {
	private OrganizationPO intermediateCentre;
	private TrainPO train;

	private ArrayList<OrderPO> orderList;

	private double fare;
	private String date;
	private String ID;

	public EntrainingReceiptPO(OrganizationPO intermediateCentre,
			TrainPO train, ArrayList<OrderPO> orderList, double fare, String ID) {
		super(intermediateCentre, orderList, fare, ID);
		this.train = train;
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

}
