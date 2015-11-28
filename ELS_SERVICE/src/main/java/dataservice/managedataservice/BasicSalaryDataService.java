package dataservice.managedataservice;

import po.BasicSalaryPO;
import type.ProfessionType;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BasicSalaryDataService {

	public int addBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public int deleteBasicSalary(ProfessionType profession) throws RemoteException;
	public int modifyBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public BasicSalaryPO findBasicSalary(ProfessionType profession) throws RemoteException;
	public ArrayList<BasicSalaryPO> showAllBasicSalarys() throws RemoteException;
	
}
