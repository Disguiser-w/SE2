
package dataservice.financedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PaymentReceiptPO;

public interface PaymentReceiptDataService extends Remote{
	public int creatPaymentReceipt(PaymentReceiptPO po) throws RemoteException;
	public ArrayList<PaymentReceiptPO> getAllPaymentReceipt() throws RemoteException;
	
//	//当天存储的持久化对象个数
//	public int getNum() throws RemoteException;
//	//根据ID查找持久化对象
//	public PaymentReceiptPO findByID(String ID) throws RemoteException;
	//修改持久化对象
	public  PaymentReceiptPO modify(PaymentReceiptPO po) throws RemoteException;
	
	//获取经营情况表需要
			public ArrayList<PaymentReceiptPO> getPayment_right(String beginTime,String endTime) throws RemoteException;

//	public double getSalary() ;
//
//	public double getFare();
//
//	public double getRent();
}
/*package dataservice.financedataservice;

import java.util.ArrayList;

import po.PaymentReceiptPO;

public interface PaymentReceiptDataService {
	   //创建付款单
		public int creatPaymentReceipt(PaymentReceiptPO vo);
		//获取所有的付款单
		public ArrayList<PaymentReceiptPO> getAllPaymentReceipt();
		//当天存储的持久化对象个数
		public int getNum();
		//根据ID查找持久化对象
		public PaymentReceiptPO findByID(String ID);
		//修改持久化对象
		public  PaymentReceiptPO modify(PaymentReceiptPO po);
		
		//获取薪水：关于业绩清0的事的处理
		public double getSalary(String time);
		//获取运费
		public double getFare(String time);
		//获取租金
		public double getRent(String time);
		

}
*/
