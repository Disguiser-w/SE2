package businesslogic.managebl;

import java.rmi.RemoteException;

import po.OrganizationPO;
import po.RepertoryPO;
import po.UserPO;
import businesslogicservice.manageblservice.OrganizationBLService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.userdataservice.UserDataService;
import vo.OrganizationVO;
import vo.RepertoryVO;

public class OrganizationBL implements OrganizationBLService{
	
	public OrganizationDataService odService;
	public UserDataService udService;
	
	public int addOrganization (OrganizationVO organizationvo){
		try{
			String organizationID = organizationvo.getOrganizationID();
			OrganizationPO organizationpo = odService.findOrganization(organizationID);
			if(organizationpo.equals(null)){
				OrganizationPO neworganizationpo = organizationVOToPO(organizationvo);
				odService.addOrganization(neworganizationpo);
				return 0;
			}
			else
				return 1;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int deleteOrganization(String organizationID) throws RemoteException{
		try{
			odService.deleteOrganization(organizationID);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int modifyOrganization(OrganizationVO organizationvo) throws RemoteException{
		try{
			OrganizationPO organizationpo = organizationVOToPO(organizationvo);
			odService.modifyOrganization(organizationpo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public OrganizationVO findOrganization(String organizationID){
		try{
			OrganizationPO organizationpo = odService.findOrganization(organizationID);
			return organizationPOToVO(organizationpo);
		}catch(RemoteException exception){
			exception.printStackTrace();
			return null;
		}
	}
	
	public int chooseDepartment(String userID, String organizationID){
		try{
			UserPO userpo = udService.findUser(userID);
			userpo.setOrganization(organizationID);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
		
	}
	
	public static RepertoryVO repertoryPOToVO(RepertoryPO repertorypo){
		return new RepertoryVO(repertorypo.getRepertoryID(),repertorypo.getOwnerID(),repertorypo.getMaxRow(),repertorypo.getMaxShelf(), repertorypo.getMaxDigit(),repertorypo.getWarningRatio(), repertorypo.getStockNumArray());
	}
	
	public static RepertoryPO repertoryVOToPO(RepertoryVO repertoryvo){
		return new RepertoryPO(repertoryvo.getRepertoryID(),repertoryvo.getOwnerID(),repertoryvo.getMaxRow(),repertoryvo.getMaxShelf(), repertoryvo.getMaxDigit(),repertoryvo.getWarningRatio(),repertoryvo.getStockNumArray());
	}
	
	public static OrganizationVO organizationPOToVO(OrganizationPO organizationpo){
		return new OrganizationVO(organizationpo.getCategory(),organizationpo.getOrganizationID(),organizationpo.getName(),repertoryPOToVO(organizationpo.getRepertory()));
	}
	
	public static OrganizationPO organizationVOToPO(OrganizationVO organizationvo){
		return new OrganizationPO(organizationvo.getCategory(),organizationvo.getOrganizationID(),organizationvo.getName(),repertoryVOToPO(organizationvo.getRepertory()));
	}
	
}
