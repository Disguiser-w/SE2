package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EnplaningReceiptPO {
	private ArrayList<OrderPO> enplaningReceipt;
	private String date;
	private String ID;
	private OrganizationPO intermediateCentre;
	private PlanePO plane;

	public EnplaningReceiptPO() {
	}

	public EnplaningReceiptPO(ArrayList<OrderPO> enplaningReceipt) {
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

	public PlanePO getPlane() {
		return plane;
	}

	public void setPlane(PlanePO plane) {
		this.plane = plane;
	}
	
}
