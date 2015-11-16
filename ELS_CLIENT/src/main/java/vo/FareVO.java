package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FareVO {
	public  double money;
	public  String ID;

	public  ArrayList<EnplaningReceiptVO> enplaningReceiptVOList;
	public  ArrayList<EntrainingReceiptVO> entrainingReceiptVOList;
	public  ArrayList<EntruckingReceiptVO> entruckingReceiptVOList;

	public  String time;
	public  OrganizationVO intermediateCentre;

	public FareVO(ArrayList<EnplaningReceiptVO> enplaningReceiptVOList,
			ArrayList<EntrainingReceiptVO> entrainingVOList,
			ArrayList<EntruckingReceiptVO> entruckingVOList, double money,
			String ID, String time, OrganizationVO intermediateCentre) {
		this.enplaningReceiptVOList = enplaningReceiptVOList;
		this.entrainingReceiptVOList = entrainingVOList;
		this.entruckingReceiptVOList = entruckingVOList;
		this.money = money;
		this.ID = ID;
		this.time = time;
		this.intermediateCentre = intermediateCentre;
	}
	
	public FareVO(){
		
	}
	public FareVO(String t,double m){
		time=t;
		money=m;
	}
	
	public String getTime(){
		return time;
	}
	
	public double getMoney(){
		return money;
	}

}
