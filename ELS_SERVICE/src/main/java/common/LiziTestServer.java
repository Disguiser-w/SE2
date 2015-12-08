package common;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import dataservice.userdataservice.UserDataService;
import dataservice.repertorydataservice.RepertoryDataService;
import data.userdata.UserData;
import data.repertorydata.RepertoryData;

public class LiziTestServer {

	public static void main(String[] args){
		try{
			UserDataService userData = new UserData();
			RepertoryDataService repertoryData = new RepertoryData();
			
			LocateRegistry.createRegistry(8888);
			Naming.rebind("rmi://localhost:8888/UserDataService", userData);
			Naming.rebind("rmi://localhost:8888/RepertoryDataService", repertoryData);
			System.out.println("User Service start");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
