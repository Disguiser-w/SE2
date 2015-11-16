package businessbl;

import java.util.ArrayList;

import businesslogic.businessbl.info.LogisticQueryInfo;
import dataservice.expressdataservice.ExpressDataService;
import vo.OrderVO;

public class MockLogisticQueryInfo extends LogisticQueryInfo {
	private ExpressDataService expressData;

	public MockLogisticQueryInfo() {

	}

	public ArrayList<OrderVO> getOrderInfos() {
		return new ArrayList<OrderVO>();
	}
}
