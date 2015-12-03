package businesslogic.datafactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.financedataservice.AccountDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.InitialStockDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import dataservice.intermediatedataservice.IntermediateDataService;

//暂时使用 localhost:8888
public class DataFactory {
	public static ExpressDataService getExpressData() throws MalformedURLException, RemoteException, NotBoundException {
		ExpressDataService expressData = (ExpressDataService) Naming.lookup("//localhost:8888/ExpressDataService");
		return expressData;
	}

	public static BusinessDataService getBusinessData()
			throws MalformedURLException, RemoteException, NotBoundException {
		BusinessDataService businessData = (BusinessDataService) Naming.lookup("//localhost:8888/BusinessDataService");
		return businessData;
	}
	
	//财务人员相关数据
	public static AccountDataService getAccountData() throws MalformedURLException, RemoteException, NotBoundException{
		AccountDataService accountData=(AccountDataService)Naming.lookup("//localhost:8888/AccountDataService");
		return accountData;
	}
	
	public static CollectionReceiptDataService getCollectionReceiptData() throws MalformedURLException, RemoteException, NotBoundException{
		CollectionReceiptDataService collectionData=(CollectionReceiptDataService)Naming.lookup("//localhost:8888/CollectionReceiptDataService");
		return collectionData;
	}
	
	public static PaymentReceiptDataService getPaymentReceiptData() throws MalformedURLException, RemoteException, NotBoundException{
		PaymentReceiptDataService paymentData=(PaymentReceiptDataService)Naming.lookup("//localhost:8888/PaymentReceiptDataService");
		return paymentData;
	}
	
	public static CostIncomeReceiptDataService getCostIncomeData() throws MalformedURLException, RemoteException, NotBoundException{
		CostIncomeReceiptDataService costIncomeData=(CostIncomeReceiptDataService)Naming.lookup("//localhost:8888/CostIncomeReceiptDataService");
		return costIncomeData;
	}
	
	public static InitialStockDataService getInitialStockData() throws MalformedURLException, RemoteException, NotBoundException{
		InitialStockDataService initData=(InitialStockDataService)Naming.lookup("//localhost:8800/InitialStockDataService");
		return initData;
	}
}
