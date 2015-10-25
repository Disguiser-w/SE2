package dataservice.businessdataservice;

import java.util.ArrayList;

public class BusinessData_stub implements BusinessDataService {

	public ArrayList<VehiclePO> getVehicleInfos() {
		// TODO Auto-generated method stub
		System.out.println("Show VehiclePOList!");
		return null;
	}

	public boolean addVehicle(VehiclePO po) {
		// TODO Auto-generated method stub
		System.out.println("AddVehicle successfully!");
		return false;
	}

	public boolean deleteVehicle(VehiclePO po) {
		// TODO Auto-generated method stub
		System.out.println("DeleteVehicle successfully!");
		return false;
	}

	public boolean modifyVehicle(VehiclePO po) {
		// TODO Auto-generated method stub
		System.out.println("ModifyVehicle successfully!");
		return false;
	}

	public DriverPO getDriverInfos(String ID) {
		// TODO Auto-generated method stub
		System.out.println("Show DriverPO!");
		return null;
	}

	public boolean addDriver(DriverPO po) {
		// TODO Auto-generated method stub
		System.out.println("AddDriver successfully!");
		return false;
	}

	public boolean deleteDriver(DriverPO po) {
		// TODO Auto-generated method stub
		System.out.println("DeleteDriver successfully!");
		return false;
	}

	public boolean modifyDriver(DriverPO po) {
		// TODO Auto-generated method stub
		System.out.println("ModifyDriver successfully!");
		return false;
	}

	public ArrayList<OrderVO> getTransferOrders() {
		// TODO Auto-generated method stub
		System.out.println("Show OrderVOList!");
		return null;
	}

	public ArrayList<VehiclePO> getFreeVehicles() {
		// TODO Auto-generated method stub
		System.out.println("Show VehiclePOList!");
		return null;
	}

}
