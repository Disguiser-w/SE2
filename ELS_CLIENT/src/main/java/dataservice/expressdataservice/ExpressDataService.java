package dataservice.expressdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CostBasePO;
import po.ExpressPO;
import po.OrderPO;
import type.OrderState;

/**
 * 快递员数据
 */
public interface ExpressDataService extends Remote {
	// 根据OrganizationID添加订单到今日订单文件中,时间自动生成，在total.dat(使用AVL树，（存放键值对，OrderID为键,路径为值）)中添加一个副本，来根据ID快速查找Organization和time
	public boolean addOrder(OrderPO po, String organizationID) throws RemoteException;

	// 在快递员的SubmitOrderID中增加一项OrderID
	public boolean addSubmitOrder(String organizationID, String expressId, String orderID) throws RemoteException;

	// 通过营业厅ID和快递员ID获取快递员信息,如果organization为null就查找所有营业厅的快递员文件
	public ExpressPO getExpressInfo(String organizationID, String expressID);

	// 更新chargeCollection
	public boolean updateChargeCollection(String organizationID, String expressId, ArrayList<String> chargeCollection)
			throws RemoteException;

	// 查找,在total.dat中查找机构和时间，然后就可以继续在文件中查找详细信息
	public OrderPO find(String ID) throws RemoteException;

	// 更新改订单的状态（改成了finished），以及该订单在ExpressPO中的状态（从PendingList移动到FinishedList）
	// 在po的时间对应的文件中查找
	public boolean receiptOrder(String organizationID, String expressId, OrderPO po);

	//改变订单状态,先找再改
	public boolean changeState(OrderState orderState, String orderID);
	
	//

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
	 * 
	 */

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

	/**
	 * 快递员增加一个pending订单
	 */
	public boolean addPendingOrder(OrderPO po, ExpressPO Epo) throws RemoteException;

	/**
	 * 快递员删除一个pending订单，增加一个已完成的订单
	 **/
	public boolean addFinishedOrder(OrderPO po, ExpressPO Epo) throws RemoteException;

	/**
	 * 每一天接收的所有订单放一个文件
	 */
	// public ArrayList<OrderPO> getOrderInfosByTime(String timeStr) throws
	// RemoteException;
}
