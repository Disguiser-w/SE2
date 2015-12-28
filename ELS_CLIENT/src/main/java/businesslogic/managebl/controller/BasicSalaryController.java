package businesslogic.managebl.controller;

import java.util.ArrayList;

import businesslogic.managebl.BasicSalaryBL;
import businesslogicservice.manageblservice.BasicSalaryBLService;
import type.ProfessionType;
import vo.BasicSalaryVO;

public class BasicSalaryController implements BasicSalaryBLService{
	
	private BasicSalaryBL basicSalaryBL;
	
	public BasicSalaryController(){
		basicSalaryBL = new BasicSalaryBL();
	}
	
	public int addBasicSalary(BasicSalaryVO basicSalaryvo){
		return basicSalaryBL.addBasicSalary(basicSalaryvo);
	}
	
	public int deleteBasicSalary(BasicSalaryVO basicSalaryvo){
		return basicSalaryBL.deleteBasicSalary(basicSalaryvo);
	}
	
	public int modifyBasicSalary(BasicSalaryVO basicSalaryvo){
		return basicSalaryBL.modifyBasicSalary(basicSalaryvo);
	}
	
	public double findBasicSalary(ProfessionType profession){
		return basicSalaryBL.findBasicSalary(profession);
	}
	
	public ArrayList<BasicSalaryVO> showAllBasicSalarys(){
		return basicSalaryBL.showAllBasicSalarys();
	}
	
}
