package businesslogic.expressbl;

import dataservice.expressdataservice.ExpressDataService;
import dataservice.expressdataservice.ExpressDataService_stub;
import vo.OrderVO;

public class ReceiptOrder {
	private ExpressDataService expressData;

	public ReceiptOrder() {
		expressData = new ExpressDataService_stub();
	}

	public OrderVO getOrderInfo(String orderID) {
		// TODO Auto-generated method stub
		OrderVO vo = null;
		try {
			vo = AddOrder.poToVO(expressData.find(orderID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public boolean receiptOrder(OrderVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			result = expressData.update(AddOrder.voToPO(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
