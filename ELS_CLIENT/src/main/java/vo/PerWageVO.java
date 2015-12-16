package vo;

import type.ProfessionType;

public class PerWageVO {
	
	public ProfessionType profession;
	public double perWage;
	
	public PerWageVO(ProfessionType profession, double perWage){
		this.profession = profession;
		this.perWage = perWage;
	}
	
}
