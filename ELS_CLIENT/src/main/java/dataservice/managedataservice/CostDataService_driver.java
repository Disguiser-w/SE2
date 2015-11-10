package dataservice.managedataservice;

import java.rmi.RemoteException;

import po.CostPO;
import type.ExpressType;

public class CostDataService_driver {

	public void drive(CostDataService_stub CostDataService) throws  RemoteException{
		CostDataService.addCost(new CostPO(ExpressType.standard, 23));
		CostDataService.deleteCost(new CostPO(ExpressType.standard, 23));
		CostDataService.modifyCost(new CostPO(ExpressType.standard, 23));
		CostDataService.findCost(ExpressType.standard);
	}
	
	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		CostDataService_stub cost_data_service = new CostDataService_stub();
		CostDataService_driver driver = new CostDataService_driver();
		driver.drive(cost_data_service);
	}
	
}
