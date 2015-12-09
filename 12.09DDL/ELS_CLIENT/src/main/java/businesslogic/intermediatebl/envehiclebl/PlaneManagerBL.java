package businesslogic.intermediatebl.envehiclebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PlanePO;
import type.OperationState;
import vo.OrganizationVO;
import vo.PlaneVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogicservice.intermediateblservice.envehicleblservice.PlaneManagerBLService;
import dataservice.intermediatedataservice.IntermediateDataService;

public class PlaneManagerBL implements PlaneManagerBLService {
	private IntermediateDataService intermediateData;

	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();
	private ArrayList<PlanePO> planeList_temp = new ArrayList<PlanePO>();

	private OrganizationVO intermediateCenter;

	public PlaneManagerBL(ArrayList<PlaneVO> planeList,
			OrganizationVO intermediateCentre,
			IntermediateDataService intermediateData) {
		// TODO 自动生成的方法存根
		this.planeList = planeList;
		this.intermediateCenter = intermediateCentre;
		this.intermediateData = intermediateData;
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
		return OperationState.SUCCEED_OPERATION;
	}

	public OperationState deletePlane(PlaneVO plane_delete) throws Exception {
		// TODO 自动生成的方法存根
		for (PlaneVO plane : planeList) {
			if (plane.ID.equals(plane_delete.ID)) {
				planeList.remove(plane);
				savePlaneList();
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
		intermediateData.savePlaneList(intermediateCenter.getOrganizationID(),
				planeList_temp);
		return OperationState.SUCCEED_OPERATION;
	}
}
