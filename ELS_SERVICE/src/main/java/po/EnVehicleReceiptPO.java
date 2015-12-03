package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.CheckState;

/**
 * 营业厅装车单
 */
public class EnVehicleReceiptPO implements Serializable{
	private OrganizationPO placeOfDeparture;
	private String time;
	private VehiclePO vehiclePO;
	private ArrayList<String> OrderPOList;
	private String receiptID;
	private CheckState checkState;

	public CheckState getCheckState() {
		return checkState;
	}

	public void setCheckState(CheckState checkState) {
		this.checkState = checkState;
	}

	public EnVehicleReceiptPO() {
	}

	public EnVehicleReceiptPO(OrganizationPO organizationPO, String time, VehiclePO vehiclePO,
			ArrayList<String> OrderPOList, String receiptID) {
		this.placeOfDeparture = organizationPO;
		this.time = time;
		this.vehiclePO = vehiclePO;
		this.OrderPOList = OrderPOList;
		this.receiptID = receiptID;
	}

	public String getReceiptID() {
		return receiptID;
	}

	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
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