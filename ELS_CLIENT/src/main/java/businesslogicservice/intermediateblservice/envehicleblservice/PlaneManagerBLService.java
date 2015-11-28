package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

import type.OperationState;
import vo.PlaneVO;

public interface PlaneManagerBLService {
	public ArrayList<PlaneVO> showPlaneList();

	public OperationState addPlane(String ID,String destination);
	
	public OperationState deletePlane(PlaneVO plane_delete) throws Exception;
	
	public OperationState modifyPlane(PlaneVO plane_modify) throws Exception;

	public PlaneVO showPlane(String plane_ID) throws Exception;

	public OperationState savePlaneList(ArrayList<PlaneVO> planeList);
}
