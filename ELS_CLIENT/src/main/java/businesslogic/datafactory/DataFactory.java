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
import dataservice.logdiarydataservice.LogDiaryDataService;
import dataservice.managedataservice.BasicSalaryDataService;
import dataservice.managedataservice.CityDistanceDataService;
import dataservice.managedataservice.CostDataService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.managedataservice.PerWageDataService;
import dataservice.repertorydataservice.EnterRepertoryReceiptDataService;
import dataservice.repertorydataservice.GoodsDataService;
import dataservice.repertorydataservice.LeaveRepertoryReceiptDataService;
import dataservice.repertorydataservice.RepertoryDataService;
import dataservice.userdataservice.UserDataService;

public class DataFactory {
	public static String address;
	private static ExpressDataService expressData;
	private static BusinessDataService businessData;
	private static AccountDataService accountData;
	private static CollectionReceiptDataService collectionData;
	private static PaymentReceiptDataService paymentData;
	private static CostIncomeReceiptDataService costincomeData;
	private static InitialStockDataService initialData;
	private static IntermediateDataService intermediateData;
	private static UserDataService userData;
	private static OrganizationDataService organizationData;
	private static RepertoryDataService repertoryData;
	private static EnterRepertoryReceiptDataService enterRepertoryReceiptData;
	private static LeaveRepertoryReceiptDataService leaveRepertoryReceiptData;
	private static GoodsDataService goodsData;
	private static PerWageDataService perWageData;
	private static BasicSalaryDataService basicSalaryData;
	private static CityDistanceDataService cityDistanceData;
	private static CostDataService costData;
	private static LogDiaryDataService logDiaryData;

	static {
		try {

			Scanner in = new Scanner(FileGetter.getFile("address"));
			address = in.next();
			in.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ExpressDataService getExpressData() throws MalformedURLException, RemoteException, NotBoundException {
		if (expressData != null) {
			return expressData;
		}
		expressData = (ExpressDataService) Naming.lookup("//" + address + "/ExpressDataService");
		return expressData;
	}

	public static BusinessDataService getBusinessData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (businessData != null) {
			return businessData;
		}
		businessData = (BusinessDataService) Naming.lookup("//" + address + "/BusinessDataService");
		return businessData;
	}

	// 财务人员相关数据
	public static AccountDataService getAccountData() throws MalformedURLException, RemoteException, NotBoundException {
		if (accountData != null) {
			return accountData;
		}
		accountData = (AccountDataService) Naming.lookup("//" + address + "/AccountDataService");
		return accountData;
	}

	public static CollectionReceiptDataService getCollectionReceiptData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (collectionData != null) {
			return collectionData;
		}
		collectionData = (CollectionReceiptDataService) Naming.lookup("//" + address + "/CollectionReceiptDataService");
		return collectionData;
	}

	public static PaymentReceiptDataService getPaymentReceiptData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (paymentData != null) {
			return paymentData;
		}
		paymentData = (PaymentReceiptDataService) Naming.lookup("//" + address + "/PaymentReceiptDataService");
		return paymentData;
	}

	public static CostIncomeReceiptDataService getCostIncomeData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (costincomeData != null) {
			return costincomeData;
		}
		costincomeData = (CostIncomeReceiptDataService) Naming.lookup("//" + address + "/CostIncomeReceiptDataService");
		return costincomeData;
	}

	public static InitialStockDataService getInitialStockData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (initialData != null) {
			return initialData;
		}
		initialData = (InitialStockDataService) Naming.lookup("//" + address + "/InitialStockDataService");
		return initialData;
	}

	// 中转中心
	public static IntermediateDataService getIntermediateData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (intermediateData != null) {
			return intermediateData;
		}
		intermediateData = (IntermediateDataService) Naming.lookup("//" + address + "/IntermediateDataService");
		return intermediateData;
	}

	// 总经理
	public static BasicSalaryDataService getBasicSalaryData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (basicSalaryData != null) {
			return basicSalaryData;
		}
		basicSalaryData = (BasicSalaryDataService) Naming.lookup("//" + address + "/BasicSalaryDataService");
		return basicSalaryData;
	}

	public static CityDistanceDataService getCityDistanceData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (cityDistanceData != null) {
			return cityDistanceData;
		}
		cityDistanceData = (CityDistanceDataService) Naming.lookup("//" + address + "/CityDistanceDataService");
		return cityDistanceData;
	}

	public static CostDataService getCostData() throws MalformedURLException, RemoteException, NotBoundException {
		if (costData != null) {
			return costData;
		}
		costData = (CostDataService) Naming.lookup("//" + address + "/CostDataService");
		return costData;
	}

	public static OrganizationDataService getOrganizationData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (organizationData != null) {
			return organizationData;
		}
		organizationData = (OrganizationDataService) Naming.lookup("//" + address + "/OrganizationDataService");
		return organizationData;
	}

	public static PerWageDataService getPerWageData() throws MalformedURLException, RemoteException, NotBoundException {
		if (perWageData != null) {
			return perWageData;
		}
		perWageData = (PerWageDataService) Naming.lookup("//" + address + "/PerWageDataService");
		return perWageData;
	}

	public static UserDataService getUserData() throws MalformedURLException, RemoteException, NotBoundException {

		if (userData != null) {
			return userData;
		}
		userData = (UserDataService) Naming.lookup("//" + address + "/UserDataService");
		return userData;
	}

	// 仓库管理员
	public static GoodsDataService getGoodsData() throws MalformedURLException, RemoteException, NotBoundException {

		if (goodsData != null) {
			return goodsData;
		}
		goodsData = (GoodsDataService) Naming.lookup("//" + address + "/GoodsDataService");
		return goodsData;
	}

	public static RepertoryDataService getRepertoryData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (repertoryData != null) {
			return repertoryData;
		}

		repertoryData = (RepertoryDataService) Naming.lookup("//" + address + "/RepertoryDataService");
		return repertoryData;
	}

	public static EnterRepertoryReceiptDataService getEnterRepertoryReceiptData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (enterRepertoryReceiptData != null) {
			return enterRepertoryReceiptData;
		}
		enterRepertoryReceiptData = (EnterRepertoryReceiptDataService) Naming
				.lookup("//" + address + "/EnterRepertoryReceiptDataService");
		return enterRepertoryReceiptData;
	}

	public static LeaveRepertoryReceiptDataService getLeaveRepertoryReceiptData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (leaveRepertoryReceiptData != null) {
			return leaveRepertoryReceiptData;
		}
		leaveRepertoryReceiptData = (LeaveRepertoryReceiptDataService) Naming
				.lookup("//" + address + "/LeaveRepertoryReceiptDataService");
		return leaveRepertoryReceiptData;
	}

	// 系统日志
	public static LogDiaryDataService getLogDiaryData()
			throws MalformedURLException, RemoteException, NotBoundException {
		if (logDiaryData != null) {
			return logDiaryData;
		}

		logDiaryData = (LogDiaryDataService) Naming.lookup("//" + address + "/LogDiaryDataService");
		return logDiaryData;
	}

}
