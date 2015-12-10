package businesslogic.managebl.controller;

import java.util.ArrayList;

import type.ProfessionType;
import vo.PerWageVO;
import businesslogic.managebl.PerWageBL;

public class PerWageController {
	
	private PerWageBL perWage;
	
	public PerWageController(){
		perWage = new PerWageBL();
	}
	
	public int addPerWage(PerWageVO perWagevo){
		return perWage.addPerWage(perWagevo);
	}
	
	public int deletePerWage(PerWageVO perWagevo){
		return perWage.deletePerWage(perWagevo);
	}
	
	public int modifyPerWage(PerWageVO perWagevo){
		return perWage.modifyPerWage(perWagevo);
	}
	
	public double findPerWage(ProfessionType profession){
		return perWage.findPerWage(profession);
	}
	
	public ArrayList<PerWageVO> showAllPerWages(){
		return perWage.showAllPerWages();
	}
	
}
