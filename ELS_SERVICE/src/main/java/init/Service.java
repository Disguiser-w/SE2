package init;

import java.io.File;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

import javax.swing.JOptionPane;

import presentation.serviceui.ServiceMainFrame;
import testConnection.Test;
import testConnection.TestConnection;

import common.FileGetter;

import data.businessdata.BusinessData;
import data.expressdata.ExpressData;
import data.financedata.AccountData;
import data.financedata.CollectionReceiptData;
import data.financedata.CostIncomeReceiptData;
import data.financedata.InitialStockData;
import data.financedata.LogDiaryData;
import data.financedata.PaymentReceiptData;
import data.intermediatedata.IntermediateData;
import data.managedata.BasicSalaryData;
import data.managedata.CityDistanceData;
import data.managedata.CostData;
import data.managedata.OrganizationData;
import data.managedata.PerWageData;
import data.repertorydata.EnterRepertoryReceiptData;
import data.repertorydata.GoodsData;
import data.repertorydata.LeaveRepertoryReceiptData;
import data.repertorydata.RepertoryData;
import data.userdata.UserData;
import dataservice.businessdataservice.BusinessDataService;
import dataservice.expressdataservice.ExpressDataService;
import dataservice.financedataservice.AccountDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.InitialStockDataService;
import dataservice.financedataservice.LogDiaryDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import dataservice.intermediatedataservice.IntermediateDataService;
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

public class Service {
	private ExpressDataService expressData;
	private BusinessDataService businessData;
	private AccountDataService accountData;
	private CollectionReceiptDataService collectionData;
	private PaymentReceiptDataService paymentData;
	private CostIncomeReceiptDataService costincomeData;
	private InitialStockDataService initialData;
	private IntermediateDataService intermediateData;
	private UserDataService userData;
	private OrganizationDataService organizationData;
	private RepertoryDataService repertoryData;
	private EnterRepertoryReceiptDataService enterRepertoryReceiptData;
	private LeaveRepertoryReceiptDataService leaveRepertoryReceiptData;
	private GoodsDataService goodsData;
	private PerWageDataService perWageData;
	private BasicSalaryDataService basicSalaryData;
	private CityDistanceDataService cityDistanceData;
	private CostDataService costData;
	private LogDiaryDataService logDiaryData;
	private TestConnection test;

	private Remote reg;
	private String address;

	public Service() {
		File file = FileGetter.getFile("address");

		try {

			if (!file.exists()) {
				JOptionPane.showMessageDialog(null, "IP地址文件不存在,将使用本地地址，请检查Info目录下的address是否丢失");
				address = "localhost";
			} else {
				Scanner in = new Scanner(file);
				address = in.nextLine();
				in.close();
			}

			System.setProperty("java.rmi.server.hostname", address);
			reg = LocateRegistry.createRegistry(8888);
			test = new Test();
			Naming.bind("rmi://" + address + ":8888/TestConnection", test);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "请勿重复启动服务器", "", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			e.printStackTrace();
		}
	}

	public boolean startService() {

		try {

			expressData = new ExpressData();
			businessData = new BusinessData();
			accountData = new AccountData();
			collectionData = new CollectionReceiptData();
			paymentData = new PaymentReceiptData();
			costincomeData = new CostIncomeReceiptData();
			initialData = new InitialStockData();
			intermediateData = new IntermediateData();
			userData = new UserData();
			organizationData = new OrganizationData();
			repertoryData = new RepertoryData();
			enterRepertoryReceiptData = new EnterRepertoryReceiptData();
			leaveRepertoryReceiptData = new LeaveRepertoryReceiptData();
			goodsData = new GoodsData();
			perWageData = new PerWageData();
			basicSalaryData = new BasicSalaryData();
			cityDistanceData = new CityDistanceData();
			costData = new CostData();
			logDiaryData = new LogDiaryData();
			Test.isConnection = true;

			Naming.rebind("rmi://" + address + ":8888/ExpressDataService", expressData);
			Naming.rebind("rmi://" + address + ":8888/BusinessDataService", businessData);
			Naming.rebind("rmi://" + address + ":8888/AccountDataService", accountData);
			Naming.rebind("rmi://" + address + ":8888/CollectionReceiptDataService", collectionData);
			Naming.rebind("rmi://" + address + ":8888/PaymentReceiptDataService", paymentData);
			Naming.rebind("rmi://" + address + ":8888/CostIncomeReceiptDataService", costincomeData);
			Naming.rebind("rmi://" + address + ":8888/InitialStockDataService", initialData);
			Naming.rebind("rmi://" + address + ":8888/IntermediateDataService", intermediateData);
			Naming.rebind("rmi://" + address + ":8888/UserDataService", userData);
			Naming.rebind("rmi://" + address + ":8888/OrganizationDataService", organizationData);
			Naming.rebind("rmi://" + address + ":8888/RepertoryDataService", repertoryData);
			Naming.rebind("rmi://" + address + ":8888/EnterRepertoryReceiptDataService", enterRepertoryReceiptData);
			Naming.rebind("rmi://" + address + ":8888/LeaveRepertoryReceiptDataService", leaveRepertoryReceiptData);
			Naming.rebind("rmi://" + address + ":8888/GoodsDataService", goodsData);
			Naming.rebind("rmi://" + address + ":8888/PerWageDataService", perWageData);
			Naming.rebind("rmi://" + address + ":8888/BasicSalaryDataService", basicSalaryData);
			Naming.rebind("rmi://" + address + ":8888/CityDistanceDataService", cityDistanceData);
			Naming.rebind("rmi://" + address + ":8888/CostDataService", costData);
			Naming.rebind("rmi://" + address + ":8888/RepertoryDataService", repertoryData);
			Naming.rebind("rmi://" + address + ":8888/LogDiaryDataService", logDiaryData);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean stopService() {

		Test.isConnection = false;
		return true;
	}

	public static void main(String[] args) {
		new ServiceMainFrame();
	}
}
