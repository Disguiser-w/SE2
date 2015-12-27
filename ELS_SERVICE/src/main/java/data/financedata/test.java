package data.financedata;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import dataservice.financedataservice.AccountDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.InitialStockDataService;
import dataservice.financedataservice.PaymentReceiptDataService;

public class test extends UnicastRemoteObject{
	
	protected test() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws UnknownHostException, RemoteException, MalformedURLException{
		
//		System.setProperty("java.rmi.server.hostname",  InetAddress.getLocalHost().toString());
//	System.setProperty("java.rmi.server.hostname", "172.26.210.111");
//	PaymentReceiptDataService data=new PaymentReceiptData();
		AccountDataService accountData=new AccountData();
		CollectionReceiptDataService collectionData=new CollectionReceiptData();
		CostIncomeReceiptDataService costIncomeData=new CostIncomeReceiptData();
		InitialStockDataService initData=new InitialStockData();
		PaymentReceiptDataService paymentData=new PaymentReceiptData();
	LocateRegistry.createRegistry(8800);
////	//绑定RMI名称进行发布
	Naming.rebind("rmi://localhost:8800/AccountDataService", accountData);
	Naming.rebind("rmi://localhost:8800/CollectionReceiptDataService", collectionData);
	Naming.rebind("rmi://localhost:8800/CostIncomeReceiptDataService", costIncomeData);
	Naming.rebind("rmi://localhost:8800/InitialStockDataService", initData);
	Naming.rebind("rmi://localhost:8800/PaymentReceiptDataService", paymentData);
	System.out.println("Service start at 8800 !");

}
}
