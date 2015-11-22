package dataservice.managedataservice;

import java.rmi.RemoteException;

import po.CostPO;
import type.ExpressType;

public interface CostDataService {

	public int addCost(CostPO costpo) throws RemoteException;
	public int deleteCost(CostPO costpo) throws RemoteException;
	public int modifyCost(CostPO costpo) throws RemoteException;
	public double findCost(ExpressType costpo) throws RemoteException;
	
}
