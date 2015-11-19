package businesslogic.businessbl.controller;

import businesslogic.businessbl.AcceptCargo;
import businesslogicservice.businessblservice.AcceptCargoBLService;
import vo.OrderAcceptReceiptVO;

public class AcceptCargoController implements AcceptCargoBLService{
	private AcceptCargo acceptCargo;
	public AcceptCargoController(){
		acceptCargo = new AcceptCargo();
	}
	
	public boolean acceptCargo(OrderAcceptReceiptVO vo) {
		// TODO Auto-generated method stub
		String pig = "sdfa";
		
		return acceptCargo.acceptCargo(vo);
		
	}

}
