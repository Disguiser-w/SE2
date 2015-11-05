package dataservice.financedataservice;

import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.CostIncomeReceiptPO;
import po.PaymentReceiptPO;

public interface CostIncomeReceiptDataService {
		public int creatCostIncomeList(CostIncomeReceiptPO po);
		public ArrayList<CostIncomeReceiptPO> getAllCostIncomeList();
		public ArrayList<CollectionReceiptPO> getCollection();
		public ArrayList<PaymentReceiptPO>getPayment();
//		public double getIncome(CollectionReceiptPO po);
//		public double getCost(PaymentReceiptPO po);
//		public double getProfit(double income,double cost);
	
		//当天存储的持久化对象个数
		public int getNum();
		//根据ID查找持久化对象
		public CostIncomeReceiptPO findByID(String ID);
		//修改持久化对象
		public  CostIncomeReceiptPO modify(CostIncomeReceiptPO po);

}
