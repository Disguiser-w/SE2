package businesslogic.managebl.controller;

import java.util.ArrayList;

import businesslogic.managebl.PerWageBL;
import businesslogicservice.manageblservice.PerWageBLService;
import type.ProfessionType;
import vo.PerWageVO;

public class PerWageController implements PerWageBLService{
	
	private PerWageBL perWageBL;
	
	public PerWageController(){
		perWageBL = new PerWageBL();
	}
	
	/*public int addPerWage(PerWageVO perWagevo){
		return perWageBL.addPerWage(perWagevo);
	}
	
	public int deletePerWage(PerWageVO perWagevo){
		return perWageBL.deletePerWage(perWagevo);
	}*/
	
	public int modifyPerWage(PerWageVO perWagevo){
		return perWageBL.modifyPerWage(perWagevo);
	}
	
	public double findPerWage(ProfessionType profession){
		return perWageBL.findPerWage(profession);
	}
	
	public ArrayList<PerWageVO> showAllPerWages(){
		return perWageBL.showAllPerWages();
	}
	
}
