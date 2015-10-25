package businesslogicservice.expressblservice;

import vo.OrderVO;

public class ReceiptOrderBL_driver {
	public void drive(ReceiptOrderBLService receiptOrderBLService) {
		receiptOrderBLService.getOrderInfo(1000);
		receiptOrderBLService.receiptOrder(new OrderVO());
	}

	public void main(String[] args) {
		(new ReceiptOrderBL_driver()).drive(new ReceiptOrderBL_stub());
	}
}
