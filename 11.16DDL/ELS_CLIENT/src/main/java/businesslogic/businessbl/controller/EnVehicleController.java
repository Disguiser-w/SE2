package businesslogic.businessbl.controller;

import java.util.ArrayList;

import businesslogic.businessbl.EnVehicle;
import businesslogicservice.businessblservice.EnVehicleBLService;
import vo.OrderVO;
import vo.VehicleVO;

public class EnVehicleController implements EnVehicleBLService {

	private EnVehicle enVehicle;

	public EnVehicleController() {
		enVehicle = new EnVehicle();
	}

	public String autoTruckLoading() {
		// TODO Auto-generated method stub
		return enVehicle.autoTruckLoading();
	}

	public ArrayList<VehicleVO> getFreeVehicles() {
		// TODO Auto-generated method stub
		return enVehicle.getFreeVehicles();
	}

	public ArrayList<OrderVO> getTransferOrders() {
		// TODO Auto-generated method stub
		return enVehicle.getTransferOrders();
	}

}
