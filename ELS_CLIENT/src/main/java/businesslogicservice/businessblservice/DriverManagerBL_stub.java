package businesslogicservice.businessblservice;

import java.util.ArrayList;

import vo.VehicleVO;

public class DriverManagerBL_stub implements DriverManagerBLService {
	public ArrayList<VehicleVO> getVehicleInfo(String ID) {
		// TODO Auto-generated method stub
		System.out.println("Show VehicleVOs!");
		return null;
	}
	public boolean addVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		System.out.println("AddVehicle successfully!");
		return false;
	}
	public boolean deleteVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		System.out.println("DeleteVehicle successfully!");
		return false;
	}
	public boolean modifyVehicle(VehicleVO vo) {
		// TODO Auto-generated method stub
		System.out.println("ModifyVehicle successfully!");
		return false;
	}
}
