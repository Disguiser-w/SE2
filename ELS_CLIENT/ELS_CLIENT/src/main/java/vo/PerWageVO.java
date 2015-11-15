package vo;

import type.ProfessionType;

public class PerWageVO {
	private ProfessionType profession;
	private double perWage;
	
	public PerWageVO(ProfessionType profession, double perWage){
		this.profession = profession;
		this.perWage = perWage;
	}
	
	public ProfessionType getProfession(){
		return this.profession;
	}
	
	public void setProfession(ProfessionType profession){
		this.profession = profession;
	}
	
	public double getPerWage(){
		return this.perWage;
	}
	
	public void setPerWage(double perWage){
		this.perWage = perWage;
	}
	
}
