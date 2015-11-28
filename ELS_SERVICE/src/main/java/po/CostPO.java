package po;

import type.ExpressType;

import java.io.Serializable;

public class CostPO implements Serializable {
	
	private static final long serialVersionUID = 141250154L;
	
	ExpressType expresstype;
	double cost;
	
	public CostPO(ExpressType expresstype, double cost){
		this.expresstype = expresstype;
		this.cost = cost;
	}
	
	public ExpressType getExpressType(){
		return this.expresstype;
	}
	
	public void setExpressType(ExpressType expresstype){
		this.expresstype  = expresstype;
	}
	
	public double getCost(){
		return this.cost;
	}
	
	public void setCost(double cost){
		this.cost  =cost;
	}
	
}
