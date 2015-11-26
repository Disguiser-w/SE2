package po;

import java.util.ArrayList;

/**
 * 营业厅装车单
 */
public class EnVehicleReceiptPO {
	private OrganizationPO placeOfDeparture;
	private String time;
	private VehiclePO vehiclePO;
	private ArrayList<String> OrderPOList;

	public EnVehicleReceiptPO() {
	}

	public EnVehicleReceiptPO(OrganizationPO organizationPO, String time, VehiclePO vehiclePO,
			ArrayList<String> OrderPOList) {
		this.placeOfDeparture = placeOfDeparture;
		this.time = time;
		this.vehiclePO = vehiclePO;
		this.OrderPOList = OrderPOList;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public OrganizationPO getPlaceOfDeparture() {
		return placeOfDeparture;
	}

	public void setPlaceOfDeparture(OrganizationPO placeOfDeparture) {
		this.placeOfDeparture = placeOfDeparture;
	}

	public VehiclePO getVehiclePO() {
		return vehiclePO;
	}

	public void setVehiclePO(VehiclePO vehiclePO) {
		this.vehiclePO = vehiclePO;
	}

	public ArrayList<String> getOrderPOList() {
		return OrderPOList;
	}

	public void setOrderPOList(ArrayList<String> orderPOList) {
		this.OrderPOList = orderPOList;
	}
}