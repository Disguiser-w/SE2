package dataservice.expressdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.ExpressPO;
import po.OrderPO;
import type.OrderState;

/**
 * 快递员数据
 */
public interface ExpressDataService extends Remote {
	// 根据OrganizationID添加订单到今日订单文件中,时间自动生成，在total.dat(使用AVL树，（存放键值对，OrderID为键,路径为值）)中添加一个副本，来根据ID快速查找Organization和time
	public boolean addOrder(OrderPO po, String organizationID) throws RemoteException;

	// 通过营业厅ID和快递员ID获取快递员信息,如果organization为null就查找所有营业厅的快递员文件
	public ExpressPO getExpressInfo(String organizationID, String expressID) throws RemoteException;

	// 获得所有快递员信息
	public ArrayList<ExpressPO> getExpressInfos(String organizationID) throws RemoteException;

	// 更新chargeCollection
	public boolean updateChargeCollection(String organizationID, String expressId, ArrayList<String> chargeCollection)
			throws RemoteException;

	// 查找,在total.dat中查找机构和时间，然后就可以继续在文件中查找详细信息
	public OrderPO find(String ID) throws RemoteException;

	// ，以及该订单在ExpressPO中的状态（从PendingList移动到FinishedList）
	// 在po的时间对应的文件中查找
	public boolean receiptOrder(String organizationID, String expressId, OrderPO po) throws RemoteException;

	// 在快递员的SubmitOrderID中增加一项OrderID
	public boolean addSubmitOrder(String organizationID, String expressId, String orderID) throws RemoteException;

	// 在expressPO的pendingList中增加一项
	public boolean addPendingOrder(String organizationID, String expressId, String orderID) throws RemoteException;

	// 改变订单状态,先找再改
	public boolean changeState(OrderState orderState, String orderID) throws RemoteException;

	// 将某OrderPO直接加到某营业厅某日的订单列表中
	public boolean addDistributingOrder(ArrayList<OrderPO> po, String organizationID) throws RemoteException;
	// 将

	// 查询某个营业厅某天的订单
	public ArrayList<OrderPO> getOrderInfosByTime(String organizationID, String time) throws RemoteException;

	// 增加某个订单的历史流程(此属性只放在添加该订单的营业厅的OrderPO的副本中，与total.dat中的订单位置对应，供查询所需),在该营业厅的昨天的订单中查找
	// 如果organizationID为null说明不知道属于哪个营业厅，于是调用find
	public boolean addHistory(String process, String organizationID, String orderID) throws RemoteException;

	// 获得本营业厅当日的订单数
	public int getOrderNum(String organizationID) throws RemoteException;
	
	//清空当日收费信息
	public boolean deleteChargeInfos(String organizationID) throws RemoteException;
	
	//获得待派送的所有订单
	public ArrayList<OrderPO> getDistributingOrder(String organizationID) throws RemoteException;
	// /**
	// * 返回订单费用的基本信息CostBasePO
	// */
	// public CostBasePO getBaseCost() throws RemoteException;
	//
	// /**
	// * 汇总快递员的收费
	// */
	// // public boolean chargeCollection(ExpressPO po) throws RemoteException;
	//
	// /**
	// * 返回快递员的信息ExpressPO
	// */
	// public ArrayList<ExpressPO> getExpressInfos() throws RemoteException;
	// /**
	// *
	// */
	//
	// /**
	// * 返回订单数
	// */
	// public int getOrderNum() throws RemoteException;
	//
	// /**
	// * 新增订单
	// */
	// public boolean addOrder(OrderPO po) throws RemoteException;
	//
	// /**
	// * 更新订单
	// */
	// public boolean update(OrderPO po) throws RemoteException;
	//
	// /**
	// * 更新快递员信息
	// */
	// public boolean update(ExpressPO po) throws RemoteException;
	//
	// /**
	// * 获得OrderPOs
	// */
	// public ArrayList<OrderPO> getOrderInfos() throws RemoteException;
	//
	// /**
	// * 快递员增加一个pending订单
	// */
	// public boolean addPendingOrder(OrderPO po, ExpressPO Epo) throws
	// RemoteException;
	//
	// /**
	// * 快递员删除一个pending订单，增加一个已完成的订单
	// **/
	// public boolean addFinishedOrder(OrderPO po, ExpressPO Epo) throws
	// RemoteException;
	//
	// /**
	// * 每一天接收的所有订单放一个文件
	// */
	// // public ArrayList<OrderPO> getOrderInfosByTime(String timeStr) throws
	// // RemoteException;
}
