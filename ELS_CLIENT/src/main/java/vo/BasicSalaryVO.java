package vo;

import type.ProfessionType;

public class BasicSalaryVO {
	
	public ProfessionType profession;
	public double basicSalary;

	public BasicSalaryVO(ProfessionType profession, double basicSalary) {
		this.profession = profession;
		this.basicSalary = basicSalary;
	}
	
}
