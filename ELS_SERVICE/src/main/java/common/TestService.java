package common;

import java.io.FileNotFoundException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import data.businessdata.BusinessData;
import data.expressdata.ExpressData;
import data.financedata.AccountData;
import data.financedata.CollectionReceiptData;
import data.financedata.CostIncomeReceiptData;
import data.financedata.InitialStockData;
import data.financedata.PaymentReceiptData;
import data.intermediatedata.IntermediateData;
import data.managedata.BasicSalaryData;
import data.managedata.CityDistanceData;
import data.managedata.CostData;
import data.managedata.OrganizationData;
import data.managedata.PerWageData;
import data.repertorydata.GoodsData;
import data.repertorydata.RepertoryData;
import data.userdata.UserData;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.financedataservice.AccountDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.InitialStockDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import dataservice.intermediatedataservice.IntermediateDataService;
import dataservice.managedataservice.BasicSalaryDataService;
import dataservice.managedataservice.CityDistanceDataService;
import dataservice.managedataservice.CostDataService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.managedataservice.PerWageDataService;
import dataservice.repertorydataservice.GoodsDataService;
import dataservice.repertorydataservice.RepertoryDataService;
import dataservice.userdataservice.UserDataService;

public class TestService {
	private static String address;

	public static void main(String[] args) {

		try {

			Scanner in = new Scanner(FileGetter.getFile("../address.txt"));
			address = in.next();
			in.close();

			System.setProperty("java.rmi.server.hostname", address);
			
			ExpressDataService expressData = new ExpressData();
			BusinessDataService businessData = new BusinessData();
			AccountDataService accountData = new AccountData();
			CollectionReceiptDataService collectionData = new CollectionReceiptData();
			PaymentReceiptDataService paymentData = new PaymentReceiptData();
			CostIncomeReceiptDataService costincomeData = new CostIncomeReceiptData();
			InitialStockDataService initialData = new InitialStockData();
			IntermediateDataService intermediateData = new IntermediateData();
			UserDataService userData = new UserData();
			OrganizationDataService organizationData = new OrganizationData();
			RepertoryDataService repertoryData = new RepertoryData();
			GoodsDataService goodsData = new GoodsData();
			PerWageDataService perWageData = new PerWageData();
			BasicSalaryDataService basicSalaryData = new BasicSalaryData();
			CityDistanceDataService cityDistanceData = new CityDistanceData();
			CostDataService costData = new CostData();

			LocateRegistry.createRegistry(8888);

			Naming.rebind("rmi://"+address+":8888/ExpressDataService", expressData);
			Naming.rebind("rmi://"+address+":8888/BusinessDataService", businessData);
			Naming.rebind("rmi://"+address+":8888/AccountDataService", accountData);
			Naming.rebind("rmi://"+address+":8888/CollectionReceiptDataService", collectionData);
			Naming.rebind("rmi://"+address+":8888/PaymentReceiptDataService", paymentData);
			Naming.rebind("rmi://"+address+":8888/CostIncomeReceiptDataService", costincomeData);
			Naming.rebind("rmi://"+address+":8888/InitialStockDataService", initialData);
			Naming.rebind("rmi://"+address+":8888/IntermediateDataService", intermediateData);
			Naming.rebind("rmi://"+address+":8888/UserDataService", userData);
			Naming.rebind("rmi://"+address+":8888/OrganizationDataService", organizationData);
			Naming.rebind("rmi://"+address+":8888/RepertoryDataService", repertoryData);
			Naming.rebind("rmi://"+address+":8888/GoodsDataService", goodsData);
			Naming.rebind("rmi://"+address+":8888/PerWageDataService", perWageData);
			Naming.rebind("rmi://"+address+":8888/BasicSalaryDataService", basicSalaryData);
			Naming.rebind("rmi://"+address+":8888/CityDistanceDataService", cityDistanceData);
			Naming.rebind("rmi://"+address+":8888/CostDataService", costData);
			Naming.rebind("rmi://"+address+":8888/RepertoryDataService", repertoryData);

			System.out.println("Service start");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
