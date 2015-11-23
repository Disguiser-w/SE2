package businesslogic.businessbl;

import java.util.ArrayList;

import dataservice.businessdataservice.BusinessDataService;
import po.VehiclePO;
import vo.VehicleVO;

public class VehicleManager {

	private BusinessDataService businessData;

	public VehicleManager() {

	}

	public ArrayList<VehicleVO> getVehicleInfo() {
		// TODO Auto-generated method stub
		ArrayList<VehicleVO> vos = new ArrayList<VehicleVO>();
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

	public static VehiclePO voToPO(VehicleVO vo) {
		return null;
	}

	public static VehicleVO voToVO(VehiclePO po) {
		return null;
	}

}
