package po;

public class IntermediatePO {
	private OrganizationPO organization;

	private String name;
	private String ID;

	public IntermediatePO(OrganizationPO organization, String name, String ID) {
		this.organization = organization;
		this.name = name;
		this.ID = ID;
	}

	public OrganizationPO getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationPO organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
}
