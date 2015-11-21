package dataservice.managedataservice;

import dataservice.managedataservice.OrganizationDataService;
import po.OrganizationPO;
import po.UserPO;
import type.OrganizationType;

import java.rmi.RemoteException;

public class OrganizationDataService_stub implements OrganizationDataService{
	
	public int addOrganization(OrganizationPO organizationpo){
		// TODO 自动生成的方法存根
		System.out.println("Add organization succeed!");
		return 0;
		}

    public int deleteOrganization(String organizationID){
        // TODO 自动生成的方法存根
        System.out.println("Delete organization succeed!");
        return 0;
    }
		
    public int modifyOrganization(OrganizationPO organizationpo){
        // TODO 自动生成的方法存根
        System.out.println("Modify organization succeed!");
        return 0;
    }
 
    public OrganizationPO findOrganization(String OrganizationID){
        // TODO 自动生成的方法存根
        System.out.println("Find organization succeed!");
        return new OrganizationPO(OrganizationType.businessHall, "025000", "鼓楼营业厅",null);
    }
    
    public int modifyUser(UserPO userpo){
    	// TODO 自动生成的方法存根
        System.out.println("Modify User succeed!");
        return 0;
    }

}
