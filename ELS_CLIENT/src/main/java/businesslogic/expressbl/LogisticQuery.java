package businesslogic.expressbl;

import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import vo.OrderVO;

public class LogisticQuery {
	private ExpressDataService expressData;

	public LogisticQuery() {
		// RMI
		expressData = new ExpressDataService_stub();
	}

	public OrderVO query(String orderID) {
		// TODO Auto-generated method stub
		OrderVO vo = null;
		try {
			vo = AddOrder.poToVO(expressData.find(orderID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;

	}

}
