package businesslogic.expressbl;

import businesslogic.datafactory.DataFactory;
import dataservice.expressdataservice.ExpressDataService;

public class ChargeCollection {
	private ExpressDataService expressData;

	public ChargeCollection() {
		try {
			expressData = DataFactory.getExpressData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
