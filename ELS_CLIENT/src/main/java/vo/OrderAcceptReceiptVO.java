package vo;

import java.util.ArrayList;

public class OrderAcceptReceiptVO {
	public final OrganizationVO local;
	public final String time;
	public final VehicleVO vehicleVO;
	public final ArrayList<String> orderIDs;

	public OrderAcceptReceiptVO(OrganizationVO local, String time, VehicleVO vehicleVO, ArrayList<String> orderIDs) {
		this.local = local;
		this.time = time;
		this.vehicleVO = vehicleVO;
		this.orderIDs = orderIDs ;
	}
}