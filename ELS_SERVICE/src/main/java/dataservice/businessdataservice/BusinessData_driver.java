package dataservice.businessdataservice;

import java.util.ArrayList;

public class BusinessData_driver {
	public void drive(BusinessDataService businessDataService) {
		businessDataService.getVehicleInfos();
		businessDataService.addVehicle(new VehiclePO());
		businessDataService.deleteVehicle(new VehiclePO());
		businessDataService.modifyVehicle(new VehiclePO());
		businessDataService.getDriverInfos(new String());
		businessDataService.addDriver(new DriverPO());
		businessDataService.deleteDriver(new Driver());
		businessDataService.modifyDriver(new DriverPO());
		businessDataService.getTransferOrders();
		businessDataService.getFreeVehicles();
	}

	public void main(String[] args) {
		(new BusinessData_driver()).drive(new BusinessData_stub());
	}
}
