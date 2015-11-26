package businesslogicservice.expressblservice;

import java.util.ArrayList;

import vo.OrderVO;

/**
 * 快递员-物流查询
 * */
public interface LogisticQueryBLService {
	/**
	 * 系统根据输入的订单号搜索并返回对应的订单信息，返回null说明订单不存在
	 * */
	public ArrayList<OrderVO> query();
}
