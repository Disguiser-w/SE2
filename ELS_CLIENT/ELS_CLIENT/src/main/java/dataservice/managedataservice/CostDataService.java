package dataservice.managedataservice;

import java.rmi.RemoteException;

import type.ExpressType;
import po.CostPO;

public interface CostDataService {

	public int addCost(CostPO costpo) throws RemoteException;
	public int deleteCost(CostPO costpo) throws RemoteException;
	public int modifyCost(CostPO costpo) throws RemoteException;
	public double findCost(ExpressType costpo) throws RemoteException;
	
}
