package businesslogic.expressbl;

import java.util.ArrayList;

import businesslogic.datafactory.DataFactory;
import businesslogic.expressbl.controller.ExpressMainController;
import dataservice.expressdataservice.ExpressDataService;
import vo.OrderVO;

public class LogisticQuery {

	private ExpressDataService expressData;

	public LogisticQuery() {
		try {
			expressData = DataFactory.getExpressData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<OrderVO> query() {
		ExpressMainController.updateExpressInfo();

		// TODO Auto-generated method stub
		ArrayList<String> orderIDs = ExpressMainController.expressVO.submitedOrderID;
		ArrayList<OrderVO> vo = new ArrayList<OrderVO>();
		try {
			for (String i : orderIDs) {
				vo.add(ExpressMainController.orderPOToVO(expressData.find(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;

	}

}
