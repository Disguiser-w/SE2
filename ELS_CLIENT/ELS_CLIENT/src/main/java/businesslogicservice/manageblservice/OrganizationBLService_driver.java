package businesslogicservice.manageblservice;

import type.OrganizationType;
import vo.OrganizationVO;

public class OrganizationBLService_driver {

	public void drive(OrganizationBLService_stub organizationBLService){
		organizationBLService.addOrganization(new OrganizationVO(OrganizationType.businessHall, "025000", "鼓楼营业厅", null));
        organizationBLService.deleteOrganization(new OrganizationVO(OrganizationType.businessHall, "025000", "鼓楼营业厅", null));
        organizationBLService.modifyOrganization(new OrganizationVO(OrganizationType.businessHall, "025000", "鼓楼营业厅", null));
        organizationBLService.findOrganization("025000");
        organizationBLService.chooseDepartment("KD-00001", "025000");
	}
	
	public static void main(String[] args){
		OrganizationBLService_stub organizationBLService = new OrganizationBLService_stub();
		OrganizationBLService_driver driver = new OrganizationBLService_driver();
        driver.drive(organizationBLService);
    }
	
}
