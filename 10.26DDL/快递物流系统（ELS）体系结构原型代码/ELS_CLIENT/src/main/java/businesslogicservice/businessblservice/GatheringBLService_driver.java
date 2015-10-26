package businesslogicservice.businessblservice;

import vo.GatheringReceiptVO;

public class GatheringBLService_driver {
	public void drive(GatheringBLService gathingBLService) {
		gathingBLService.getChargeInfo();

		gathingBLService.gathering(new GatheringReceiptVO());
	}

	public void main(String[] args) {
		(new GatheringBLService_driver()).drive(new GatheringBLService_stub());
	}
}
