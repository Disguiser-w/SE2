package dataservice.businessdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.DriverPO;
import po.EnVehicleReceiptPO;
import po.GatheringReceiptPO;
import po.OrderAcceptReceiptPO;
import po.OrderPO;
import po.VehiclePO;

/**
 * BusinessData
 */
public interface BusinessDataService extends Remote {
	/**
	 * 返回车辆信息列表
	 */
	public ArrayList<VehiclePO> getVehicleInfos() throws RemoteException;

	/**
	 * 增加一个车辆信息VehiclePO到VehiclePOList中
	 */
	public boolean addVehicle(VehiclePO po) throws RemoteException;

	/**
	 * 删除VehiclePOList中的一个车辆信息VehiclePO
	 */
	public boolean deleteVehicle(VehiclePO po) throws RemoteException;

	/**
	 * 修改VehiclePOList中的一个车辆信息VehiclePO
	 */
	public boolean modifyVehicle(VehiclePO po) throws RemoteException;

	/**
	 * 返回司机信息列表
	 */
	public DriverPO getDriverInfos(String ID) throws RemoteException;

	/**
	 * 增加一个司机信息DriverPO到DriverPOList中
	 */
	public boolean addDriver(DriverPO po) throws RemoteException;

	/**
	 * 删除DriverPOList中的一个车辆信息DriverPO
	 */
	public boolean deleteDriver(DriverPO po) throws RemoteException;

	/**
	 * 修改DriverPOList中的一个车辆信息DriverPO
	 */
	public boolean modifyDriver(DriverPO po) throws RemoteException;

	/**
	 * 返回待转运的订单的列表
	 */
	public ArrayList<OrderPO> getTransferOrders() throws RemoteException;

	/**
	 * 返回待派送的订单的列表
	 */
	public ArrayList<VehiclePO> getFreeVehicles() throws RemoteException;

	/**
	 * 生成装车单
	 */
	public boolean addEnVehicleReceiptPO(EnVehicleReceiptPO po) throws RemoteException;

	/**
	 * 生成收款汇总单
	 */
	public ArrayList<GatheringReceiptPO> getGatheringReceiptPOs() throws RemoteException;

	/**
	 * 增加收货单
	 */
	public boolean addReceipt(OrderAcceptReceiptPO po) throws RemoteException;
}
