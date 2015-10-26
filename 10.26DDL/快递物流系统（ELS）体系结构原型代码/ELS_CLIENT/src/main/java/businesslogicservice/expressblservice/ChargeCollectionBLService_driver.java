package businesslogicservice.expressblservice;

import vo.ExpressVO;

public class ChargeCollectionBLService_driver {
	public void drive(ChargeCollectionBLService chargeCollecitonBLService) {
		chargeCollecitonBLService.getChargeInfo();
		chargeCollecitonBLService.chargeCollection(new ExpressVO());
	}

	public void main(String[] args) {
		(new ChargeCollectionBLService_driver()).drive(new ChargeCollectionBLService_stub());
	}
}
