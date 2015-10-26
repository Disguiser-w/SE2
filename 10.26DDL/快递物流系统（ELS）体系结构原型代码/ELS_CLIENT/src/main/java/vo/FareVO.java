package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FareVO {
     double money;
     String ID;
     
     ArrayList<EnplaningReceiptVO> enplaningReceiptVOList;
     ArrayList<EntrainingReceiptVO> entrainingReceiptVOList;
     ArrayList<EntruckingReceiptVO> entruckingReceiptVOList;
     
     String time;
     OrganizationVO organication;
     
     public FareVO(){    	 
     }
     
     public FareVO(ArrayList<EnplaningReceiptVO> enplaningReceiptVOList){
    	 Date date = new Date();
 		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		this.time = format.format(date);
 		this.enplaningReceiptVOList = enplaningReceiptVOList;
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

	public ArrayList<EnplaningReceiptVO> getEnplaningReceiptVOList() {
		return enplaningReceiptVOList;
	}

	public void setEnplaningReceiptVOList(
			ArrayList<EnplaningReceiptVO> enplaningReceiptVOList) {
		this.enplaningReceiptVOList = enplaningReceiptVOList;
	}

	public ArrayList<EntrainingReceiptVO> getEntrainingReceiptVOList() {
		return entrainingReceiptVOList;
	}

	public void setEntrainingReceiptVOList(
			ArrayList<EntrainingReceiptVO> entrainingReceiptVOList) {
		this.entrainingReceiptVOList = entrainingReceiptVOList;
	}

	public ArrayList<EntruckingReceiptVO> getEntruckingReceiptVOList() {
		return entruckingReceiptVOList;
	}

	public void setEntruckingReceiptVOList(
			ArrayList<EntruckingReceiptVO> entruckingReceiptVOList) {
		this.entruckingReceiptVOList = entruckingReceiptVOList;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public OrganizationVO getOrganication() {
		return organication;
	}

	public void setOrganication(OrganizationVO organication) {
		this.organication = organication;
	}
}
