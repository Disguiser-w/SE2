package businesslogic.financebl.controller;

import java.util.ArrayList;

import vo.PaymentReceiptVO;
import businesslogic.financebl.PaymentReceiptBL;
import businesslogicservice.financeblservice.PaymentReceiptBLService;

public class PaymentReceiptBLController implements PaymentReceiptBLService{
	
	private PaymentReceiptBL paymentReceiptBL;
	
	public PaymentReceiptBLController() {
		paymentReceiptBL=new PaymentReceiptBL();
	}

	public int creatPaymentReceipt(PaymentReceiptVO vo) {
		// TODO Auto-generated method stub
		return paymentReceiptBL.creatPaymentReceipt(vo);
	}

	public PaymentReceiptVO getPaymentReceipt(String s) {
		// TODO Auto-generated method stub
		return paymentReceiptBL.getPaymentReceipt(s);
	}

	public ArrayList<PaymentReceiptVO> getAllPaymentReceipt() {
		// TODO Auto-generated method stub
		return paymentReceiptBL.getAllPaymentReceipt();
	}

	public String getPaymentReceiptListID() {
		// TODO Auto-generated method stub
		return paymentReceiptBL.getPaymentReceiptListID();
	}

	public double getSalary(String time) {
		// TODO Auto-generated method stub
		return paymentReceiptBL.getSalary(time);
	}

	public double getFare(String time) {
		// TODO Auto-generated method stub
		return paymentReceiptBL.getFare(time);
	}

	public double getRent(String time) {
		// TODO Auto-generated method stub
		return paymentReceiptBL.getRent(time);
	}

	public ArrayList<PaymentReceiptVO> getUnapprovedPaymentReceipt() {
		// TODO Auto-generated method stub
		return paymentReceiptBL.getUnapprovedPaymentReceipt();
	}

}
