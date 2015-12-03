package vo;

import java.util.ArrayList;

import type.ReceiptState;

/**
 * 营业厅到达单
 */
public class EnVehicleReceiptVO {
	public final OrganizationVO placeOfDeparture;
	public final String time;
	public final VehicleVO vehicleVO;
	public final ArrayList<String> OrderVOList;
	public final String receiptID;
	public final ReceiptState receiptState;

	public EnVehicleReceiptVO(OrganizationVO placeOfDeparture, String time, VehicleVO vehicleVO,
			ArrayList<String> OrderVOList, String receiptID,ReceiptState receiptState) {
		this.placeOfDeparture = placeOfDeparture;
		this.time = time;
		this.vehicleVO = vehicleVO;
		this.OrderVOList = OrderVOList;
		this.receiptID = receiptID;
		this.receiptState = receiptState;

	}
}