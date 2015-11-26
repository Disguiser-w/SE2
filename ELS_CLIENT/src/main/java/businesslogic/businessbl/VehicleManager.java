package businesslogic.businessbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.managebl.OrganizationBL;
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
			pos = businessData.getVehicleInfos(BusinessMainController.businessVO.organizationVO.organizationID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (VehiclePO i : pos)
			vos.add(BusinessMainController.vehiclePOToVO(i));

		return vos;
	}

	public boolean addVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = businessData.addVehicle(BusinessMainController.vehicleVOToPO(vo));
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
			result = businessData.deleteVehicle(BusinessMainController.vehicleVOToPO(vo));
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
			result = businessData.deleteVehicle(BusinessMainController.vehicleVOToPO(vo));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
