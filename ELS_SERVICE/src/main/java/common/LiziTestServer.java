package common;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import dataservice.userdataservice.UserDataService;
import data.userdata.UserData;

public class LiziTestServer {

	public static void main(String[] args){
		try{
			UserDataService userData = new UserData();
			
			LocateRegistry.createRegistry(8888);
			Naming.rebind("rmi://localhost:8888/UserDataService", userData);
			System.out.println("User Service start");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
