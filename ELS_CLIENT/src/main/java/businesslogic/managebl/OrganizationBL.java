package businesslogic.managebl;

import businesslogicservice.magageblservice.OrganizationBLService;
import vo.OrganizationVO;

public class OrganizationBL implements OrganizationBLService{
	
	public int addOrganization(OrganizationVO organizationvo) {
		// TODO 自动生成的方法存根
		System.out.println("Add organization succeed!");
		return 0;
	}

    public int deleteOrganization(OrganizationVO organizationvo) {
        // TODO 自动生成的方法存根
        System.out.println("Delete organization succeed!");
        return 0;
    }
		
    public int modifyOrganization(OrganizationVO organizationvo) {
        // TODO 自动生成的方法存根
        System.out.println("Modify organization succeed!");
        return 0;
    }
 
    public OrganizationVO findOrganization(String organizationID) {
        // TODO 自动生成的方法存根
        System.out.println("Find organization succeed!");
        return null;
    }
    
    public int chooseDepartment(String userID, String organizationID){
    	// TODO 自动生成的方法存根
        System.out.println("Choose department succeed!");
        return 0;
    }
    
}
