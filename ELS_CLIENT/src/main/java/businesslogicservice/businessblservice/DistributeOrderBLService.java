package businesslogicservice.businessblservice;

import java.util.ArrayList;

import vo.ExpressVO;
import vo.OrderVO;

/**
 * 营业厅业务员-派件
 */
public interface DistributeOrderBLService {
	/**
	 * 系统自动分配待派送的订单给快递员，并返回更新结果
	 */
	public ArrayList<String> distributeOrder();

}
