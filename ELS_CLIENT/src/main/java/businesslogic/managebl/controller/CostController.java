package businesslogic.managebl.controller;

import java.util.ArrayList;

import type.ExpressType;
import vo.CostVO;
import businesslogic.managebl.CostBL;

public class CostController {
	
	private CostBL cost;
	
	public CostController(){
		cost = new CostBL();
	}
	
	public int addCost(CostVO costvo){
		return cost.addCost(costvo);
	}
	
	public int deleteCost(CostVO costvo){
		return cost.deleteCost(costvo);
	}
	
	public int modifyCost(CostVO costvo){
		return cost.modifyCost(costvo);
	}
	
	public double findCost(ExpressType profession){
		return cost.findCost(profession);
	}
	
	public ArrayList<CostVO> showAllCosts(){
		return cost.showAllCosts();
	}
	
}
