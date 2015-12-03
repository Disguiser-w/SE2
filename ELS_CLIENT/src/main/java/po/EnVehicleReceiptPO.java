package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.ReceiptState;

/**
 * 营业厅装车单
 */
public class EnVehicleReceiptPO implements Serializable {
	private OrganizationPO placeOfDeparture;
	private String time;
	private VehiclePO vehiclePO;
	private ArrayList<String> OrderPOList;
	private String receiptID;
	private ReceiptState receiptState;

	public EnVehicleReceiptPO(OrganizationPO organizationPO, String time, VehiclePO vehiclePO,
			ArrayList<String> OrderPOList, String receiptID, ReceiptState receiptState) {
		this.placeOfDeparture = organizationPO;
		this.time = time;
		this.vehiclePO = vehiclePO;
		this.OrderPOList = OrderPOList;
		this.receiptID = receiptID;
		this.receiptState = receiptState;
	}

	public ReceiptState getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(ReceiptState receiptState) {
		this.receiptState = receiptState;
	}

	public EnVehicleReceiptPO() {
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