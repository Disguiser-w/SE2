package businesslogic.financebl;

import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.PaymentReceiptPO;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import businesslogic.receiptbl.ReceiptBL;
import businesslogicservice.financeblservice.CostIncomeReceiptBLService;
import vo.CostIncomeReceiptVO;

public class CostIncomeReceiptBL extends ReceiptBL implements CostIncomeReceiptBLService{
	double cost;
	double income;
	double profit;
	CostIncomeReceiptDataService cirdService;
	CollectionReceiptDataService crdService;
	PaymentReceiptDataService prdService;
	
	public CostIncomeReceiptBL() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * create方法还没有写，宝宝委屈==
	 * 还有voToPO的转化
	 * */
	public int creatCostIncomeList(CostIncomeReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
//	public CostIncomeReceiptVO getCostIncomeList(String s) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public ArrayList<CostIncomeReceiptVO> getAllCostIncomeList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public ArrayList<CollectionReceiptVO> getCollection() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public ArrayList<PaymentReceiptVO> getPayment() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	/**
	 * 计算income
	 * doge说这玩意儿写在bl里.........
	 * */
	public double getIncome() {
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs=crdService.getAllCollection();
		double income=0;
		if(collectionReceiptPOs==null){
			System.out.println("collectionReceiptPOs is null");
			return -1;
		}
		else{
		for(CollectionReceiptPO p:collectionReceiptPOs){
			income+=p.totalMoney();
		}
		return income;
		}
	}
	
	/**
	 * 计算cost
	 * */
	public double getCost() {
		// TODO Auto-generated method stub
		ArrayList<PaymentReceiptPO> paymentReceiptPOs=prdService.getAllPaymentReceipt();
		double cost=0;
		if(paymentReceiptPOs==null){
			System.out.println("paymentReceiptPOs is null------CostIncomeReceiptBL");
			return 0;
		}
		else{
			for(PaymentReceiptPO p:paymentReceiptPOs){
				cost+=p.getMoney();
			}
			return cost;
		}
	}
	
	/**
	 * 计算profit
	 * */
	public double getProfit(double income, double cost) {
		// TODO Auto-generated method stub
		return income-cost;
	}
	
	/**
	 * 自动获取ID
	 * 这个在总的receipt里写应该就可以了
	 * 其实这个好像根本不需要编号的呀
	 * */
	public String getCostIncomeListID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
