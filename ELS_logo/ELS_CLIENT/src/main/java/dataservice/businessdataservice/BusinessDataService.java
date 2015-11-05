package dataservice.businessdataservice;

import java.util.ArrayList;

import po.DriverPO;
import po.EnVehicleReceiptPO;
import po.OrderPO;
import po.VehiclePO;

/**
 * BusinessData
 */
public interface BusinessDataService {
	/**
	 * 返回车辆信息列表
	 */
	public ArrayList<VehiclePO> getVehicleInfos();

	/**
	 * 增加一个车辆信息VehiclePO到VehiclePOList中
	 */
	public boolean addVehicle(VehiclePO po);

	/**
	 * 删除VehiclePOList中的一个车辆信息VehiclePO
	 */
	public boolean deleteVehicle(VehiclePO po);

	/**
	 * 修改VehiclePOList中的一个车辆信息VehiclePO
	 */
	public boolean modifyVehicle(VehiclePO po);

	/**
	 * 返回司机信息列表
	 */
	public DriverPO getDriverInfos(String ID);

	/**
	 * 增加一个司机信息DriverPO到DriverPOList中
	 */
	public boolean addDriver(DriverPO po);

	/**
	 * 删除DriverPOList中的一个车辆信息DriverPO
	 */
	public boolean deleteDriver(DriverPO po);

	/**
	 * 修改DriverPOList中的一个车辆信息DriverPO
	 */
	public boolean modifyDriver(DriverPO po);

	/**
	 * 返回待转运的订单的列表
	 */
	public ArrayList<OrderPO> getTransferOrders();

	/**
	 * 返回待派送的订单的列表
	 */
	public ArrayList<VehiclePO> getFreeVehicles();
	/**
	 * 生成装车单
	 * */
	public boolean addEnVehicleReceiptPO(EnVehicleReceiptPO po);
	
}
