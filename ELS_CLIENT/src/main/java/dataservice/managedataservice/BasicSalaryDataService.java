package dataservice.managedataservice;

import type.ProfessionType;

import java.rmi.RemoteException;

import po.BasicSalaryPO;

public interface BasicSalaryDataService {

	public int addBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public int deleteBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public int modifyBasicSalary(BasicSalaryPO basicsalarypo) throws RemoteException;
	public double findBasicSalary(ProfessionType profession) throws RemoteException;
	
}
