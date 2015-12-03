package businesslogic.datafactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.intermediatedataservice.IntermediateDataService;

//暂时使用 localhost:8888
public class DataFactory {
	public static ExpressDataService getExpressData()
			throws MalformedURLException, RemoteException, NotBoundException {
		ExpressDataService expressData = (ExpressDataService) Naming
				.lookup("//localhost:8888/ExpressDataService");
		return expressData;
	}

	public static BusinessDataService getBusinessData()
			throws MalformedURLException, RemoteException, NotBoundException {
		BusinessDataService businessData = (BusinessDataService) Naming
				.lookup("//localhost:8888/BusinessDataService");
		return businessData;
	}

	public static IntermediateDataService getIntermediateDate()
			throws MalformedURLException, RemoteException, NotBoundException {
		IntermediateDataService intermediateData = (IntermediateDataService) Naming
				.lookup("//localhost:8888/IntermediateDataService");
		return intermediateData;
	}
}
