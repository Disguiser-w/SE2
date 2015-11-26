package po;

import java.io.Serializable;

import type.ProfessionType;

public class PerWagePO implements Serializable {
	
	private static final long serialVersionUID = 141250155L;
	
	private ProfessionType profession;
	private double perWage;
	
	public PerWagePO(ProfessionType profession, double perWage){
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
