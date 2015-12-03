package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.PaymentReceiptPO;
import dataservice.financedataservice.AccountDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.InitialStockDataService;
import dataservice.financedataservice.PaymentReceiptDataService;

public class test extends UnicastRemoteObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected test() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
		try {
			AccountDataService accountData=(AccountDataService)Naming.lookup("//localhost:8800/AccountDataService");
			CollectionReceiptDataService collectionData=(CollectionReceiptDataService)Naming.lookup("//localhost:8800/CollectionReceiptDataService");
			CostIncomeReceiptDataService costIncomeData=(CostIncomeReceiptDataService)Naming.lookup("//localhost:8800/CostIncomeReceiptDataService");
			InitialStockDataService initData=(InitialStockDataService)Naming.lookup("//localhost:8800/InitialStockDataService");
			PaymentReceiptDataService paymentData=(PaymentReceiptDataService)Naming.lookup("//localhost:8800/PaymentReceiptDataService");
			System.out.println(accountData.findbyName("鼓楼").getMoney());
			ArrayList<PaymentReceiptPO> pos=paymentData.getAllPaymentReceipt();
			for(PaymentReceiptPO p:pos){
				System.out.println("ID: "+p.getID());
			}
			System.out.println("client connect!");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
