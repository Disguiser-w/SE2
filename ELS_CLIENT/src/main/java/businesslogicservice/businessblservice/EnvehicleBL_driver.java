package businesslogicservice.businessblservice;

public class EnvehicleBL_driver {
	public void drive(EnVehicleBLService truckManagerBLService) {
		truckManagerBLService.autoTruckLoading();

		truckManagerBLService.getFreeVehicles();

		truckManagerBLService.getTransferOrders();
	}

	public void main(String[] args) {
		(new EnvehicleBL_driver()).drive(new EnvehicleBL_stub());
	}
}
