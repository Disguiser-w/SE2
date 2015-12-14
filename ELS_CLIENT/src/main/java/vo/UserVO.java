package vo;

import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

public class UserVO {
	public String userName;
	public String userID;
	public String password;
	public ProfessionType profession;
	public String organization;
	public SalaryPlanType salaryPlan;
	public AuthorityType authority;
	public int grades;

	public UserVO(String userName,String userID,String password, ProfessionType profession,String organization, 
//                       姓名                         编号                    密码                                           职业类型                            所属机构名 
			SalaryPlanType salaryPlan, AuthorityType authority, int grades){
//		                                      薪水策略类型                             权限类型		绩点（快递员绩点即为本月派件总数，司机绩点即为本月出车次数）
        
		this.userName = userName;
		this.userID = userID;
		this.password  = password;
		this.profession = profession;
		this.organization = organization;
		this.salaryPlan = salaryPlan;
		this.authority = authority;
		this.grades = grades;
	}
	
}
