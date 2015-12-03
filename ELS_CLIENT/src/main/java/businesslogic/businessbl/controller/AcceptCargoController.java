package businesslogic.businessbl.controller;

import java.util.ArrayList;

import businesslogic.businessbl.AcceptCargo;
import businesslogicservice.businessblservice.AcceptCargoBLService;

public class AcceptCargoController implements AcceptCargoBLService{
	private AcceptCargo acceptCargo;
	public AcceptCargoController(){
		acceptCargo = new AcceptCargo();
	}
	
	public boolean acceptCargo(String organizationID, String vehicleID, ArrayList<String> orderIDs) {
		// TODO Auto-generated method stub
		return acceptCargo.acceptCargo(organizationID,vehicleID,orderIDs);
		
	}

}
