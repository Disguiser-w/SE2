package vo;

public class IntermediateVO {
	public final OrganizationVO organization;

	public final String name;
	public final String ID;

	public IntermediateVO(OrganizationVO organization, String name, String ID) {
		this.organization = organization;
		this.name = name;
		this.ID = ID;
	}
}
