package businesslogic.expressbl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.ExpressMainController;
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
