package dataservice.managedataservice;

import dataservice.managedataservice.PerWageDataService_stub;
import po.PerWagePO;
import type.ProfessionType;

import java.rmi.RemoteException;

public class PerWageDataService_driver {
	public void drive(PerWageDataService_stub PerWageDataService) throws RemoteException {
		PerWageDataService.addPerWage(new PerWagePO(ProfessionType.courier, 0.5));
		PerWageDataService.deletePerWage(new PerWagePO(ProfessionType.courier, 0.5));
		PerWageDataService.modifyPerWage(new PerWagePO(ProfessionType.courier, 0.5));
		PerWageDataService.findPerWage(ProfessionType.courier);
	}
	
	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		PerWageDataService_stub perwage_data_service = new PerWageDataService_stub();
		PerWageDataService_driver driver = new PerWageDataService_driver();
		driver.drive(perwage_data_service);
	}
	
}
