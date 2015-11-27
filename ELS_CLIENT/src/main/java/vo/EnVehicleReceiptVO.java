package vo;

import java.util.ArrayList;

/**
 * 营业厅到达单
 */
public class EnVehicleReceiptVO {
	public final OrganizationVO placeOfDeparture;
	public final String time;
	public final VehicleVO vehicleVO;
	public final ArrayList<String> OrderVOList;
	public final String receiptID;

	public EnVehicleReceiptVO(OrganizationVO placeOfDeparture, String time, VehicleVO vehicleVO,
			ArrayList<String> OrderVOList, String receiptID) {
		this.placeOfDeparture = placeOfDeparture;
		this.time = time;
		this.vehicleVO = vehicleVO;
		this.OrderVOList = OrderVOList;
		this.receiptID = receiptID;

	}
}