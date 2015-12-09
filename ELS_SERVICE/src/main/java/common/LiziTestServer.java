package common;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import dataservice.userdataservice.UserDataService;
import dataservice.repertorydataservice.RepertoryDataService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.managedataservice.PerWageDataService;
import dataservice.managedataservice.BasicSalaryDataService;
import dataservice.managedataservice.CityDistanceDataService;
import dataservice.managedataservice.CostDataService;
import data.userdata.UserData;
import data.repertorydata.RepertoryData;
import data.managedata.OrganizationData;
import data.managedata.PerWageData;
import data.managedata.BasicSalaryData;
import data.managedata.CityDistanceData;
import data.managedata.CostData;

public class LiziTestServer {

	public static void main(String[] args){
		try{
			UserDataService userData = new UserData();
			OrganizationDataService organizationData = new OrganizationData();
			PerWageDataService perWageData = new PerWageData();
			BasicSalaryDataService basicSalaryData = new BasicSalaryData();
			CityDistanceDataService cityDistanceData = new CityDistanceData();
			CostDataService costData = new CostData();
			RepertoryDataService repertoryData = new RepertoryData();
			
			LocateRegistry.createRegistry(8888);
			Naming.rebind("rmi://localhost:8888/UserDataService", userData);
			Naming.rebind("rmi://localhost:8888/OrganizationDataService", organizationData);
			Naming.rebind("rmi://localhost:8888/PerWageDataService", perWageData);
			Naming.rebind("rmi://localhost:8888/BasicSalaryDataService", basicSalaryData);
			Naming.rebind("rmi://localhost:8888/CityDistanceDataService", cityDistanceData);
			Naming.rebind("rmi://localhost:8888/CostDataService", costData);
			Naming.rebind("rmi://localhost:8888/RepertoryDataService", repertoryData);
			
			System.out.println("User Service start");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
