package businesslogic.managebl.controller;

import java.util.ArrayList;

import vo.OrganizationVO;
import businesslogic.managebl.OrganizationBL;

public class OrganizationController {

	private OrganizationBL organization;
	
	public OrganizationController(){
		organization = new OrganizationBL();
	}
	
	public int addOrganization(OrganizationVO organizationvo){
		return organization.addOrganization(organizationvo);
	}
	
	public int deleteOrganization(String organzationID){
		return organization.deleteOrganization(organzationID);
	}
	
	public int modifyOrganization(OrganizationVO organizationvo){
		return organization.modifyOrganization(organizationvo);
	}
	
	public OrganizationVO findOrganization(String organzationID){
		return organization.findOrganization(organzationID);
	}
	
	public ArrayList<OrganizationVO> showAllOrganizations(){
		return organization.showAllOrganizations();
	}
	
}
