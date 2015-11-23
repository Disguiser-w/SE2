package po;

import java.util.ArrayList;

import vo.OrganizationVO;

public class OrderAcceptReceiptPO {
	private OrganizationVO local;
	private String time;
	private VehiclePO vehiclePO;
	private ArrayList<OrderPO> orderPOList;

	public OrderAcceptReceiptPO() {
	}

	public OrderAcceptReceiptPO(OrganizationVO local, String time, VehiclePO vehiclePO,
			ArrayList<OrderPO> orderPOList) {
		this.local = local;
		this.time = time;
		this.vehiclePO = vehiclePO;
		this.orderPOList = orderPOList;
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

	public ArrayList<OrderPO> getOrderPOList() {
		return orderPOList;
	}

	public void setOrderPOList(ArrayList<OrderPO> orderPOList) {
		this.orderPOList = orderPOList;
	}
}
