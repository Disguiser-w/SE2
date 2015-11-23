package businesslogic.managebl;

import java.rmi.RemoteException;

import po.PerWagePO;
import vo.PerWageVO;
import type.ProfessionType;
import businesslogicservice.manageblservice.PerWageBLService;
import dataservice.managedataservice.PerWageDataService;

public class PerWageBL implements PerWageBLService{
	PerWageDataService pwdService;
	
	public int addPerWage(PerWageVO perWagevo){
		try{
			PerWagePO perWagepo = voToPO(perWagevo);
			pwdService.addPerWage(perWagepo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int deletePerWage(PerWageVO perWagevo){
		try{
			PerWagePO perWagepo = voToPO(perWagevo);
			pwdService.deletePerWage(perWagepo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int modifyPerWage(PerWageVO perWagevo){
		try{
			PerWagePO perWagepo = voToPO(perWagevo);
			pwdService.modifyPerWage(perWagepo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public double findPerWage(ProfessionType profession){
		try{
			return pwdService.findPerWage(profession);
		}catch(RemoteException exception){
			exception.printStackTrace();
			return -1.0;
		}
	}
	
	public PerWagePO voToPO(PerWageVO perWagevo){
		return new PerWagePO(perWagevo.getProfession(),perWagevo.getPerWage());
	}
	
	public PerWageVO poToVO(PerWagePO perWagepo){
		return new PerWageVO(perWagepo.getProfession(),perWagepo.getPerWage());
	}
	
}
