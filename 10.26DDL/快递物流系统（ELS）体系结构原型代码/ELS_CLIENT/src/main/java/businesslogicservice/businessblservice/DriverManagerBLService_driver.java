package businesslogicservice.businessblservice;

import vo.DriverVO;

public class DriverManagerBLService_driver {
	public void drive(DriverManagerBLService driverManagerBLService) {
		driverManagerBLService.getDriverInfo(new String());

		driverManagerBLService.addDriver(new DriverVO());

		driverManagerBLService.deleteDriver(new DriverVO());

		driverManagerBLService.modifyDriver(new DriverVO());
	}

	public void main(String[] args) {
		(new DriverManagerBLService_driver()).drive(new DriverManagerBLService_stub());
	}
}
