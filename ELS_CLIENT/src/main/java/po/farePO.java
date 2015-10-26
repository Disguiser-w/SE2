package po;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FarePO {
	 double money;
     String ID;
     
     ArrayList<EnplaningReceiptPO> enplaningReceiptPOList;
     ArrayList<EntrainingReceiptPO> entrainingReceiptPOList;
     ArrayList<EntruckingReceiptPO> entruckingReceiptPOList;
     
     String time;
     OrganizationPO organication;
     
     public FarePO(){    	 
     }
     
     public FarePO(ArrayList<EnplaningReceiptPO> enplaningReceiptPOList){
    	 Date date = new Date();
 		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		this.time = format.format(date);
 		this.enplaningReceiptPOList = enplaningReceiptPOList;
     }

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
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
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public OrganizationPO getOrganication() {
		return organication;
	}

	public void setOrganication(OrganizationPO organication) {
		this.organication = organication;
	}
}
