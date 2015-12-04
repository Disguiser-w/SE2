package dataservice.managedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.OrganizationPO;

public class OrganizationDataService_stub implements OrganizationDataService{

	public int addOrganization(OrganizationPO organizationpo) throws RemoteException{
		System.out.println("Add organization succeed!");
		return 0;
	}
	
    public int deleteOrganization(String organizationID) throws RemoteException{
		System.out.println("Delete organization succeed!");
		return 0;
	}
    
    public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException{
		System.out.println("Modify organization succeed!");
		return 0;
	}
    
    public OrganizationPO findOrganization(String organizationID) throws RemoteException{
		System.out.println("Find organization succeed!");
		return null;
	}
    
    public ArrayList<OrganizationPO> showAllOrganizations() throws RemoteException{
		System.out.println("Show all organization succeed!");
		return null;
	}
    
    public ArrayList<String> getBelongingPlaces (String city) throws RemoteException{
    	System.out.println("Get all belonging places succeed!");
		return null;
    }
}
