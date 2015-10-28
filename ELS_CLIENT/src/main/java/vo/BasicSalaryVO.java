package vo;

import type.ProfessionType;

public class BasicSalaryVO {
	public final ProfessionType profession;
	public final double basicSalary;

	public BasicSalaryVO(ProfessionType profession, double basicSalary) {
		this.profession = profession;
		this.basicSalary = basicSalary;
	}
}
