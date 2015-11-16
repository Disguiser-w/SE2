package businesslogic.businessbl;

import java.util.ArrayList;

import businesslogicservice.businessblservice.VehicleManagerBLService;
import dataservice.businessdataservice.BusinessDataService;
import vo.VehicleVO;

public class VehicleManager {

	private BusinessDataService businessData;

	public VehicleManager() {

	}

	public ArrayList<VehicleVO> getVehicleInfo() {
		// TODO Auto-generated method stub
		ArrayList<VehicleVO> vos = new ArrayList<VehicleVO> ();
		vos.add(new VehicleVO("025-001-112", null, null, null, null, null));
		return vos;
	}

	public boolean addVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean deleteVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean modifyVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		return true;
	}

}
