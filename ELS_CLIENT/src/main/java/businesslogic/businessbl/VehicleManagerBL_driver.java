package businesslogic.businessbl;

import java.util.ArrayList;

public class VehicleManagerBL_driver {
	public void drive(VehicleManagerBLService vehicleManagerBLService) {
		vehicleManagerBLService.getVehicleInfo(new String());


		vehicleManagerBLService.addVehicle(new VehicleVO());


		vehicleManagerBLService.deleteVehicle(new VehicleVO());


		vehicleManagerBLService.modifyVehicle(new VehicleVO())
	}

	public void main(String[] args) {
		(new VehicleManagerBL_driver()).drive(new VehicleManagerBL_stub());
	}
}
