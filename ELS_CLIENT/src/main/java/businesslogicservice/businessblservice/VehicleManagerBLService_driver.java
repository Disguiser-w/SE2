package businesslogicservice.businessblservice;

import vo.VehicleVO;

public class VehicleManagerBLService_driver {
	public void drive(VehicleManagerBLService vehicleManagerBLService) {
		vehicleManagerBLService.getVehicleInfo();

		

		vehicleManagerBLService.addVehicle(null);


		vehicleManagerBLService.deleteVehicle(null);


		vehicleManagerBLService.modifyVehicle(null);
	}

	public void main(String[] args) {
		(new VehicleManagerBLService_driver()).drive(new VehicleManagerBLService_stub());
	}
}
