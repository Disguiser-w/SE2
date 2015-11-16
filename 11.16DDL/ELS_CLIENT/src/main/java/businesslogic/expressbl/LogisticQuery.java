package businesslogic.expressbl;

import dataservice.expressdataservice.ExpressDataService;
import vo.OrderVO;

public class LogisticQuery {
	private ExpressDataService expressData;

	public LogisticQuery() {

	}

	public OrderVO query(String orderID) {
		// TODO Auto-generated method stub
		OrderVO vo = new OrderVO(orderID, "狗剩", orderID, orderID, orderID, orderID, orderID, orderID, orderID, orderID,
				orderID, 0, orderID, orderID, orderID, null, null, 0, 0, orderID, orderID, orderID);
		return vo;
	}

}
