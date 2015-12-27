package vo;

import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;

public class IntermediateVO extends UserVO {
	public IntermediateVO(String name, String ID, String password,
			ProfessionType professionType, String organizationID,
			SalaryPlanType salaryPlanType, AuthorityType authorityType,
			int grades) {
		super(name, ID, password, ProfessionType.intermediateCenterCounterman,
				organizationID, SalaryPlanType.basicStaffSalaryPlan,
				AuthorityType.lowest, grades);
	}
}
