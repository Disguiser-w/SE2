package vo;

import java.util.ArrayList;

import type.CheckState;

public class OrderAcceptReceiptVO {
	public final OrganizationVO local;
	public final String time;
	public final VehicleVO vehicleVO;
	public final ArrayList<String> orderIDs;
	public final String receiptID;
	public final CheckState checkState;

	public OrderAcceptReceiptVO(OrganizationVO local, String time, VehicleVO vehicleVO, ArrayList<String> orderIDs,String receiptID,CheckState checkState) {
		this.local = local;
		this.time = time;
		this.vehicleVO = vehicleVO;
		this.orderIDs = orderIDs ;
		this.receiptID = receiptID;
		this.checkState = checkState;
	}
}