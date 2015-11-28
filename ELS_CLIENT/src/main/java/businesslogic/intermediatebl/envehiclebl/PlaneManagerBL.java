package businesslogic.intermediatebl.envehiclebl;

import java.util.ArrayList;

import type.OperationState;
import vo.PlaneVO;
import businesslogicservice.intermediateblservice.envehicleblservice.PlaneManagerBLService;

public class PlaneManagerBL implements PlaneManagerBLService {
	private ArrayList<PlaneVO> planeList = new ArrayList<PlaneVO>();

	public PlaneManagerBL(ArrayList<PlaneVO> planeList) {
		// TODO 自动生成的方法存根
		this.planeList = planeList;
	}

	public ArrayList<PlaneVO> showPlaneList() {
		// TODO 自动生成的方法存根
		return planeList;
	}
	
	public OperationState addPlane(String ID,String destination){
		// TODO 自动生成的方法存根
		PlaneVO plane_add= new PlaneVO(ID,destination);
		planeList.add(plane_add);
		return OperationState.SUCCEED_OPERATION;
	}
	
	public OperationState deletePlane(PlaneVO plane_delete) throws Exception{
		// TODO 自动生成的方法存根
		for(PlaneVO plane:planeList){
			if(plane.ID.equals(plane_delete.ID)){
				planeList.remove(plane);
				return OperationState.SUCCEED_OPERATION;
						}
		}
		throw new Exception("未找到该ID的飞机！");
	}
	
	public OperationState modifyPlane(PlaneVO plane_modify) throws Exception{
		// TODO 自动生成的方法存根
		for(PlaneVO plane:planeList){
			if(plane.ID.equals(plane_modify.ID)){
				planeList.set(planeList.indexOf(plane), plane_modify);
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

	public OperationState savePlaneList(ArrayList<PlaneVO> planeList){
		// TODO 自动生成的方法存根
		//
		return OperationState.SUCCEED_OPERATION;
	}
}