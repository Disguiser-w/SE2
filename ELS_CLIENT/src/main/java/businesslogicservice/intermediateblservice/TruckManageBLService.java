package businesslogicservice.intermediateblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import type.OperationState;
import vo.TruckVO;

public interface TruckManageBLService {
	public ArrayList<TruckVO> showTruckList();

	public OperationState addTruck(String ID, String destination) throws RemoteException;

	public OperationState deleteTruck(String truck_ID) throws Exception;

	public OperationState modifyTruck(TruckVO truck_modify) throws Exception;

	public TruckVO showTruck(String truck_ID) throws Exception;

	public OperationState saveTruckList() throws RemoteException;
}
