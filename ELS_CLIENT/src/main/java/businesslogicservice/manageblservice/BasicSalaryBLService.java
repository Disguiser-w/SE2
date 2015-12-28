package businesslogicservice.manageblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.ProfessionType;
import vo.BasicSalaryVO;

public interface BasicSalaryBLService {
	
	public int addBasicSalary(BasicSalaryVO basicsalaryvo) throws RemoteException;
	public int deleteBasicSalary(BasicSalaryVO basicsalaryvo) throws RemoteException;
	public int modifyBasicSalary(BasicSalaryVO basicsalaryvo) throws RemoteException;
	public double findBasicSalary(ProfessionType profession) throws RemoteException;
	public ArrayList<BasicSalaryVO> showAllBasicSalarys() throws RemoteException;
	
}
