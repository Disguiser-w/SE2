package dataservice.managedataservice;

import po.PerWagePO;

import java.rmi.RemoteException;
import type.ProfessionType;

public interface PerWageDataService {

	public int addPerWage(PerWagePO perwagepo) throws RemoteException;
	public int deletePerWage(PerWagePO perwagepo) throws RemoteException;
	public int modifyPerWage(PerWagePO perwagepo) throws RemoteException;
	public double findPerWage(ProfessionType profession) throws RemoteException;
	
}
