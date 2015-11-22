package dataservice.managedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.OrganizationPO;
import po.UserPO;

public interface OrganizationDataService extends Remote{
	
	public int addOrganization(OrganizationPO organizationpo) throws RemoteException;
	public int deleteOrganization(String organizationID) throws RemoteException;
	public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException;
	public OrganizationPO findOrganization(String OrganizationID) throws RemoteException;
	
	public int modifyUser(UserPO userpo) throws RemoteException;
	
}
