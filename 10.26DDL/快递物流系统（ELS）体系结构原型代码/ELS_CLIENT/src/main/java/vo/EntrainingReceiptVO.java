package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntrainingReceiptVO {
	ArrayList<OrderVO> enplaningReceipt;
	String time;
	String ID;
	OrganizationVO intermediateCentre;
	PlaneVO plane;

	public EntrainingReceiptVO() {
	}

	public EntrainingReceiptVO(ArrayList<OrderVO> enplaningReceipt) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.time = format.format(date);
		this.enplaningReceipt = enplaningReceipt;
	}

	public ArrayList<OrderVO> getEnplaningReceipt() {
		return enplaningReceipt;
	}

	public void setEnplaningReceipt(ArrayList<OrderVO> enplaningReceipt) {
		this.enplaningReceipt = enplaningReceipt;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public OrganizationVO getIntermediateCentre() {
		return intermediateCentre;
	}

	public void setIntermediateCentre(OrganizationVO intermediateCentre) {
		this.intermediateCentre = intermediateCentre;
	}

	public PlaneVO getPlane() {
		return plane;
	}

	public void setPlane(PlaneVO plane) {
		this.plane = plane;
	}
	
}
