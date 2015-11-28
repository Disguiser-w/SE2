package businesslogic.managebl;

import java.rmi.RemoteException;

import po.CostPO;
import businesslogicservice.manageblservice.CostBLService;
import dataservice.managedataservice.CostDataService;
import type.ExpressType;
import vo.CostVO;

public class CostBL implements CostBLService{
	
	CostDataService codService;
	
	public int addCost(CostVO costvo){
		try{
			CostPO costpo = voToPO(costvo);
			return(codService.addCost(costpo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int deleteCost(CostVO costvo){
		try{
			return(codService.deleteCost(costvo.getCategory()));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public int modifyCost(CostVO costvo){
		try{
			CostPO costpo = voToPO(costvo);
			return(codService.modifyCost(costpo));
		}catch(RemoteException exception){
			exception.printStackTrace();
			return 2;
		}
	}
	
	public double findCost(ExpressType category){
		try{
			return codService.findCost(category).getCost();
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
