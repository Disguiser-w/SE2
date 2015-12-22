package po;

import java.io.Serializable;

import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;

public class UserPO implements Serializable {

	private static final long serialVersionUID = 141250147L;
	
	private String userName;
	private String userID;
	private String password;
	private ProfessionType profession;
	private String organization;
	private SalaryPlanType salaryPlan;
	private AuthorityType authority;
	private int grades;

	public UserPO(String userName ,String userID, ProfessionType profession, String organization, 
			SalaryPlanType salaryPlan, AuthorityType authority, int grades){
        
		this.userName = userName;
		this.userID = userID;
		this.profession = profession;
		this.organization = organization;
		this.salaryPlan = salaryPlan;
		this.authority = authority;
		this.grades = grades;
	}
	
	public UserPO(String userName, String userID, String password, ProfessionType profession, String organization, 
			SalaryPlanType salaryPlan, AuthorityType authority, int grades){
        
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

	public String getUserID() {
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
