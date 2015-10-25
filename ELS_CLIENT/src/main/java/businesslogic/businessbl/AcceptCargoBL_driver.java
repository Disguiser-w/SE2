package businesslogic.businessbl;

import vo.OrderAcceptReceiptVO;

public class AcceptCargoBL_driver {
	public void drive(AcceptCargoBLService acceptCargoBLService) {
		acceptCargoBLService.acceptCargo(new OrderAcceptReceiptVO());
	}

	public void main(String[] args){
		(new AcceptCargoBL_driver()).drive(new AcceptCargoBL_stub());
	}
}
