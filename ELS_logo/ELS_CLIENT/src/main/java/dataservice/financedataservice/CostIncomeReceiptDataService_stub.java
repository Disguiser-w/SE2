package dataservice.financedataservice;

import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.CostIncomeReceiptPO;
import po.PaymentReceiptPO;

public class CostIncomeReceiptDataService_stub implements CostIncomeReceiptDataService{

	public int creatCostIncomeList(CostIncomeReceiptPO po) {
		// TODO Auto-generated method stub
		System.out.println("Creat CostIncomeList successfully!");
		return 0;
	}

	public ArrayList<CostIncomeReceiptPO> getAllCostIncomeList() {
		// TODO Auto-generated method stub
		System.out.println("get CostIncomeList successfully!");
		return null;
	}

	public ArrayList<CollectionReceiptPO> getCollection() {
		// TODO Auto-generated method stub
		System.out.println("get Collection successfully!");
		return null;
	}

	public ArrayList<PaymentReceiptPO> getPayment() {
		// TODO Auto-generated method stub
		System.out.println("get Payment successfully!");
		return null;
	}

//	public double getIncome(CollectionReceiptPO po) {
//		// TODO Auto-generated method stub
//		System.out.println("get income successfully!");
//		return 0;
//	}
//
//	public double getCost(PaymentReceiptPO po) {
//		// TODO Auto-generated method stub
//		System.out.println("get cost successfully!");
//		return 0;
//	}
//
//	public double getProfit(double income, double cost) {
//		// TODO Auto-generated method stub
//		System.out.println("get profit successfully!");
//		return 0;
//	}

	public int getNum() {
		// TODO Auto-generated method stub
		System.out.println("get num successfully!");
		return 0;
	}

	public CostIncomeReceiptPO findByID(String ID) {
		// TODO Auto-generated method stub
		System.out.println("get CostIncomeReceiptPO successfully!");
		return null;
	}

	public CostIncomeReceiptPO modify(CostIncomeReceiptPO po) {
		// TODO Auto-generated method stub
		System.out.println("modify successfully!");
		return null;
	}
	

}
