package vo;

import java.util.ArrayList;

public class OrderAcceptReceiptVO {
	private OrganizationVO local;
	private String time;
	private VehicleVO vehicleVO;
	private ArrayList<OrderVO> orderVOList;

	public OrderAcceptReceiptVO() {
	}

	public OrderAcceptReceiptVO(OrganizationVO local, String time, VehicleVO vehicleVO,
			ArrayList<OrderVO> orderVOList) {
		this.local=local;
		this.time=time;
		this.vehicleVO=vehicleVO;
		this.orderVOList=orderVOList;
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

	public VehicleVO getVehicleVO() {
		return vehicleVO;
	}

	public void setVehicleVO(VehicleVO vehicleVO) {
		this.vehicleVO = vehicleVO;
	}

	public ArrayList<OrderVO> getOrderVOList() {
		return orderVOList;
	}

	public void setOrderVOList(ArrayList<OrderVO> orderVOList) {
		this.orderVOList = orderVOList;
	}
}
