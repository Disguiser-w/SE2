package businesslogicservice.businessblservice;

import java.util.ArrayList;

import vo.OrderVO;
import vo.VehicleVO;

public interface EnVehicleBLService {

	public String autoTruckLoading();

	public ArrayList<VehicleVO> getFreeVehicles();

	public ArrayList<OrderVO> getTransferOrders();
}
