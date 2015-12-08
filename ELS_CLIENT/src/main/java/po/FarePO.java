package po;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FarePO implements Serializable{
	private OrganizationPO organication;

	private ArrayList<EnplaningReceiptPO> enplaningReceiptPOList;
	private ArrayList<EntrainingReceiptPO> entrainingReceiptPOList;
	private ArrayList<EntruckingReceiptPO> entruckingReceiptPOList;

	private double fare_sum;
	private String ID;
	private String date;

	public FarePO(OrganizationPO organication,
			ArrayList<EnplaningReceiptPO> enplaningReceiptPOList,
			ArrayList<EntrainingReceiptPO> entrainingReceiptPOList,
			ArrayList<EntruckingReceiptPO> entruckingReceiptPOList,
			double fare_sum) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.date = format.format(date);
		this.organication = organication;
		this.enplaningReceiptPOList = enplaningReceiptPOList;
		this.entrainingReceiptPOList = entrainingReceiptPOList;
		this.entruckingReceiptPOList = entruckingReceiptPOList;
		this.fare_sum = fare_sum;
	}

	public double getMoney() {
		return fare_sum;
	}

	public void setMoney(double money) {
		this.fare_sum = money;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<EnplaningReceiptPO> getEnplaningReceiptPOList() {
		return enplaningReceiptPOList;
	}

	public void setEnplaningReceiptPOList(
			ArrayList<EnplaningReceiptPO> enplaningReceiptPOList) {
		this.enplaningReceiptPOList = enplaningReceiptPOList;
	}

	public ArrayList<EntrainingReceiptPO> getEntrainingReceiptPOList() {
		return entrainingReceiptPOList;
	}

	public void setEntrainingReceiptPOList(
			ArrayList<EntrainingReceiptPO> entrainingReceiptPOList) {
		this.entrainingReceiptPOList = entrainingReceiptPOList;
	}

	public ArrayList<EntruckingReceiptPO> getEntruckingReceiptPOList() {
		return entruckingReceiptPOList;
	}

	public void setEntruckingReceiptPOList(
			ArrayList<EntruckingReceiptPO> entruckingReceiptPOList) {
		this.entruckingReceiptPOList = entruckingReceiptPOList;
	}

	public String getTime() {
		return date;
	}

	public void setTime(String time) {
		this.date = time;
	}

	public OrganizationPO getOrganication() {
		return organication;
	}

	public void setOrganication(OrganizationPO organication) {
		this.organication = organication;
	}
}
