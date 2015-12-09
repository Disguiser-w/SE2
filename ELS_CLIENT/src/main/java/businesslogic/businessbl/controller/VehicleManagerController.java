package businesslogic.businessbl.controller;

import java.util.ArrayList;

import businesslogic.businessbl.VehicleManager;
import businesslogicservice.businessblservice.VehicleManagerBLService;
import vo.DriverVO;
import vo.OrganizationVO;
import vo.VehicleVO;

public class VehicleManagerController implements VehicleManagerBLService {
	private VehicleManager vehicleManager;

	public VehicleManagerController() {
		vehicleManager = new VehicleManager();
	}

	public ArrayList<VehicleVO> getVehicleInfo() {
		// TODO Auto-generated method stub
		return vehicleManager.getVehicleInfo();
	}

	public boolean addVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		return vehicleManager.addVehicle(vo);
	}

	public boolean deleteVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		return vehicleManager.deleteVehicle(vo);
	}

	public boolean modifyVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		return vehicleManager.modifyVehicle(vo);
	}

	public ArrayList<DriverVO> getDriverInfos() {
		return vehicleManager.getDriverInfos();
	}

	public ArrayList<OrganizationVO> getOrganizationInfos() {
		return vehicleManager.getOrganizationInfos();
	}
	
	public int getNumOfVehicle(){
		return vehicleManager.getNumOfVehicle();
	}

}
