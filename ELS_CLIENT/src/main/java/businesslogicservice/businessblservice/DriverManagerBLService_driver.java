package businesslogicservice.businessblservice;

import vo.VehicleVO;

public class DriverManagerBLService_driver {
	public void drive(DriverManagerBLService driverManagerBLService) {
		driverManagerBLService.getVehicleInfo(new String());

		driverManagerBLService.addVehicle(new VehicleVO());

		driverManagerBLService.deleteVehicle(new VehicleVO());

		driverManagerBLService.modifyVehicle(new VehicleVO());
	}

	public void main(String[] args) {
		(new DriverManagerBLService_driver()).drive(new DriverManagerBLService_stub());
	}
}
