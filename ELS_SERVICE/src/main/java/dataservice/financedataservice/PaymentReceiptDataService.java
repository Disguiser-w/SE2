package dataservice.financedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PaymentReceiptPO;

public interface PaymentReceiptDataService {
	public int creatPaymentReceipt(PaymentReceiptPO po) throws RemoteException;
	public ArrayList<PaymentReceiptPO> getAllPaymentReceipt() throws RemoteException;
	
//	//当天存储的持久化对象个数
//	public int getNum() throws RemoteException;
//	//根据ID查找持久化对象
//	public PaymentReceiptPO findByID(String ID) throws RemoteException;
	//修改持久化对象
	public  PaymentReceiptPO modify(PaymentReceiptPO po) throws RemoteException;
	
	//筛选特定时间内的付款单
	public ArrayList<PaymentReceiptPO> getRightPaymentReceipt(String beginTime,String endTime);

	//获取经营情况表需要
			public ArrayList<PaymentReceiptPO> getPayment_right(String beginTime,String endTime);

//	public double getSalary() ;
//
//	public double getFare();
//
//	public double getRent();
}
