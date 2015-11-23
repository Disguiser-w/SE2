package dataservice.expressdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CostBasePO;
import po.ExpressPO;
import po.OrderPO;

/**
 * 快递员数据
 */
public interface ExpressDataService extends Remote {
	/**
	 * 查找
	 */
	public OrderPO find(String ID) throws RemoteException;

	/**
	 * 快递员信息
	 */
	public ExpressPO getExpressInfo(String ID) throws RemoteException;

	/**
	 * 返回订单费用的基本信息CostBasePO
	 */
	public CostBasePO getBaseCost() throws RemoteException;

	/**
	 * 汇总快递员的收费
	 */
	// public boolean chargeCollection(ExpressPO po) throws RemoteException;

	/**
	 * 返回快递员的信息ExpressPO
	 */
	public ArrayList<ExpressPO> getExpressInfos() throws RemoteException;

	/**
	 * 返回订单数
	 */
	public int getOrderNum() throws RemoteException;

	/**
	 * 新增订单
	 */
	public boolean addOrder(OrderPO po) throws RemoteException;

	/**
	 * 更新订单
	 */
	public boolean update(OrderPO po) throws RemoteException;

	/**
	 * 更新快递员信息
	 */
	public boolean update(ExpressPO po) throws RemoteException;

	/**
	 * 获得OrderPOs
	 */
	public ArrayList<OrderPO> getOrderInfos() throws RemoteException;
}
