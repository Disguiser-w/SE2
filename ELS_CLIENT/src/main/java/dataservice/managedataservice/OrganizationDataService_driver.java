package dataservice.managedataservice;


import dataservice.managedataservice.OrganizationDataService_stub;
import po.OrganizationPO;
import po.UserPO;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import type.OrganizationType;

import java.rmi.RemoteException;

public class OrganizationDataService_driver {

	public void drive(OrganizationDataService_stub OrganizationDataService) throws RemoteException {
    	OrganizationDataService.addOrganization(new OrganizationPO(OrganizationType.businessHall, "025000", "鼓楼营业厅",null));
        OrganizationDataService.deleteOrganization(new OrganizationPO(OrganizationType.businessHall, "025000", "鼓楼营业厅",null));
        OrganizationDataService.modifyOrganization(new OrganizationPO(OrganizationType.businessHall, "025000", "鼓楼营业厅",null));
        OrganizationDataService.findOrganization("025000");
        OrganizationDataService.modifyUser(new UserPO("王梦娜", "KD-00001", "123456", ProfessionType.courier, "鼓楼营业厅", 
  									SalaryPlanType.countermanSalaryPlan, AuthorityType.lowest, 0));
	}
	
	public static void main(String[] args) throws RemoteException {
		// TODO 自动生成的方法存根
		OrganizationDataService_stub organization_data_service = new OrganizationDataService_stub();
		OrganizationDataService_driver driver = new OrganizationDataService_driver();
		driver.drive(organization_data_service);
	}
	
}
