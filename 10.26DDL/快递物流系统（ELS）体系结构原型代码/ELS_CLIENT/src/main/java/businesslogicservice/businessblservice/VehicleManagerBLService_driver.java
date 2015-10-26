package businesslogicservice.businessblservice;

import vo.VehicleVO;

public class VehicleManagerBLService_driver {
	public void drive(VehicleManagerBLService vehicleManagerBLService) {
		vehicleManagerBLService.getVehicleInfo(new String());


		vehicleManagerBLService.addVehicle(new VehicleVO());


		vehicleManagerBLService.deleteVehicle(new VehicleVO());


		vehicleManagerBLService.modifyVehicle(new VehicleVO());
	}

	public void main(String[] args) {
		(new VehicleManagerBLService_driver()).drive(new VehicleManagerBLService_stub());
	}
}
