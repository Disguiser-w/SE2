package po;

import java.io.Serializable;

import type.ProfessionType;

public class BasicSalaryPO implements Serializable {
	
	private static final long serialVersionUID = 141250152L;

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
