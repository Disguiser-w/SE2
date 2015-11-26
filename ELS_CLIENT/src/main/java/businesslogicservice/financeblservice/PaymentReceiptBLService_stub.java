package businesslogicservice.financeblservice;

import java.util.ArrayList;

import po.FarePO;
import po.UserPO;
import vo.PaymentReceiptVO;

public class PaymentReceiptBLService_stub implements PaymentReceiptBLService {

	public int creatPaymentReceipt(PaymentReceiptVO vo) {
		// TODO Auto-generated method stub
		System.out.println("Create Payment successfully!");
		return 0;
	}

	public PaymentReceiptVO getPaymentReceipt(String s) {
		// TODO Auto-generated method stub
		System.out.println("Show Payment successfully!");
		return null;
	}

	public ArrayList<PaymentReceiptVO> getAllPaymentReceipt() {
		// TODO Auto-generated method stub
		System.out.println("Get all payment successfully!");
		return null;
	}

	public String getPaymentReceiptListID() {
		// TODO Auto-generated method stub
		System.out.println("Get ID successfully!");
		return null;
	}

	@Override
	public double getSalary() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFare() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRent() {
		// TODO Auto-generated method stub
		return 0;
	}

	


}
