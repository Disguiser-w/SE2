package vo;

import java.util.ArrayList;

public class MockFareVO extends FareVO {
	public MockFareVO(OrganizationVO intermediateCentre,
			ArrayList<EnplaningReceiptVO> enplaningReceiptVOList,
			ArrayList<EntrainingReceiptVO> entrainingVOList,
			ArrayList<EntruckingReceiptVO> entruckingVOList, double fare_sum) {
		super(intermediateCentre, enplaningReceiptVOList, entrainingVOList,
				entruckingVOList, fare_sum);
		// TODO 自动生成的构造函数存根
	}

	String time;
	double money;

	public String getTime() {
		return time;
	}

	public double getMoney() {
		return money;
	}
}
