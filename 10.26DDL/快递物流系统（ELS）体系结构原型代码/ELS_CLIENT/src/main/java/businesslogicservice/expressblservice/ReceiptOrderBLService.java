package businesslogicservice.expressblservice;

import vo.OrderVO;

/**
 * 快递员-收费信息输入
 * */
public interface ReceiptOrderBLService {
	/**
	 * 系统根据输入的订单号返回对应的订单信息，如果不存在返回null
	 * */
	public OrderVO getOrderInfo(long orderNum);
	/**
	 * 系统将更新订单信息并返回更新结果
	 * */
	public boolean receiptOrder(OrderVO vo);
}
