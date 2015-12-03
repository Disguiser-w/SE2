package po;

import java.io.Serializable;
import java.util.ArrayList;

import type.CheckState;

public class OrderAcceptReceiptPO implements Serializable{
	private OrganizationPO local;
	private String time;
	private VehiclePO vehiclePO;
	private ArrayList<String> orderIDs;
	private String receiptID;
	private CheckState checkState;

	public OrderAcceptReceiptPO(OrganizationPO local, String time,
			VehiclePO vehiclePO, ArrayList<String> orderIDs, String receiptID) {
		this.local = local;
		this.time = time;
		this.vehiclePO = vehiclePO;
		this.orderIDs = orderIDs;
		this.receiptID = receiptID;
		this.checkState = CheckState.UNCHECKED;
	}

	public CheckState getCheckState() {
		return checkState;
	}

	public void setCheckState(CheckState checkState) {
		this.checkState = checkState;
	}

	public String getReceiptID() {
		return receiptID;
	}

	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}

	public OrganizationPO getLocal() {
		return local;
	}

	public void setLocal(OrganizationPO local) {
		this.local = local;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public VehiclePO getVehiclePO() {
		return vehiclePO;
	}

	public void setVehiclePO(VehiclePO vehiclePO) {
		this.vehiclePO = vehiclePO;
	}

	public ArrayList<String> getOrderIDs() {
		return orderIDs;
	}

	public void setOrderIDs(ArrayList<String> orderIDs) {
		this.orderIDs = orderIDs;
	}

}
