package vo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FareVO {
	public final double money;
	public final String ID;

	public final ArrayList<EnplaningReceiptVO> enplaningReceiptVOList;
	public final ArrayList<EntrainingReceiptVO> entrainingReceiptVOList;
	public final ArrayList<EntruckingReceiptVO> entruckingReceiptVOList;

	public final String time;
	public final OrganizationVO intermediateCentre;

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
}
