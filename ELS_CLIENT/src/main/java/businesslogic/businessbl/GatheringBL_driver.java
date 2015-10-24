package businesslogic.businessbl;

import java.util.ArrayList;

public class GatheringBL_driver {
	public void drive(GatheringBLService gathingBLService) {
		gathingBLService.getChargeInfo();

		gathingBLService.gathering(new GatheringReceiptVO());
	}

	public void main(String[] args) {
		(new GatheringBL_driver()).drive(new GatheringBL_stub());
	}
}
