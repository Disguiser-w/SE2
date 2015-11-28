package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import type.OperationState;
import vo.TruckVO;

public interface TruckManageBLService {
	public ArrayList<TruckVO> showTruckList();

	public OperationState addTruck(String ID, String destination);

	public OperationState deleteTruck(TruckVO truck_delete) throws Exception;

	public OperationState modifyTruck(TruckVO truck_modify) throws Exception;

	public TruckVO showTruck(String truck_ID) throws Exception;

	public OperationState saveTruckList();
}
