package businesslogic.businessbl.info;

import java.util.ArrayList;

import dataservice.expressdataservice.ExpressDataService;
import vo.OrderVO;

public class LogisticQueryInfo {
	private ExpressDataService expressData;

	public ArrayList<OrderVO> getOrderInfos() {
		return new ArrayList<OrderVO>();
	}
}
