package po;

import type.ProfessionType;

public class BasicSalaryPO {

	private ProfessionType profession;
	private double basicSalary;
	
	public BasicSalaryPO(ProfessionType profession, double basicSalary){
		this.profession = profession;
		this.basicSalary = basicSalary;
	}
	
	public ProfessionType getProfession(){
		return this.profession;
	}
	
	public void setProfession(ProfessionType profession){
		this.profession = profession;
	}
	
	public double getBasicSalary(){
		return this.basicSalary;
	}
	
	public void setBasicSalary(double basicSalary){
		this.basicSalary = basicSalary;
	}
	
}
