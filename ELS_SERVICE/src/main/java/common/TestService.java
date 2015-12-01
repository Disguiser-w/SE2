package common;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import data.businessdata.BusinessData;
import data.expressdata.ExpressData;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;

public class TestService {
	public static void main(String[] args) {

		try {

			// BusinessDataService businessData = new BusinessData();
			// LocateRegistry.createRegistry(7777);
			// Naming.rebind("rmi://172.25.133.95:7777/BusinessDataService",
			// businessData);

//			System.setProperty("java.rmi.server.hostname", "172.25.133.95");
			ExpressDataService expressData = new ExpressData();
			BusinessDataService businessData = new BusinessData();
			LocateRegistry.createRegistry(8888);
			Naming.rebind("rmi://localhost:8888/ExpressDataService", expressData);
			Naming.rebind("rmi://localhost:8888/BusinessDataService",businessData);

			System.out.println("Service start");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
