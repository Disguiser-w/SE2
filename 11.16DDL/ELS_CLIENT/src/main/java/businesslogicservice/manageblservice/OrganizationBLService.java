package businesslogicservice.manageblservice;

import vo.OrganizationVO;

public interface OrganizationBLService {
	public int addOrganization(OrganizationVO organizationvo);
	public int deleteOrganization(OrganizationVO organizationvo);
	public int modifyOrganization(OrganizationVO organizationvo);
	public OrganizationVO findOrganization(String organizationID);
	public int chooseDepartment(String userID, String organizationID);
}
