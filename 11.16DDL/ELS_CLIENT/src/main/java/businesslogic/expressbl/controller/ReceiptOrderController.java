package businesslogic.expressbl.controller;

import businesslogic.expressbl.ReceiptOrder;
import businesslogicservice.expressblservice.ReceiptOrderBLService;
import vo.OrderVO;

public class ReceiptOrderController implements ReceiptOrderBLService {

	private ReceiptOrder receiptOrder;

	public ReceiptOrderController() {
		receiptOrder = new ReceiptOrder();
	}

	public OrderVO getOrderInfo(long orderNum) {
		// TODO Auto-generated method stub
		return receiptOrder.getOrderInfo(orderNum);
	}

	public boolean receiptOrder(OrderVO vo) {
		// TODO Auto-generated method stub
		return receiptOrder.receiptOrder(vo);
	}

}
