package businesslogicservice.businessblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 营业厅业务员-派件
 */
public interface DistributeOrderBLService {
	/**
	 * 系统自动分配待派送的订单给快递员，并返回更新结果
	 * @throws RemoteException 
	 */
	public ArrayList<String> distributeOrder() throws RemoteException;

}
