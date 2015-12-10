package common;

import java.rmi.Naming;

import po.UserPO;
import dataservice.userdataservice.UserDataService;

public class LiziTestClient {
	public static void main(String[] args) {
		try {
			String[] list = Naming.list("rmi://localhost:8888");
			
			UserDataService userData = (UserDataService) Naming.lookup(list[1]);
			
			System.out.println("successful");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
