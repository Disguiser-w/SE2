package businesslogic.expressbl.controller;

import java.util.ArrayList;

import businesslogic.expressbl.LogisticQuery;
import businesslogicservice.expressblservice.LogisticQueryBLService;
import vo.OrderVO;

public class LogisticQueryController implements LogisticQueryBLService {
	private LogisticQuery logisticQuery;

	public LogisticQueryController() {
		logisticQuery = new LogisticQuery();
	}

	public ArrayList<OrderVO> query() {
		// TODO Auto-generated method stub
		return logisticQuery.query();
	}

}
