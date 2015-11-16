package businesslogic.expressbl.controller;

import businesslogic.expressbl.LogisticQuery;
import businesslogicservice.expressblservice.LogisticQueryBLService;
import vo.OrderVO;

public class LogisticQueryController implements LogisticQueryBLService {
	private LogisticQuery logisticQuery;

	public LogisticQueryController() {
		logisticQuery = new LogisticQuery();
	}

	public OrderVO query(String orderID) {
		// TODO Auto-generated method stub
		return logisticQuery.query(orderID);
	}

}
