package dataservice.managedataservice;

import java.rmi.RemoteException;

import po.CostPO;
import type.ExpressType;

public class CostDataService_stub implements CostDataService{

	public int addCost(CostPO costpo) throws RemoteException{
		System.out.println("Add cost succeed!");
		return 0;
	}
	
	public int deleteCost(CostPO costpo) throws RemoteException{
		System.out.println("Delete cost succeed!");
		return 0;
	}
	
	public int modifyCost(CostPO costpo) throws RemoteException{
		System.out.println("Modify cost succeed!");
		return 0;
	}
	
	public double findCost(ExpressType expresstype) throws RemoteException{
		System.out.println("Find cost succeed!");
		return 0;
	}
	
}
