package businesslogicservice.manageblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.CostVO;
import type.ExpressType;

public interface CostBLService {
	public int addCost(CostVO costvo) throws RemoteException;
	public int deleteCost(CostVO costvo) throws RemoteException;
	public int modifyCost(CostVO costvo) throws RemoteException;
	public double findCost(ExpressType category) throws RemoteException;
	public ArrayList<CostVO> showAllCosts() throws RemoteException;
}
