package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import type.OperationState;
import vo.TruckVO;
import businesslogicservice.intermediateblservice.envehicleblservice.TruckManageBLService;

public class TruckManagerBL implements TruckManageBLService {
	private ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();

	public TruckManagerBL(ArrayList<TruckVO> truckList) {
		this.truckList = truckList;
	}

	public ArrayList<TruckVO> showTruckList() {
		// TODO 自动生成的方法存根
		return truckList;
	}

	public OperationState addTruck(String ID, String destination) {
		// TODO 自动生成的方法存根
		TruckVO truck_add = new TruckVO(ID, destination);
		truckList.add(truck_add);
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState deleteTruck(TruckVO truck_delete) throws Exception {
		// TODO 自动生成的方法存根
		for (TruckVO truck : truckList) {
			if (truck.ID.equals(truck_delete.ID)) {
				truckList.remove(truck);
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("未找到该ID的汽车！");
	}

	public OperationState modifyTruck(TruckVO truck_modify) throws Exception {
		// TODO 自动生成的方法存根
		for (TruckVO truck : truckList) {
			if (truck.ID.equals(truck_modify.ID)) {
				truckList.set(truckList.indexOf(truck), truck_modify);
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("未找到该ID的汽车！");
	}

	public TruckVO showTruck(String truck_ID) throws Exception {
		// TODO 自动生成的方法存根
		for (TruckVO truck : truckList) {
			if (truck.ID.equals(truck_ID))
				return truck;
		}
		throw new Exception("未找到该ID的汽车！");
	}

	public OperationState saveTruckList() {
		// TODO 自动生成的方法存根
		//
		return OperationState.SUCCEED_OPERATION;
	}
}
