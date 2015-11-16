package businesslogic.businessbl;

import java.util.ArrayList;

import dataservice.businessdataservice.BusinessDataService;
import vo.OrderVO;
import vo.VehicleVO;

public class EnVehicle {
	
	private BusinessDataService businessData;
	public EnVehicle() {

	}

	public String autoTruckLoading() {
		// TODO Auto-generated method stub
		return "分配完成";
	}

	public ArrayList<VehicleVO> getFreeVehicles() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<OrderVO> getTransferOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
