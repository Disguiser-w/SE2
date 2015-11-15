package dataservice.financedataservice;

import po.PaymentReceiptPO;

public class PaymentReceiptDataService_driver {
	public void drive(PaymentReceiptDataService pds){
		pds.creatPaymentReceipt(null);
		pds.getAllPaymentReceipt();
		pds.findByID(null);
		pds.getNum();
		pds.modify(new PaymentReceiptPO());
	}
	
	public static void main(String[] args){
		PaymentReceiptDataService pds=new PaymentReceiptDataService_stub();
		PaymentReceiptDataService_driver driver=new PaymentReceiptDataService_driver();
		driver.drive(pds);
	}

}
