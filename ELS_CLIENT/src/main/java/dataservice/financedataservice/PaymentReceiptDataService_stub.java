package dataservice.financedataservice;

import java.util.ArrayList;

import po.PaymentReceiptPO;

public class PaymentReceiptDataService_stub implements PaymentReceiptDataService{

	public int creatPaymentReceipt(PaymentReceiptPO vo) {
		// TODO Auto-generated method stub
		System.out.println("Create Payment sucessfully!");
		return 0;
	}

	public ArrayList<PaymentReceiptPO> getAllPaymentReceipt() {
		// TODO Auto-generated method stub
		System.out.println("get sucessfully!");
		return null;
	}

	public int getNum() {
		// TODO Auto-generated method stub
		System.out.println("get num  sucessfully!");
		return 0;
	}

	public PaymentReceiptPO findByID(String ID) {
		// TODO Auto-generated method stub
		System.out.println("find sucessfully!");
		return null;
	}

	public PaymentReceiptPO modify(PaymentReceiptPO po) {
		// TODO Auto-generated method stub
		System.out.println("nodify sucessfully!");
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
