package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntruckingReceiptPO {
	private ArrayList<OrderPO> enplaningReceipt;
	private String date;
	private String ID;
	private OrganizationPO intermediateCentre;
	private TruckPO truck;

	public EntruckingReceiptPO() {
	}

	public EntruckingReceiptPO(ArrayList<OrderPO> enplaningReceipt) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.date = format.format(date);
		this.enplaningReceipt = enplaningReceipt;
	}

	public ArrayList<OrderPO> getEnplaningReceipt() {
		return enplaningReceipt;
	}

	public void setEnplaningReceipt(ArrayList<OrderPO> enplaningReceipt) {
		this.enplaningReceipt = enplaningReceipt;
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
}
