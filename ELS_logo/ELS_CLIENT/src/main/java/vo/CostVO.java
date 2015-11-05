package vo;

import type.ExpressType;
public class CostVO {

	private ExpressType category;
	private double cost;
	
	public CostVO(ExpressType category, double cost){
		this.category = category;
		this.cost = cost;
	}
	
	public ExpressType getCategory(){
		return this.category;
	}
	
	public void setCategory(ExpressType category){
		this.category = category;
	}
	
	public double getCost(){
		return this.cost;
	}
	
	public void setCost(double cost){
		this.cost = cost;
	}
}
