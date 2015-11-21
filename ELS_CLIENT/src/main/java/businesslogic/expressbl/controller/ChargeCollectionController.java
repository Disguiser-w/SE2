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

	public ExpressVO getChargeInfo() {
		// TODO Auto-generated method stub
		return chargeCollection.getChargeInfo();
	}

	public boolean chargeCollection(ExpressVO vo) {
		// TODO Auto-generated method stub
		return chargeCollection.chargeCollection(vo);
	}
	
	public ExpressDataService getDataManager(){
		return chargeCollection.getExpressData();
	}

}
