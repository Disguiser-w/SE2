package businesslogicservice.businessblservice;

import vo.OrderAcceptReceiptVO;

/**
 * 营业厅业务员-接收货物
 */
public interface AcceptCargoBLService {
	/**
	 * 系统根据所有的订单号生成营业厅接收单，并更新到营业厅到达单列表，并返回更新结果
	 */
	public boolean acceptCargo(OrderAcceptReceiptVO vo);
}
