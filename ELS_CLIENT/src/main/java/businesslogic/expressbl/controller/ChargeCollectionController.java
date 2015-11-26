package businesslogic.expressbl.controller;

import businesslogic.expressbl.ChargeCollection;
import businesslogicservice.expressblservice.ChargeCollectionBLService;
import dataservice.expressdataservice.ExpressDataService;
import vo.ExpressVO;

public class ChargeCollectionController implements ChargeCollectionBLService {

	private ChargeCollection chargeCollection;

	public ChargeCollectionController() {
		chargeCollection = new ChargeCollection();
	}

	public boolean chargeCollection(String chargeInfo) {
		// TODO Auto-generated method stub
		return chargeCollection.chargeCollection(chargeInfo);
	}

}
