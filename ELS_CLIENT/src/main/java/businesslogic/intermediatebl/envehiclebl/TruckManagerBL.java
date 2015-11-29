package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import po.TruckPO;
import type.OperationState;
import vo.OrganizationVO;
import vo.TrainVO;
import vo.TruckVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogicservice.intermediateblservice.envehicleblservice.TruckManageBLService;
import dataservice.intermediatedataservice.IntermediateDataService;

public class TruckManagerBL implements TruckManageBLService {
	private IntermediateDataService intermediateData;

	private ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();
	private ArrayList<TruckPO> truckList_temp = new ArrayList<TruckPO>();

	private OrganizationVO intermediateCentre;

	public TruckManagerBL(ArrayList<TruckVO> truckList,
			OrganizationVO intermediateCentre,
			IntermediateDataService intermediateData) {
		this.truckList = truckList;
		this.intermediateCentre = intermediateCentre;
		this.intermediateData = intermediateData;
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
		for (TruckVO train : truckList)
			truckList_temp.add(IntermediateMainController.voToPO(train));
		intermediateData.saveTruckList(intermediateCentre.getOrganizationID(),
				truckList_temp);
		return OperationState.SUCCEED_OPERATION;
	}
}
