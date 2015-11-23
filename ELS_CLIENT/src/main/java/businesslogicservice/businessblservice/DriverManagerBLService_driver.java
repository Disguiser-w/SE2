package businesslogicservice.businessblservice;

import vo.DriverVO;

public class DriverManagerBLService_driver {
	public void drive(DriverManagerBLService driverManagerBLService) {
		driverManagerBLService.getDriverInfo("");

		driverManagerBLService.addDriver(null);

		driverManagerBLService.deleteDriver(null);

		driverManagerBLService.modifyDriver(null);
	}

	public void main(String[] args) {
		(new DriverManagerBLService_driver()).drive(new DriverManagerBLService_stub());
	}
}
