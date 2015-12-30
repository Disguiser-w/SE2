package dataservice.managedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.OrganizationPO;

public interface OrganizationDataService extends Remote{
	
	public int addOrganization(OrganizationPO organizationpo) throws RemoteException;
	public int deleteOrganization(String organizationID) throws RemoteException;
	public OrganizationPO findOrganizationByID(String organizationID) throws RemoteException;
	public ArrayList<OrganizationPO> findOrganizationByKeyword(String keyword) throws RemoteException;
	public ArrayList<OrganizationPO> showAllOrganizations() throws RemoteException;
	public ArrayList<String> getBelongingPlaces (String city) throws RemoteException;
	
}
