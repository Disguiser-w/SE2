package businesslogic.managebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.OrganizationPO;
import po.RepertoryPO;
import po.UserPO;
import businesslogicservice.manageblservice.OrganizationBLService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.userdataservice.UserDataService;
import type.OrganizationType;
import vo.OrganizationVO;
import vo.RepertoryVO;

public class OrganizationBL implements OrganizationBLService{
	
	public OrganizationDataService odService;
	public UserDataService udService;
	
	public int addOrganization (OrganizationVO organizationvo){
		try{
			OrganizationPO neworganizationpo = organizationVOToPO(organizationvo);
			return(odService.addOrganization(neworganizationpo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int deleteOrganization(String organizationID) throws RemoteException{
		try{
			return(odService.deleteOrganization(organizationID));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int modifyOrganization(OrganizationVO organizationvo) throws RemoteException{
		try{
			OrganizationPO organizationpo = organizationVOToPO(organizationvo);
			return(odService.modifyOrganization(organizationpo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
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
	
	
	/*--------------------------------------------------Test Part---------------------------------------------------*/ 
    
    /*------------------------------------- Test server whether can normally work ----------------------------------*/
	
	public static void main(String[] args){
		try {
			OrganizationDataService organizationData = (OrganizationDataService)Naming.lookup("rmi://172.25.132.40:6006/OrganizationDataService");
			
			ArrayList<OrganizationPO> organizationList0 = organizationData.showAllOrganizations();
			for(OrganizationPO organization:organizationList0)
				System.out.println(("ID: "+organization.getOrganizationID()+", Name: "+organization.getName()));

			organizationData.addOrganization(new OrganizationPO(OrganizationType.businessHall, "012000", "南极营业厅"));
			
			ArrayList<OrganizationPO> organizationList1 = organizationData.showAllOrganizations();
			for(OrganizationPO organization:organizationList1)
				System.out.println(("ID: "+organization.getOrganizationID()+", Name: "+organization.getName()));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
