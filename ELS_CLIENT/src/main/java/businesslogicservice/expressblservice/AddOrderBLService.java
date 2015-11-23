package businesslogicservice.expressblservice;

import vo.OrderVO;

/**
 * 快递员-输入订单
 */
public interface AddOrderBLService {
	/**
	 * 系统添加订单到订到列表中，并返回更新结果
	 */
	public boolean addOrder(OrderVO vo);

	/**
	 * 自动计算运费和包装费
	 */
	public void calculateCost(OrderVO vo);
}
