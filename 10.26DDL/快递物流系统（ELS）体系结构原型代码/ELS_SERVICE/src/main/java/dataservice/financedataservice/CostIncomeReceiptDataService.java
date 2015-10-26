package dataservice.financedataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.CostIncomeReceiptPO;
import po.PaymentReceiptPO;

public interface CostIncomeReceiptDataService {
	public int creatCostIncomeList(CostIncomeReceiptPO po) throws RemoteException;
	public ArrayList<CostIncomeReceiptPO> getAllCostIncomeList() throws RemoteException;
	public ArrayList<CollectionReceiptPO> getCollection() throws RemoteException;
	public ArrayList<PaymentReceiptPO>getPayment() throws RemoteException;
//	public double getIncome(CollectionReceiptPO po);
//	public double getCost(PaymentReceiptPO po);
//	public double getProfit(double income,double cost);

	//当天存储的持久化对象个数
	public int getNum() throws RemoteException;
	//根据ID查找持久化对象
	public CostIncomeReceiptPO findByID(String ID) throws RemoteException;
	//修改持久化对象
	public  CostIncomeReceiptPO modify(CostIncomeReceiptPO po) throws RemoteException;
}
