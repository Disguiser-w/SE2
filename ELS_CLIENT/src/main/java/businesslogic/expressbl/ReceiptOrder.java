package businesslogic.expressbl;

import businesslogic.expressbl.controller.ExpressMainController;
import dataservice.expressdataservice.ExpressDataService;
import po.OrderPO;
import type.OrderState;
import vo.ExpressVO;
import vo.OrderVO;

public class ReceiptOrder {

	//
	public OrderVO getOrderInfo(String orderID) {
		ExpressMainController.updateExpressInfo();
		// TODO Auto-generated method stub
		OrderVO vo = null;
		try {
			vo = ExpressMainController.orderPOToVO(ExpressMainController.expressData.find(orderID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public boolean receiptOrder(OrderVO vo) {
		ExpressMainController.updateExpressInfo();
		ExpressDataService expressData = ExpressMainController.expressData;
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			// 此po原来在该快递员的pendingList中，现在要移动到finishedList中
			OrderPO po = ExpressMainController.orderVOToPO(vo);
			expressData.changeState(OrderState.FINISHED, po.getID());
			expressData.addHistory("订单已签收", null, po.getID());

			//
			ExpressVO expressVO = ExpressMainController.expressVO;
			result = expressData.receiptOrder(expressVO.organization.organizationID, expressVO.ID,
					po);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
