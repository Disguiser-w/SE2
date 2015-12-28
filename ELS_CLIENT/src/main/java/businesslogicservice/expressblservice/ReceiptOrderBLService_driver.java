package businesslogicservice.expressblservice;

public class ReceiptOrderBLService_driver {
	public void drive(ReceiptOrderBLService receiptOrderBLService) {
//		receiptOrderBLService.getOrderInfo(1000);
		receiptOrderBLService.receiptOrder(null);
	}

	public void main(String[] args) {
		(new ReceiptOrderBLService_driver()).drive(new ReceiptOrderBLService_stub());
	}
}
