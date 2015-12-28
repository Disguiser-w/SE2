package businesslogic.managebl.controller;

import java.util.ArrayList;

import businesslogic.managebl.CostBL;
import businesslogicservice.manageblservice.CostBLService;
import type.ExpressType;
import vo.CostVO;

public class CostController implements CostBLService{
	
	private CostBL costBL;
	
	public CostController(){
		costBL = new CostBL();
	}
	
	public int addCost(CostVO costvo){
		return costBL.addCost(costvo);
	}
	
	public int deleteCost(CostVO costvo){
		return costBL.deleteCost(costvo);
	}
	
	public int modifyCost(CostVO costvo){
		return costBL.modifyCost(costvo);
	}
	
	public double findCost(ExpressType profession){
		return costBL.findCost(profession);
	}
	
	public ArrayList<CostVO> showAllCosts(){
		return costBL.showAllCosts();
	}
	
}
