package businesslogic.adminbl;

public class ChargeCollectionBL_driver {
	public void drive(ChargeCollecitonBLService chargeCollecitonBLService) {
		chargeCollecitonBLService.getChargeInfo();
		chargeCollecitonBLService.chargeCollection(new ExpressVO());
	}

	public void main(String[] args) {
		(new ChargeCollectionBL_driver()).drive(new ChargeCollecitonBL_stub());
	}
}
