package dataservice.financedataservice;

import java.rmi.RemoteException;
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

	public double getSalary(String time) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getFare(String time) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getRent(String time) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<PaymentReceiptPO> getPayment_right(String beginTime,
			String endTime) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PaymentReceiptPO> getUnapprovedPaymentReceipt() {
		// TODO Auto-generated method stub
		return null;
	}

	public int saveSubmittedPaymentReceiptInfo(PaymentReceiptPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
