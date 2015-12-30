package dataservice.managedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PerWagePO;
import type.ProfessionType;

public interface PerWageDataService extends Remote {

	public int modifyPerWage(PerWagePO perwagepo) throws RemoteException;
	public PerWagePO findPerWage(ProfessionType profession) throws RemoteException;
	public ArrayList<PerWagePO> showAllPerWages() throws RemoteException;
	
}
