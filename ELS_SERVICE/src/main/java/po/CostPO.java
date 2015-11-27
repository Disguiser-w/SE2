package po;

<<<<<<< HEAD
import type.ExpressType;

public class CostPO {
=======
import java.io.Serializable;

import type.ExpressType;

public class CostPO implements Serializable {
	
	private static final long serialVersionUID = 141250154L;
	
>>>>>>> dfb0783d596c62136e5eb78018eb2b88f4604364
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
