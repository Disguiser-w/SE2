package businesslogicservice.manageblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.ProfessionType;
import vo.PerWageVO;

public interface PerWageBLService {
	
	public int modifyPerWage(PerWageVO perwagevo) throws RemoteException;
	public double findPerWage(ProfessionType profession) throws RemoteException;
	public ArrayList<PerWageVO> showAllPerWages() throws RemoteException;
	
}
