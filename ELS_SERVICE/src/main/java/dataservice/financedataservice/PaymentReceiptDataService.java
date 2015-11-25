package dataservice.financedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PaymentReceiptPO;

public interface PaymentReceiptDataService {
	public int creatPaymentReceipt(PaymentReceiptPO vo) throws RemoteException;
	public ArrayList<PaymentReceiptPO> getAllPaymentReceipt() throws RemoteException;
	
//	//当天存储的持久化对象个数
//	public int getNum() throws RemoteException;
//	//根据ID查找持久化对象
//	public PaymentReceiptPO findByID(String ID) throws RemoteException;
	//修改持久化对象
	public  PaymentReceiptPO modify(PaymentReceiptPO po) throws RemoteException;

	public double getSalary() ;

	public double getFare();

	public double getRent();
}
