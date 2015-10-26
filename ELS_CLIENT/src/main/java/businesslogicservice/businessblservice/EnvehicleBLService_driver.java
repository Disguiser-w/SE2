package businesslogicservice.businessblservice;

public class EnvehicleBLService_driver {
	public void drive(EnVehicleBLService truckManagerBLService) {
		truckManagerBLService.autoTruckLoading();

		truckManagerBLService.getFreeVehicles();

		truckManagerBLService.getTransferOrders();
	}

	public void main(String[] args) {
		(new EnvehicleBLService_driver()).drive(new EnvehicleBLService_stub());
	}
}
