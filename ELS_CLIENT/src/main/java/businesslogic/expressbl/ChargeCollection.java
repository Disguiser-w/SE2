package businesslogic.expressbl;

import java.util.ArrayList;

import dataservice.expressdataservice.ExpressDataService;
import vo.ExpressVO;

public class ChargeCollection {
	private ExpressDataService expressData;

	public ChargeCollection() {

	}

	public ExpressVO getChargeInfo() {
		// TODO Auto-generated method stub
		ArrayList<Double> charge = new ArrayList<Double>();
		charge.add(10.0);
		return new ExpressVO(null, null, null, charge, null, null, null, null);
	}

	public boolean chargeCollection(ExpressVO vo) {
		// TODO Auto-generated method stub
		return true;
	}

	public ExpressDataService getExpressData(){
		return expressData;
	}
}
