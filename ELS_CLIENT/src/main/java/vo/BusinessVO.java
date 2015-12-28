package vo;

import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;

public class BusinessVO extends UserVO {

	public final String serviceTime;
	public final OrganizationVO organizationVO;

	public BusinessVO(String name, String ID, String password, ProfessionType professionType, String organizationID,
			SalaryPlanType salaryPlanType, AuthorityType authority, int grade, String serviceTime,
			OrganizationVO organizationVO) {
		super(name, ID, password, professionType, organizationID, salaryPlanType, authority, grade);

		this.serviceTime = serviceTime;
		this.organizationVO = organizationVO;

	}
}
