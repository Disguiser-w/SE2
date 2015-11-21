package businesslogic.managebl;

import java.rmi.RemoteException;

import po.BasicSalaryPO;
import vo.BasicSalaryVO;
import type.ProfessionType;
import businesslogicservice.manageblservice.BasicSalaryBLService;
import dataservice.managedataservice.BasicSalaryDataService;

public class BasicSalaryBL implements BasicSalaryBLService{
	BasicSalaryDataService bsdService;
	
	public int addBasicSalary(BasicSalaryVO basicSalaryvo){
		try{
			BasicSalaryPO basicSalarypo = voToPO(basicSalaryvo);
			bsdService.addBasicSalary(basicSalarypo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int deleteBasicSalary(BasicSalaryVO basicSalaryvo){
		try{
			BasicSalaryPO basicSalarypo = voToPO(basicSalaryvo);
			bsdService.deleteBasicSalary(basicSalarypo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int modifyBasicSalary(BasicSalaryVO basicSalaryvo){
		try{
			BasicSalaryPO basicSalarypo = voToPO(basicSalaryvo);
			bsdService.modifyBasicSalary(basicSalarypo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public double findBasicSalary(ProfessionType profession){
		try{
			return bsdService.findBasicSalary(profession);
		}catch(RemoteException exception){
			exception.printStackTrace();
			return -1.0;
		}
	}
	
	public BasicSalaryPO voToPO(BasicSalaryVO basicSalaryvo){
		return new BasicSalaryPO(basicSalaryvo.getProfession(),basicSalaryvo.getBasicSalary());
	}
	
	public BasicSalaryVO poToVO(BasicSalaryPO basicSalarypo){
		return new BasicSalaryVO(basicSalarypo.getProfession(),basicSalarypo.getBasicSalary());
	}
	
}
