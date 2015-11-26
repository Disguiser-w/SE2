package po;

import java.util.ArrayList;

import vo.OrganizationVO;

public class OrderAcceptReceiptPO {
	private OrganizationVO local;
	private String time;
	private VehiclePO vehiclePO;
	private ArrayList<String> orderIDs;
	public OrderAcceptReceiptPO() {
	}

	public OrderAcceptReceiptPO(OrganizationVO local, String time, VehiclePO vehiclePO, ArrayList<String> orderIDs) {
		this.local = local;
		this.time = time;
		this.vehiclePO = vehiclePO;
		this.orderIDs = orderIDs;
	}

	public OrganizationVO getLocal() {
		return local;
	}

	public void setLocal(OrganizationVO local) {
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
