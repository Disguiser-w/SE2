package businesslogic.managebl;

import java.rmi.RemoteException;

import businesslogicservice.manageblservice.CostBLService;
import dataservice.managedataservice.CostDataService;
import type.ExpressType;
import vo.CostVO;
import po.CostPO;

public class CostBL implements CostBLService{
	
	CostDataService codService;
	
	public int addCost(CostVO costvo){
		try{
			CostPO costpo = voToPO(costvo);
			codService.addCost(costpo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int deleteCost(CostVO costvo){
		try{
			CostPO costpo = voToPO(costvo);
			codService.deleteCost(costpo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public int modifyCost(CostVO costvo){
		try{
			CostPO costpo = voToPO(costvo);
			codService.modifyCost(costpo);
			return 0;
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 1;
		}
	}
	
	public double findCost(ExpressType category){
		try{
			return codService.findCost(category);
		}catch(RemoteException exception){
			exception.printStackTrace();
			return -1.0;
		}
	}
	
	public CostPO voToPO(CostVO costvo){
		return new CostPO(costvo.getCategory(), costvo.getCost());
	}
	
	public CostVO poToVO(CostPO costpo){
		return new CostVO(costpo.getExpressType(), costpo.getCost());
	}
}
