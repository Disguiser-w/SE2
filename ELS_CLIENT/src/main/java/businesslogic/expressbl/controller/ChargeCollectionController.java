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

	public ExpressVO getChargeInfo(String ID) {
		// TODO Auto-generated method stub
		return chargeCollection.getChargeInfo(ID);
	}

	public boolean chargeCollection(String ID, String chargeInfo) {
		// TODO Auto-generated method stub
		return chargeCollection.chargeCollection(ID, chargeInfo);
	}

	public ExpressDataService getDataManager() {
		return chargeCollection.getExpressData();
	}

}
