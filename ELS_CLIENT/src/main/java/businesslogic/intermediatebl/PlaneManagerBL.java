package businesslogic.intermediatebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.financebl.LogDiaryBL;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.receiptbl.GetDate;
import businesslogicservice.intermediateblservice.envehicleblservice.PlaneManagerBLService;
import dataservice.intermediatedataservice.IntermediateDataService;
import po.PlanePO;
import type.OperationState;
import vo.LogDiaryVO;
import vo.OrganizationVO;
import vo.PlaneVO;
import vo.UserVO;

public class PlaneManagerBL implements PlaneManagerBLService {
	private IntermediateDataService intermediateData;

	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	private ArrayList<PlanePO> planeList_temp = new ArrayList<PlanePO>();

	private OrganizationVO intermediateCenter;
	private UserVO intermediate;

	private LogDiaryBL logDiary;

	public PlaneManagerBL(ArrayList<PlaneVO> planeList,
			OrganizationVO intermediateCentre,
			IntermediateDataService intermediateData,
			UserVO intermediate) {
		// TODO 自动生成的方法存根
		this.planeList = planeList;
		this.intermediateCenter = intermediateCentre;
		this.intermediateData = intermediateData;
		this.intermediate = intermediate;

		logDiary = new LogDiaryBL();
	}

	public ArrayList<PlaneVO> showPlaneList() {
		// TODO 自动生成的方法存根
		return planeList;
	}

	public OperationState addPlane(String ID, String destination)
			throws RemoteException {
		// TODO 自动生成的方法存根
		PlaneVO plane_add = new PlaneVO(ID, destination);
		planeList.add(plane_add);
		savePlaneList();
		logDiary.addLogDiary(new LogDiaryVO(GetDate.getTime(), intermediate,
				"在本中转中心飞机列表中新增了一架飞机"), GetDate.getTime());
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState deletePlane(String s) throws Exception {
		// TODO 自动生成的方法存根
		for (PlaneVO plane : planeList) {
			if (plane.ID.equals(s)) {
				planeList.remove(plane);
				savePlaneList();
				logDiary.addLogDiary(new LogDiaryVO(GetDate.getTime(),
						intermediate, "在本中转中心飞机列表中删除了一架飞机"), GetDate.getTime());
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("未找到该ID的飞机！");
	}

	public OperationState modifyPlane(PlaneVO plane_modify) throws Exception {
		// TODO 自动生成的方法存根
		for (PlaneVO plane : planeList) {
			if (plane.ID.equals(plane_modify.ID)) {
				planeList.set(planeList.indexOf(plane), plane_modify);
				savePlaneList();
				logDiary.addLogDiary(new LogDiaryVO(GetDate.getTime(),
						intermediate, "在本中转中心中修改了一家飞机信息"), GetDate.getTime());
				return OperationState.SUCCEED_OPERATION;
			}
		}
		throw new Exception("未找到该ID的飞机！");
	}

	public PlaneVO showPlane(String plane_ID) throws Exception {
		// TODO 自动生成的方法存根
		for (PlaneVO plane : planeList) {
			if (plane.ID.equals(plane_ID))
				return plane;
		}
		throw new Exception("未找到该ID的飞机！");
	}

	public OperationState savePlaneList() throws RemoteException {
		// TODO 自动生成的方法存根
		for (PlaneVO plane : planeList)
			planeList_temp.add(IntermediateMainController.voToPO(plane));
		intermediateData.savePlaneList(intermediateCenter.organizationID,
				planeList_temp);
		return OperationState.SUCCEED_OPERATION;
	}
}
