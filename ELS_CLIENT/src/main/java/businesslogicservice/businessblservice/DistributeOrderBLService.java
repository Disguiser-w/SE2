package businesslogicservice.businessblservice;

import java.util.ArrayList;

import vo.ExpressVO;
import vo.OrderVO;

/**
 * 营业厅业务员-派件
 * */
public interface DistributeOrderBLService {
	/**
	 * 系统自动分配待派送的订单给快递员，并返回更新结果
	 * */
	public String distributeOrder();
	/**
	 * 返回该营业厅所有快递员的信息
	 * */
	public ArrayList<ExpressVO> getExpressInfos();
	/**
	 * 返回待派送的所有订单的信息
	 * */
	public ArrayList<OrderVO> getSendOrder();
}
