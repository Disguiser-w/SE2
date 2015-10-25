package businesslogic.businessbl;

public class TruckManagerBL_driver {
	public void drive(TruckManagerBLService truckManagerBLService) {
		truckManagerBLService.autoTruckLoading();

		truckManagerBLService.getFreeVehicles();

		truckManagerBLService.getTransferOrders();
	}

	public void main(String[] args) {
		(new TruckManagerBL_driver()).drive(new TruckManagerBL_stub());
	}
}
