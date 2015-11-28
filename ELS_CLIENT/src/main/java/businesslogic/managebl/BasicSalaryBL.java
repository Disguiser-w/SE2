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
			return(bsdService.addBasicSalary(basicSalarypo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int deleteBasicSalary(BasicSalaryVO basicSalaryvo){
		try{
			BasicSalaryPO basicSalarypo = voToPO(basicSalaryvo);
			return(bsdService.deleteBasicSalary(basicSalarypo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int modifyBasicSalary(BasicSalaryVO basicSalaryvo){
		try{
			BasicSalaryPO basicSalarypo = voToPO(basicSalaryvo);
			return(bsdService.modifyBasicSalary(basicSalarypo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
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
