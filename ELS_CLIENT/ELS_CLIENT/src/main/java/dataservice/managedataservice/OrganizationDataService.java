package dataservice.managedataservice;

import po.UserPO;
import po.OrganizationPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OrganizationDataService extends Remote{
	
	public int addOrganization(OrganizationPO organizationpo) throws RemoteException;
	public int deleteOrganization(OrganizationPO organizationpo) throws RemoteException;
	public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException;
	public OrganizationPO findOrganization(String OrganizationID) throws RemoteException;
	
	public int modifyUser(UserPO userpo) throws RemoteException;
	
}
