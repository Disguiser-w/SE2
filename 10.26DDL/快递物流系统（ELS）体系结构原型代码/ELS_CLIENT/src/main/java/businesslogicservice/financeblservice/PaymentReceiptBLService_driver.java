package businesslogicservice.financeblservice;

import vo.PaymentReceiptVO;

public class PaymentReceiptBLService_driver {
	public void drive(PaymentReceiptBLService pbs){
		pbs.creatPaymentReceipt(new PaymentReceiptVO());
		String s="FKD-20151010-00001";
		pbs.getPaymentReceipt(s);
		pbs.getAllPaymentReceipt();
		pbs.getPaymentReceiptListID();
		
	}
	
	public static void main(String[] args){
		PaymentReceiptBLService pbs=new PaymentReceiptBLService_stub();
		PaymentReceiptBLService_driver driver=new PaymentReceiptBLService_driver();
		driver.drive(pbs);
		
	}
}


