package data.financedata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import File.JXCFile;
import po.PaymentReceiptPO;
import dataservice.financedataservice.PaymentReceiptDataService;

public class PaymentReceiptData implements PaymentReceiptDataService{
	JXCFile file;
	int num;
	public PaymentReceiptData(){
		super();
		file=new JXCFile("payment.ser");
	}

	public int creatPaymentReceipt(PaymentReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("payment.ser");
		file.write(po);
		num++;
		return 0;
	}

	public ArrayList<PaymentReceiptPO> getAllPaymentReceipt()
			throws RemoteException {
		// TODO Auto-generated method stub
		file=new JXCFile("payment.ser");
		ArrayList<PaymentReceiptPO> paymentReceiptPOs=new ArrayList<PaymentReceiptPO>();
		ArrayList<Object> os=file.read();
		if(os==null){
			System.out.println("读取文件payment.ser失败");
			return null;
		}
		for(Object o:os){
			PaymentReceiptPO paymentReceiptPO=(PaymentReceiptPO) o;
			paymentReceiptPOs.add(paymentReceiptPO);
		}
		return paymentReceiptPOs;
	}
	
	/**
	 * 获取特定时间内的付款单——BSL要用
	 * */
	public ArrayList<PaymentReceiptPO> getRightPaymentReceipt(String beginTime,
			String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public PaymentReceiptPO modify(PaymentReceiptPO po) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public double getSalary() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getFare() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getRent() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<PaymentReceiptPO> getPayment_right(String beginTime,
			String endTime) {
		// TODO Auto-generated method stub
		file=new JXCFile("payment.ser");
		ArrayList<Object> os=file.read();
		ArrayList<PaymentReceiptPO> pos=new ArrayList<PaymentReceiptPO>();
		if(beginTime.compareTo(endTime)>0){
			System.out.println("输入时间区间格式不对");
			return null;
		}
		else{
			for(Object o:os){
				PaymentReceiptPO p=(PaymentReceiptPO) o;
				if((p.getDate().compareTo(beginTime)>0)&&(p.getDate().compareTo(endTime)<0)){
					pos.add(p);
				}
			}
			return pos;
		}
	}



}
