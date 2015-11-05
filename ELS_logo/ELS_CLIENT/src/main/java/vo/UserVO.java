package vo;

import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

public class UserVO {
	private String userName;
	private String userID;
	private String password;
	private ProfessionType profession;
	private String organization;
	private SalaryPlanType salaryPlan;
	private AuthorityType authority;
	private int grades;

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
		
	public String getName() {
		return this.userName;
	}

	public String getID() {
		return this.userID;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassWord(String password){
		this.password = password;
	}
	
	public ProfessionType getProfession(){
		return this.profession;
	}
	
	public void setProfession(ProfessionType profession){
		this.profession = profession;
	}
	
	public String getOrganization(){
		return this.organization;
	}
	
	public void setOrganization(String organization){
		this.organization = organization;
	}
	
	public SalaryPlanType getSalaryPlan(){
		return this.salaryPlan;
	}
	
	public void setSalatyPlan(SalaryPlanType salaryPlan){
		this.salaryPlan = salaryPlan;
	}
	
	public AuthorityType getAuthority(){
		return this.authority;
	}
	
	public void setAuthority(AuthorityType authority){
		this.authority = authority;
	}
	
	public int getGrades(){
		return this.grades;
	}
	
	public void setGrades(int grades){
		this.grades = grades;
	}
	
}
