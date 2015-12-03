package po;

import java.io.Serializable;

public class BusinessPO implements Serializable{
	private String name;
	private String ID;
	private String serviceTime;
	private OrganizationPO organizationPO;

	public BusinessPO(String name, String ID, String serviceTime, OrganizationPO organizationPO) {
		this.name = name;
		this.ID = ID;
		this.serviceTime = serviceTime;
		this.organizationPO = organizationPO;

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

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public OrganizationPO getOrganizationPO() {
		return organizationPO;
	}

	public void setOrganizationPO(OrganizationPO organizationPO) {
		this.organizationPO = organizationPO;
	}
}
