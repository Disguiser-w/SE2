package dataservice.managedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.OrganizationPO;
import po.UserPO;

public interface OrganizationDataService extends Remote{
	
	public int addOrganization(OrganizationPO organizationpo) throws RemoteException;
	public int deleteOrganization(String organizationID) throws RemoteException;
	public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException;
	public OrganizationPO findOrganization(String OrganizationID) throws RemoteException;
	
	public int modifyUser(UserPO userpo) throws RemoteException;
	//期初建账需要所有的机构信息
	public ArrayList<OrganizationPO> getOrganizations();
	
}
