package businesslogic.expressbl;

import java.util.ArrayList;

import businesslogic.expressbl.controller.ExpressMainController;
import vo.OrderVO;

public class LogisticQuery {

	public ArrayList<OrderVO> query() {
		ExpressMainController.updateExpressInfo();

		// TODO Auto-generated method stub
		ArrayList<String> orderIDs = ExpressMainController.expressVO.submitedOrderID;
		ArrayList<OrderVO> vo = null;
		try {
			for (String i : orderIDs)
				vo.add(ExpressMainController.orderPOToVO(ExpressMainController.expressData.find(i)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;

	}

}
