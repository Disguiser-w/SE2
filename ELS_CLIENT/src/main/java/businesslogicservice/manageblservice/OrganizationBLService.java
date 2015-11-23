package businesslogicservice.manageblservice;

import java.rmi.RemoteException;

import vo.OrganizationVO;

public interface OrganizationBLService {
	public int addOrganization(OrganizationVO organizationvo) throws RemoteException;
	public int deleteOrganization(String organizationID) throws RemoteException;
	public int modifyOrganization(OrganizationVO organizationvo) throws RemoteException;
	public OrganizationVO findOrganization(String organizationID) throws RemoteException;
	public int chooseDepartment(String userID, String organizationID) throws RemoteException;
}
