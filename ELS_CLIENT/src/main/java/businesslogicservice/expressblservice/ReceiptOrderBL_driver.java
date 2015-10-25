package businesslogic.adminbl;

public class ReceiptOrderBL_driver {
	public void drive(ReceiptOrderBLService receiptOrderBLService) {
		receiptOrderBLService.getOrderInfo(orderNum);
		receiptOrderBLService.receiptOrder(new OrderVO());
	}

	public void main(String[] args) {
		(new ReceiptOrderBL_driver()).drive(new ReceiptOrderBL_stub());
	}
}
