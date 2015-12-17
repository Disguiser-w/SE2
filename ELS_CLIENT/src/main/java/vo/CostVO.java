package vo;

import type.ExpressType;
public class CostVO {

	public ExpressType category;
	public double cost;
	
	public CostVO(ExpressType category, double cost){
		this.category = category;
		this.cost = cost;
	}
	
}
