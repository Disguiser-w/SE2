package dataservice.financedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CostIncomeReceiptPO;

public interface CostIncomeReceiptDataService extends Remote{
	public int creatCostIncomeList(CostIncomeReceiptPO po) throws RemoteException;
	public ArrayList<CostIncomeReceiptPO> getAllCostIncomeList() throws RemoteException;
//	public ArrayList<CollectionReceiptPO> getCollection() throws RemoteException;
//	public ArrayList<PaymentReceiptPO>getPayment() throws RemoteException;
//	public double getIncome(CollectionReceiptPO po);
//	public double getCost(PaymentReceiptPO po);
//	public double getProfit(double income,double cost);

	//当天存储的持久化对象个数
	public int getNum() throws RemoteException;
	//根据ID查找持久化对象
	public CostIncomeReceiptPO findByID(String ID) throws RemoteException;
	//修改持久化对象
	public  CostIncomeReceiptPO modify(CostIncomeReceiptPO po) throws RemoteException;
	//按ID或者是日期获取(总经理要按时间查询)
	public CostIncomeReceiptPO getCostIncomeReceipt(String time) throws RemoteException;
}


//package dataservice.financedataservice;
//
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//
//import po.CollectionReceiptPO;
//import po.CostIncomeReceiptPO;
//import po.PaymentReceiptPO;
//
//public interface CostIncomeReceiptDataService {
//	//创建成本收益表
//		public int creatCostIncomeList(CostIncomeReceiptPO po);
//		//获取所有成本收益表
//		public ArrayList<CostIncomeReceiptPO> getAllCostIncomeList();
//		//获取所有入款单
//		public ArrayList<CollectionReceiptPO> getCollection();
//		//获取所有付款单
//		public ArrayList<PaymentReceiptPO>getPayment();
//		//获取收入
//		public double getIncome(ArrayList<CollectionReceiptPO> po);
//		//获取成本
//		public double getCost(ArrayList<PaymentReceiptPO> po);
//		//获取利润
//		public double getProfit(double income,double cost);
//	
////		//当天存储的持久化对象个数
////		public int getNum();
////		//根据ID查找持久化对象
////		public CostIncomeReceiptPO findByID(String ID);
////		//修改持久化对象
////		public  CostIncomeReceiptPO modify(CostIncomeReceiptPO po);
//		//按ID或者是日期获取(总经理要按时间查询)
//		public CostIncomeReceiptPO getCostIncomeReceipt(String time) throws RemoteException;

//}
