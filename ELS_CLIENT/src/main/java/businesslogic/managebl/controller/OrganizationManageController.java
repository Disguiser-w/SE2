package businesslogic.managebl.controller;

import java.util.ArrayList;

import businesslogic.managebl.OrganizationBL;
import businesslogicservice.manageblservice.OrganizationBLService;
import vo.OrganizationVO;

public class OrganizationManageController implements OrganizationBLService{

	private OrganizationBL organizationBL;
	
	public OrganizationManageController(){
		organizationBL = new OrganizationBL();
	}
	
	public int addOrganization(OrganizationVO organizationvo){
		return organizationBL.addOrganization(organizationvo);
	}
	
	public int deleteOrganization(String organzationID){
		return organizationBL.deleteOrganization(organzationID);
	}
	
	public int modifyOrganization(OrganizationVO organizationvo){
		return organizationBL.modifyOrganization(organizationvo);
	}
	
	public OrganizationVO findOrganization(String organzationID){
		return organizationBL.findOrganization(organzationID);
	}
	
	public ArrayList<OrganizationVO> findOrganizationByKeyword(String keyword){
		return organizationBL.findOrganizationByKeyword(keyword);
	}
	
	public ArrayList<OrganizationVO> showAllOrganizations(){
		return organizationBL.showAllOrganizations();
	}

	public int chooseDepartment(String userID, String organizationID){
		return organizationBL.chooseDepartment(userID, organizationID);
	}

}
