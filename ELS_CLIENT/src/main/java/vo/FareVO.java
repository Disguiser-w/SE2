package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FareVO {
	public OrganizationVO intermediateCentre;

	public ArrayList<EnplaningReceiptVO> enplaningReceiptVOList;
	public ArrayList<EntrainingReceiptVO> entrainingReceiptVOList;
	public ArrayList<EntruckingReceiptVO> entruckingReceiptVOList;

	public double fare_sum;
	public String ID;
	public String date;

	public FareVO(OrganizationVO intermediateCentre,
			ArrayList<EnplaningReceiptVO> enplaningReceiptVOList,
			ArrayList<EntrainingReceiptVO> entrainingVOList,
			ArrayList<EntruckingReceiptVO> entruckingVOList, double fare_sum,
			String ID, String date) {
		this.enplaningReceiptVOList = enplaningReceiptVOList;
		this.entrainingReceiptVOList = entrainingVOList;
		this.entruckingReceiptVOList = entruckingVOList;
		this.fare_sum = fare_sum;
		this.ID = ID;
		this.date = date;
		this.intermediateCentre = intermediateCentre;
	}
}
