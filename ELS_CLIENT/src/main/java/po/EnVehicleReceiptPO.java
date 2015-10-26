package po;

import java.util.ArrayList;
/**
 * 营业厅到达单
 * */
public class EnVehicleReceiptPO {
	private OrganizationPO placeOfDeparture;
	private String time;
	private VehiclePO vehiclePO;
	private ArrayList<OrderPO> OrderPOList;

	public EnVehicleReceiptPO() {
	}

	public EnVehicleReceiptPO(VehiclePO vehiclePO, ArrayList<OrderPO> OrderPOList) {
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

	public ArrayList<OrderPO> getOrderPOList() {
		return OrderPOList;
	}

	public void setOrderPOList(ArrayList<OrderPO> orderVOList) {
		OrderVOList = orderVOList;
	}
}