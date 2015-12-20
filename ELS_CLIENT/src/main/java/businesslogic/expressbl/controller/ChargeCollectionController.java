package businesslogic.expressbl.controller;

import businesslogic.expressbl.ChargeCollection;
import businesslogicservice.expressblservice.ChargeCollectionBLService;

public class ChargeCollectionController implements ChargeCollectionBLService {

	private ChargeCollection chargeCollection;

	public ChargeCollectionController() {

	}

	public boolean chargeCollection() {
		chargeCollection = new ChargeCollection();
		return true;
	}

}
