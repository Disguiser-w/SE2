package businesslogicservice.businessblservice;

import vo.OrderAcceptReceiptVO;

public class AcceptCargoBLService_driver {
	public void drive(AcceptCargoBLService acceptCargoBLService) {
		acceptCargoBLService.acceptCargo(new OrderAcceptReceiptVO());
	}

	public void main(String[] args){
		(new AcceptCargoBLService_driver()).drive(new AcceptCargoBLService_stub());
	}
}
