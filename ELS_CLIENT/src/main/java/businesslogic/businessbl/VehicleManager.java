package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.businessdataservice.BusinessDataService;
import dataservice.businessdataservice.BusinessDataService_stub;
import po.VehiclePO;
import vo.VehicleVO;

public class VehicleManager {

	private BusinessDataService businessData;

	public VehicleManager() {
		businessData = new BusinessDataService_stub();
	}

	public ArrayList<VehicleVO> getVehicleInfo() {
		// TODO Auto-generated method stub
		ArrayList<VehicleVO> vos = new ArrayList<VehicleVO>();
		ArrayList<VehiclePO> pos = null;
		try {
			pos = businessData.getVehicleInfos();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (VehiclePO i : pos)
			vos.add(poToVO(i));

		return vos;
	}

	public boolean addVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.addVehicle(voToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.deleteVehicle(voToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public boolean modifyVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.deleteVehicle(voToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static VehiclePO voToPO(VehicleVO vo) {

		return new VehiclePO(vo.ID, vo.engineNumber, vo.licensePlateNumber, vo.lowNumberPlate, vo.buyTime,
				vo.serviceTime, vo.location);
	}

	public static VehicleVO poToVO(VehiclePO po) {
		return new VehicleVO(po.getID(), po.getEngineNumber(), po.getLicensePlateNumber(), po.getLowNumberPlate(),
				po.getBuyTime(), po.getServiceTime(), po.getLocation());
	}

}
