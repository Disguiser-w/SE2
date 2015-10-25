package businesslogicservice.expressblservice;

import vo.ExpressVO;

public class ChargeCollectionBL_driver {
	public void drive(ChargeCollectionBLService chargeCollecitonBLService) {
		chargeCollecitonBLService.getChargeInfo();
		chargeCollecitonBLService.chargeCollection(new ExpressVO());
	}

	public void main(String[] args) {
		(new ChargeCollectionBL_driver()).drive(new ChargeCollectionBL_stub());
	}
}
