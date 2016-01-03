package businesslogic.intermediatebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.financebl.LogDiaryBL;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.receiptbl.GetDate;
import businesslogicservice.intermediateblservice.TruckManageBLService;
import dataservice.intermediatedataservice.IntermediateDataService;
import po.TruckPO;
import type.OperationState;
import vo.LogDiaryVO;
import vo.OrganizationVO;
import vo.TruckVO;
import vo.UserVO;

public class TruckManagerBL implements TruckManageBLService {
	private IntermediateDataService intermediateData;

	private ArrayList<TruckVO> truckList = new ArrayList<TruckVO>();

	private OrganizationVO intermediateCenter;
	private UserVO intermediate;

	private LogDiaryBL logDiary;

	public TruckManagerBL(ArrayList<TruckVO> truckList,
			OrganizationVO intermediateCentre,
			IntermediateDataService intermediateData, UserVO intermediate) {
		// TODO 自动生成的方法存根
		this.truckList = truckList;
		this.intermediateCenter = intermediateCentre;
		this.intermediateData = intermediateData;
		this.intermediate = intermediate;

		logDiary = new LogDiaryBL();
	}

	public ArrayList<TruckVO> showTruckList() {
		// TODO 自动生成的方法存根
		return truckList;
	}

	public OperationState addTruck(String ID, String destination)
			throws RemoteException {
		// TODO 自动生成的方法存根
		TruckVO truck_add = new TruckVO(ID, destination);
		truckList.add(truck_add);
		saveTruckList();
		logDiary.addLogDiary(new LogDiaryVO(GetDate.getTime(), intermediate,
				"在本中转中心汽车列表中新增了一架汽车"), GetDate.getTime());
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState deleteTruck(String s) throws Exception {
		// TODO 自动生成的方法存根
		for (TruckVO truck : truckList) {
			if (truck.ID.equals(s)) {
				truckList.remove(truck);
				saveTruckList();
				logDiary.addLogDiary(new LogDiaryVO(GetDate.getTime(),
						intermediate, "在本中转中心汽车列表中删除了一架汽车"), GetDate.getTime());
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
				saveTruckList();
				logDiary.addLogDiary(new LogDiaryVO(GetDate.getTime(),
						intermediate, "在本中转中心中修改了一家汽车信息"), GetDate.getTime());
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

	public OperationState saveTruckList() throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<TruckPO> truckList_temp = new ArrayList<TruckPO>();
		for (TruckVO truck : truckList)
			truckList_temp.add(IntermediateMainController.voToPO(truck));
		System.out.println(intermediateCenter.organizationID);
		intermediateData.saveTruckList(intermediateCenter.organizationID,
				truckList_temp);
		return OperationState.SUCCEED_OPERATION;
	}
}
