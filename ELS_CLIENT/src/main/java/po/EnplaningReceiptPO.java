package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EnplaningReceiptPO {
	private ArrayList<OrderPO> orderList;
	private String date;
	private String ID;
	private OrganizationPO intermediateCentre;
	private PlanePO plane;

	public EnplaningReceiptPO(ArrayList<OrderPO> orderList,String ID,OrganizationPO intermediateCentre,PlanePO plane) {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.date = format.format(date).substring(0, 10);
		this.orderList = orderList;
		this.ID = ID;
		this.intermediateCentre = intermediateCentre;
		this.plane = plane;
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

	public PlanePO getPlane() {
		return plane;
	}

	public void setPlane(PlanePO plane) {
		this.plane = plane;
	}
	
}
