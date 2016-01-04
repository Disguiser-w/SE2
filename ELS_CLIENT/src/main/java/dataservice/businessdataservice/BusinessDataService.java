package dataservice.businessdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.BusinessPO;
import po.DistributeReceiptPO;
import po.DriverPO;
import po.EnVehicleReceiptPO;
import po.GatheringReceiptPO;
import po.OrderAcceptReceiptPO;
import po.OrganizationPO;
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
	public boolean addVehicle(String organizationID, VehiclePO po) throws RemoteException;

	// 删除VehiclePOList中的一个车辆信息VehiclePO
	public boolean deleteVehicle(String organizationID, VehiclePO po) throws RemoteException;

	// 修改VehiclePOList中的一个车辆信息VehiclePO
	public boolean modifyVehicle(String organizationID, VehiclePO po) throws RemoteException;

	// 返回本营业厅司机信息列表
	public ArrayList<DriverPO> getDriverInfos(String organizationID) throws RemoteException;

	// 增加一个GatheringReceipt到本营业厅今日的文件中，一天也就一个
	public boolean addGatheringReceipt(String organizationID, GatheringReceiptPO grp) throws RemoteException;

	// 获得今日本营业厅OrderAcceptReceiptPO的个数
	public int getNumOfOrderAcceptReceipt(String organizationID) throws RemoteException;

	// 返回规定日期的所有GatheringReceipt,time格式 2015-11-23
	public ArrayList<GatheringReceiptPO> getGatheringReceipt(String time) throws RemoteException;

	public ArrayList<GatheringReceiptPO> getGatheringReceiptByHallID(String organization) throws RemoteException;

	public ArrayList<GatheringReceiptPO> getGatheringReceiptByBoth(String organization, String time)
			throws RemoteException;

	// 增加一个DistributeOrder到本营业厅今日的文件中，一天也就一个
	public boolean addDistributeReceipt(String organizationID, DistributeReceiptPO po) throws RemoteException;

	// 查照死机
	public DriverPO getDriverInfo(String organizationID, String ID) throws RemoteException;

	// 增加一个司机到本营业厅
	public boolean addDriver(String organizationID, DriverPO po) throws RemoteException;

	// 删除一个司机到本营业厅
	public boolean deleteDriver(String organizationID, DriverPO po) throws RemoteException;

	// 修改本营业厅该司机信息
	public boolean modifyDriver(String organizationID, DriverPO po) throws RemoteException;

	/**
	 * Lizi 收款单
	 */
	public ArrayList<GatheringReceiptPO> getSubmittedGatheringReceiptInfo() throws RemoteException;

	/**
	 * Lizi 派件单
	 */
	public ArrayList<DistributeReceiptPO> getSubmittedDistributeReceiptInfo() throws RemoteException;

	/**
	 * Lizi 装车
	 */
	public ArrayList<EnVehicleReceiptPO> getSubmittedEnVehicleReceiptInfo() throws RemoteException;

	/**
	 * Lizi 到达单
	 */
	public ArrayList<OrderAcceptReceiptPO> getSubmittedOrderAcceptReceiptInfo() throws RemoteException;

	public void saveDistributeReceiptInfo(DistributeReceiptPO po) throws RemoteException;

	public void saveOrderAcceptReceiptInfo(OrderAcceptReceiptPO po) throws RemoteException;

	public void saveEnVehicleReceiptInfo(EnVehicleReceiptPO po) throws RemoteException;

	public void saveGatheringReceiptInfo(GatheringReceiptPO po) throws RemoteException;

	//
	public boolean addDriverTime(String organizationID, String driverID) throws RemoteException;

	public ArrayList<OrganizationPO> getOrganizationInfos() throws RemoteException;

	public int getNumOfVehicles(String organizationID) throws RemoteException;

	public int getNumOfDrivers(String organizationID) throws RemoteException;

	public int getNumOfEnVechileReceipt(String organizationID) throws RemoteException;

	public int getNumOfOrderReceipt(String organizationID) throws RemoteException;

	public int getNumOfOrderDistributeReceipt(String organizationID) throws RemoteException;
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
