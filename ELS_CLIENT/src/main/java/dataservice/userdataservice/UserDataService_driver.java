package dataservice.userdataservice;

import dataservice.userdataservice.UserDataService_stub;
import po.UserPO;
import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

import java.rmi.RemoteException;

public class UserDataService_driver {

	public void drive(UserDataService_stub userDataService){
        userDataService.addUser(new UserPO("王梦娜", "KD-00001", "123456", ProfessionType.courier, "鼓楼营业厅", 
        		                        SalaryPlanType.countermanSalaryPlan, AuthorityType.lowest, 0));
        userDataService.deleteUser("KD-00001");
        userDataService.modifyUser(new UserPO("王梦娜", "KD-00001", "123456", ProfessionType.courier, "鼓楼营业厅", 
                						  SalaryPlanType.countermanSalaryPlan, AuthorityType.lowest, 0));
        userDataService.findUser("KD-00001");
        userDataService.showAllUsers();
    }
	
	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		UserDataService_stub user_data_service = new UserDataService_stub();
		UserDataService_driver driver = new UserDataService_driver();
		driver.drive(user_data_service);
	}
	
}
