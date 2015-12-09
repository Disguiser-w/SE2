package dataservice.managedataservice;

import po.OrganizationPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OrganizationDataService extends Remote{
	
	public int addOrganization(OrganizationPO organizationpo) throws RemoteException;
	public int deleteOrganization(String OrganizationID) throws RemoteException;
	public int modifyOrganization(OrganizationPO organizationpo) throws RemoteException;
	public OrganizationPO findOrganization(String OrganizationID) throws RemoteException;
	public ArrayList<OrganizationPO> showAllOrganizations() throws RemoteException;
	public ArrayList<String> getBelongingPlaces (String city) throws RemoteException;
}
