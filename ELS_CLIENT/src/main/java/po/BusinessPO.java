package po;

import java.io.Serializable;

import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;

public class BusinessPO extends UserPO implements Serializable{

	private String serviceTime;
	private OrganizationPO organizationPO;

	public BusinessPO(String name, String ID, String password,ProfessionType professionType, String organizationID,
			SalaryPlanType salaryPlanType, AuthorityType authority, int grade,String serviceTime,
			OrganizationPO organizationPO) {
		super(name, ID, password, professionType, organizationID, salaryPlanType, authority, grade);

		this.serviceTime = serviceTime;
		this.organizationPO = organizationPO;

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
