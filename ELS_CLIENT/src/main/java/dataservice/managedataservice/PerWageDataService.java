package dataservice.managedataservice;

import po.PerWagePO;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.ProfessionType;

public interface PerWageDataService {

	public int addPerWage(PerWagePO perwagepo) throws RemoteException;
	public int deletePerWage(ProfessionType profession ) throws RemoteException;
	public int modifyPerWage(PerWagePO perwagepo) throws RemoteException;
	public PerWagePO findPerWage(ProfessionType profession) throws RemoteException;
	public ArrayList<PerWagePO> showAllPerWages() throws RemoteException;
}
