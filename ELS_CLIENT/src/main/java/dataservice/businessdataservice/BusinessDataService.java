package dataservice.businessdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BusinessPO;
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
	// 根据营业厅ID（找到文件）和营业厅业务员ID（查找文件内容）,ID为null就返回第一个
	public BusinessPO getBusinessInfo(String organizationID, String ID) throws RemoteException;

	// 根据营业厅ID和车辆ID查询车辆PO
	public VehiclePO getVehicleInfo(String organizationID, String vehicleID) throws RemoteException;

	// 营业厅每日一个，每日早上8点发货一次
	public boolean addReceipt(String organizationID, OrderAcceptReceiptPO po) throws RemoteException;

	// 获得某营业厅的全部车辆信息
	public ArrayList<VehiclePO> getVehicleInfos(String organizationID) throws RemoteException;

	// 添加装车单到今日装车单文件中
	public boolean addEnVehicleReceipt(String organizationID, ArrayList<EnVehicleReceiptPO> pos) throws RemoteException;

	// 增加一个车辆信息VehiclePO到VehiclePOList中
	public boolean addVehicle(VehiclePO po) throws RemoteException;

	// 删除VehiclePOList中的一个车辆信息VehiclePO
	public boolean deleteVehicle(VehiclePO po) throws RemoteException;

	// 修改VehiclePOList中的一个车辆信息VehiclePO
	public boolean modifyVehicle(VehiclePO po) throws RemoteException;

	// 返回司机信息列表
	public ArrayList<DriverPO> getDriverInfos(String organizationID) throws RemoteException;

	/**
	 * 查找司机
	 */
	public DriverPO getDriverInfo(String ID) throws RemoteException;

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
	//
	// /**
	// * 返回待转运的订单的列表
	// */
	// public ArrayList<OrderPO> getTransferOrders() throws RemoteException;
	//
	// /**
	// * 返回待派送的订单的列表
	// */
	// public ArrayList<VehiclePO> getFreeVehicles() throws RemoteException;
	//
	// /**
	// * 生成装车单
	// */
	// public boolean addEnVehicleReceiptPO(EnVehicleReceiptPO po) throws
	// RemoteException;
	//
	// /**
	// * 获取收款汇总单
	// */
	// public ArrayList<GatheringReceiptPO> getGatheringReceiptPOs() throws
	// RemoteException;
	//
	// /**
	// * 增加收货单
	// */
	// public boolean addReceipt(OrderAcceptReceiptPO po) throws
	// RemoteException;
	//
	// /**
	// * 增加收款汇总单
	// */
	//
	// public boolean addGatheringReceipt(GatheringReceiptPO po);

}
