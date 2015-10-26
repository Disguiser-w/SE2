package dataservice.managedataservice;

import java.rmi.RemoteException;

import po.BasicSalaryPO;
import po.PerWagePO;
import type.ProfessionType;

public class BasicSalaryDataService_driver {

	public void drive(BasicSalaryDataService_stub BasicSalaryDataService) throws RemoteException {
		BasicSalaryDataService.addBasicSalary(new BasicSalaryPO(ProfessionType.courier, 0.5));
		BasicSalaryDataService.deleteBasicSalary(new BasicSalaryPO(ProfessionType.courier, 0.5));
		BasicSalaryDataService.modifyBasicSalary(new BasicSalaryPO(ProfessionType.courier, 0.5));
		BasicSalaryDataService.findBasicSalary(ProfessionType.courier);
	}
	
	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		BasicSalaryDataService_stub perwage_data_service = new BasicSalaryDataService_stub();
		BasicSalaryDataService_driver driver = new BasicSalaryDataService_driver();
		driver.drive(perwage_data_service);
	}
}
