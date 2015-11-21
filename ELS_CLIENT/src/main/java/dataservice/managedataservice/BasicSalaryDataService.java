package dataservice.managedataservice;

import po.BasicSalaryPO;
import type.ProfessionType;

import java.rmi.RemoteException;

public interface BasicSalaryDataService {

	public int addBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public int deleteBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public int modifyBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public double findBasicSalary(ProfessionType profession) throws RemoteException;
	
}
