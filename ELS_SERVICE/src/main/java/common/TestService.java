package common;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import data.businessdata.BusinessData;
import data.expressdata.ExpressData;
import data.financedata.AccountData;
import data.financedata.CollectionReceiptData;
import data.financedata.CostIncomeReceiptData;
import data.financedata.InitialStockData;
import data.financedata.PaymentReceiptData;
import data.managedata.CityDistanceData;
import data.managedata.OrganizationData;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.financedataservice.AccountDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.InitialStockDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import dataservice.managedataservice.CityDistanceDataService;
import dataservice.managedataservice.OrganizationDataService;

public class TestService {
	public static void main(String[] args) {

		try {

			// LocateRegistry.createRegistry(7777);
			// Naming.rebind("rmi://172.25.133.95:7777/BusinessDataService",
			// businessData);

			// System.setProperty("java.rmi.server.hostname", "172.25.133.95");
			ExpressDataService expressData = new ExpressData();
			BusinessDataService businessData = new BusinessData();
			CityDistanceDataService cityDistanceData = new CityDistanceData();
			OrganizationDataService organizationData = new OrganizationData();
			AccountDataService accountData=new AccountData();
			CollectionReceiptDataService collectionData=new CollectionReceiptData();
			PaymentReceiptDataService paymentData=new PaymentReceiptData();
			CostIncomeReceiptDataService costincomeData=new CostIncomeReceiptData();
			InitialStockDataService initialData=new InitialStockData();

			LocateRegistry.createRegistry(8888);
			Naming.rebind("rmi://localhost:8888/ExpressDataService", expressData);
			Naming.rebind("rmi://localhost:8888/BusinessDataService", businessData);
			Naming.rebind("rmi://localhost:8888/CityDistanceDataService", cityDistanceData);
			Naming.rebind("rmi://localhost:8888/OrganizationDataService", organizationData);
			Naming.rebind("rmi://localhost:8888/AccountDataService", accountData);
			Naming.rebind("rmi://localhost:8888/CollectionReceiptDataService", collectionData);
			Naming.rebind("rmi://localhost:8888/PaymentReceiptDataService", paymentData);
			Naming.rebind("rmi://localhost:8888/CostIncomeReceiptDataService", costincomeData);
			Naming.rebind("rmi://localhost:8888/InitialStockDataService", initialData);

			System.out.println("Service start");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
