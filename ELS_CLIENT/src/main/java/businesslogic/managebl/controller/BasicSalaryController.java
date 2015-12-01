package businesslogic.managebl.controller;

import java.util.ArrayList;

import type.ProfessionType;
import vo.BasicSalaryVO;
import businesslogic.managebl.BasicSalaryBL;

public class BasicSalaryController {
	
	private BasicSalaryBL basicSalary;
	
	public BasicSalaryController(){
		basicSalary = new BasicSalaryBL();
	}
	
	public int addBasicSalary(BasicSalaryVO basicSalaryvo){
		return basicSalary.addBasicSalary(basicSalaryvo);
	}
	
	public int deleteBasicSalary(BasicSalaryVO basicSalaryvo){
		return basicSalary.deleteBasicSalary(basicSalaryvo);
	}
	
	public int modifyBasicSalary(BasicSalaryVO basicSalaryvo){
		return basicSalary.modifyBasicSalary(basicSalaryvo);
	}
	
	public double findBasicSalary(ProfessionType profession){
		return basicSalary.findBasicSalary(profession);
	}
	
	public ArrayList<BasicSalaryVO> showAllBasicSalarys(){
		return basicSalary.showAllBasicSalarys();
	}
	
}
