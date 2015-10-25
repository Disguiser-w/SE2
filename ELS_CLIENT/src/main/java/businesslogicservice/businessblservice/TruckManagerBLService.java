package businesslogic.businessbl;

import java.util.ArrayList;

import vo.OrderVO;
import vo.VehicleVO;

/**
 * 营业厅业务员-装车管理
 */
public interface TruckManagerBLService {
	/**
	 * 系统根据空闲车辆的信息和待转发的订单自动分配订单，生成装车单，更新到装车单列表，并返回分配信息
	 */
	public String autoTruckLoading();

	/**
	 * 系统返回所有空闲车辆的信息
	 */
	public ArrayList<VehicleVO> getFreeVehicles();

	/**
	 * 系统返回所有待转发的订单信息
	 */
	public ArrayList<OrderVO> getTransferOrders();
}
