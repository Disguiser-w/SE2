package po;

import java.io.Serializable;

import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;

public class IntermediatePO extends UserPO implements Serializable {
	public IntermediatePO(String userName, String userID,
			ProfessionType profession, String organization,
			SalaryPlanType salaryPlan, AuthorityType authority, int grades) {
		super(userName, userID, profession, organization, salaryPlan,
				authority, grades);
		// TODO 自动生成的构造函数存根
	}

}
