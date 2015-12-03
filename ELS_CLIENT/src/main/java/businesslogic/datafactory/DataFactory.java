package businesslogic.datafactory;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.FileGetter;
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

//暂时使用 localhost:8888
public class DataFactory {
	public static String address;

	static {
		try {
			Scanner in = new Scanner(FileGetter.getFile("address.dat"));
			address = in.next();
			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ExpressDataService getExpressData() throws MalformedURLException, RemoteException, NotBoundException {
		ExpressDataService expressData = (ExpressDataService) Naming.lookup("//" + address + "/ExpressDataService");
		return expressData;
	}

	public static BusinessDataService getBusinessData()
			throws MalformedURLException, RemoteException, NotBoundException {
		BusinessDataService businessData = (BusinessDataService) Naming.lookup("//" + address + "/BusinessDataService");
		return businessData;
	}

	// 财务人员相关数据
	public static AccountDataService getAccountData() throws MalformedURLException, RemoteException, NotBoundException {
		AccountDataService accountData = (AccountDataService) Naming.lookup("//" + address + "/AccountDataService");
		return accountData;
	}

	public static CollectionReceiptDataService getCollectionReceiptData()
			throws MalformedURLException, RemoteException, NotBoundException {
		CollectionReceiptDataService collectionData = (CollectionReceiptDataService) Naming
				.lookup("//" + address + "/CollectionReceiptDataService");
		return collectionData;
	}

	public static PaymentReceiptDataService getPaymentReceiptData()
			throws MalformedURLException, RemoteException, NotBoundException {
		PaymentReceiptDataService paymentData = (PaymentReceiptDataService) Naming
				.lookup("//" + address + "/PaymentReceiptDataService");
		return paymentData;
	}

	public static CostIncomeReceiptDataService getCostIncomeData()
			throws MalformedURLException, RemoteException, NotBoundException {
		CostIncomeReceiptDataService costIncomeData = (CostIncomeReceiptDataService) Naming
				.lookup("//" + address + "/CostIncomeReceiptDataService");
		return costIncomeData;
	}

	public static InitialStockDataService getInitialStockData()
			throws MalformedURLException, RemoteException, NotBoundException {
		InitialStockDataService initData = (InitialStockDataService) Naming
				.lookup("//" + address + "/InitialStockDataService");
		return initData;
	}

	// 中转中心
	public static IntermediateDataService getIntermediateData()
			throws MalformedURLException, RemoteException, NotBoundException {
		IntermediateDataService intermediateData = (IntermediateDataService) Naming
				.lookup("//" + address + "/IntermediateDataService");
		return intermediateData;
	}

	// 总经理
	public static BasicSalaryDataService getBasicSalaryData()
			throws MalformedURLException, RemoteException, NotBoundException {
		BasicSalaryDataService basicSalaryData = (BasicSalaryDataService) Naming
				.lookup("//" + address + "/BasicSalaryDataService");
		return basicSalaryData;
	}

	public static CityDistanceDataService getCityDistanceData()
			throws MalformedURLException, RemoteException, NotBoundException {
		CityDistanceDataService cityDistanceData = (CityDistanceDataService) Naming
				.lookup("//" + address + "/CityDistanceDataService");
		return cityDistanceData;
	}
	
	public static CostDataService getCostData()
			throws MalformedURLException, RemoteException, NotBoundException {
		CostDataService costDataData = (CostDataService) Naming
				.lookup("//" + address + "/CostDataService");
		return costDataData;
	}
	
	public static OrganizationDataService getOrganizationData()
			throws MalformedURLException, RemoteException, NotBoundException {
		OrganizationDataService organizationData = (OrganizationDataService) Naming
				.lookup("//" + address + "/OrganizationDataService");
		return organizationData;
	}
	
	public static PerWageDataService getPerWageData()
			throws MalformedURLException, RemoteException, NotBoundException {
		PerWageDataService perWageData = (PerWageDataService) Naming
				.lookup("//" + address + "/PerWageDataService");
		return perWageData;
	}
	
	//仓库管理员
	public static  GoodsDataService getGoodsData()
			throws MalformedURLException, RemoteException, NotBoundException {
		GoodsDataService goodsData = (GoodsDataService) Naming
				.lookup("//" + address + "/GoodsDataService");
		return goodsData;
	}
	
	public static RepertoryDataService getRepertoryData()
			throws MalformedURLException, RemoteException, NotBoundException {
		RepertoryDataService repertoryData = (RepertoryDataService) Naming
				.lookup("//" + address + "/RepertoryDataService");
		return repertoryData;
	}
	


}
