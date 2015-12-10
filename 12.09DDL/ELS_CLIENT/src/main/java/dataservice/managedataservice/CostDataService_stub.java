package dataservice.managedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CostPO;
import type.ExpressType;

public class CostDataService_stub implements CostDataService{
	
	public int addCost(CostPO costpo) throws RemoteException{
		System.out.println("Add cost succeed!");
		return 0;
	}
	
    public int deleteCost(ExpressType express) throws RemoteException{
		System.out.println("Delete cost succeed!");
		return 0;
	}
    
    public int modifyCost(CostPO costpo) throws RemoteException{
		System.out.println("Modify cost succeed!");
		return 0;
	}
    
    public CostPO findCost(ExpressType express) throws RemoteException{
		System.out.println("Find cost succeed!");
		return null;
	}
    
    public ArrayList<CostPO> showAllCosts() throws RemoteException{
		System.out.println("Show all cost succeed!");
		return null;
	}
    
}
