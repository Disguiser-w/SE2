package dataservice.managedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BasicSalaryPO;
import type.ProfessionType;

public interface BasicSalaryDataService extends Remote {

	public int addBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public int deleteBasicSalary(ProfessionType profession) throws RemoteException;
	public int modifyBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public BasicSalaryPO findBasicSalary(ProfessionType profession) throws RemoteException;
	public ArrayList<BasicSalaryPO> showAllBasicSalarys() throws RemoteException;
	
}
