package businesslogic.businessbl;

import vo.VehicleVO;

public class DriverManagerBL_driver {
	public void drive(DriverManagerBLService driverManagerBLService) {
		driverManagerBLService.getVehicleInfo(new String());

		driverManagerBLService.addVehicle(new VehicleVO());

		driverManagerBLService.deleteVehicle(new VehicleVO());

		driverManagerBLService.modifyVehicle(new VehicleVO());
	}

	public void main(String[] args) {
		(new DriverManagerBL_driver()).drive(new DriverManagerBL_stub());
	}
}
