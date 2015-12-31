package vo;

import java.util.ArrayList;

public class FareVO {
	public OrganizationVO intermediateCentre;

	public ArrayList<EnplaningReceiptVO> enplaningReceiptVOList;
	public ArrayList<EntrainingReceiptVO> entrainingReceiptVOList;
	public ArrayList<EntruckingReceiptVO> entruckingReceiptVOList;

	public double fare_sum;

	public FareVO(OrganizationVO intermediateCentre,
			ArrayList<EnplaningReceiptVO> enplaningReceiptVOList,
			ArrayList<EntrainingReceiptVO> entrainingVOList,
			ArrayList<EntruckingReceiptVO> entruckingVOList, double fare_sum) {
		this.enplaningReceiptVOList = enplaningReceiptVOList;
		this.entrainingReceiptVOList = entrainingVOList;
		this.entruckingReceiptVOList = entruckingVOList;
		this.fare_sum = fare_sum;
		this.intermediateCentre = intermediateCentre;
	}
}
