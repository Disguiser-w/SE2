package businesslogicservice.manageblservice;

import java.rmi.RemoteException;

import vo.BasicSalaryVO;
import type.ProfessionType;

public interface BasicSalaryBLService {
	public int addBasicSalary(BasicSalaryVO basicsalaryvo) throws RemoteException;
	public int deleteBasicSalary(BasicSalaryVO basicsalaryvo) throws RemoteException;
	public int modifyBasicSalary(BasicSalaryVO basicsalaryvo) throws RemoteException;
	public double findBasicSalary(ProfessionType profession) throws RemoteException;
}
