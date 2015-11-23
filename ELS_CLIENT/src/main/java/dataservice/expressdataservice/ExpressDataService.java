package dataservice.expressdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import po.CostBasePO;
import po.ExpressPO;
import po.OrderPO;

/**
 * 快递员数据
 */
public interface ExpressDataService extends Remote{
	/**
	 * 快递员信息
	 * */
	public ExpressPO getExpressInfo(String ID) throws RemoteException;
	/**
	 * 返回订单费用的基本信息CostBasePO
	 */
	public CostBasePO getBaseCost() throws RemoteException;

	/**
	 * 汇总快递员的收费
	 */
	public boolean chargeCollection(ExpressPO po) throws RemoteException;

	/**
	 * 返回快递员的信息ExpressPO
	 */
	public ExpressPO getExpressInfos() throws RemoteException;

	/**
	 * 返回订单数
	 */
	public int getOrderNum() throws RemoteException;
	/**
	 * 新增订单　
	 * */
	public boolean addOrder(OrderPO po) throws RemoteException;
}
