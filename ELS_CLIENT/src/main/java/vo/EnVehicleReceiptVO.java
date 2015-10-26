package vo;

import java.util.ArrayList;
/**
 * 营业厅到达单
 * */
public class EnVehicleReceiptVO {
	private OrganizationVO placeOfDeparture;
	private String time;
	private VehicleVO vehicleVO;
	private ArrayList<OrderVO> OrderVOList;

	public EnVehicleReceiptVO() {
	}

	public EnVehicleReceiptVO(VehicleVO vehicleVO, ArrayList<OrderVO> OrderVOList) {
		this.vehicleVO = vehicleVO;
		this.OrderVOList = OrderVOList;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public OrganizationVO getPlaceOfDeparture() {
		return placeOfDeparture;
	}

	public void setPlaceOfDeparture(OrganizationVO placeOfDeparture) {
		this.placeOfDeparture = placeOfDeparture;
	}

	public VehicleVO getVehicleVO() {
		return vehicleVO;
	}

	public void setVehicleVO(VehicleVO vehicleVO) {
		this.vehicleVO = vehicleVO;
	}

	public ArrayList<OrderVO> getOrderVOList() {
		return OrderVOList;
	}

	public void setOrderVOList(ArrayList<OrderVO> orderVOList) {
		OrderVOList = orderVOList;
	}
}