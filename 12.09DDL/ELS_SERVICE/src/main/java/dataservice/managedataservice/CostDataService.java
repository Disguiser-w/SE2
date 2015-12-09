package dataservice.managedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CostPO;
import type.ExpressType;

public interface CostDataService extends Remote {

	public int addCost(CostPO costpo) throws RemoteException;
    public int deleteCost(ExpressType expressType) throws RemoteException;
    public int modifyCost(CostPO costpo) throws RemoteException;
    public CostPO findCost(ExpressType expressType) throws RemoteException;
    public ArrayList<CostPO> showAllCosts() throws RemoteException;
    
}
