package vo;

public class BusinessVO {
	public final String name;
	public final String ID;
	public final String serviceTime;
	public final OrganizationVO organizationVO;

	public BusinessVO(String name, String ID, String serviceTime, OrganizationVO organizationVO) {
		this.name = name;
		this.ID = ID;
		this.serviceTime = serviceTime;
		this.organizationVO = organizationVO;

	}
}
