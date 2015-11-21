package businesslogic.financebl;

import java.util.ArrayList;

import businesslogicservice.financeblservice.CostIncomeReceiptBLService;
import vo.CollectionReceiptVO;
import vo.CostIncomeReceiptVO;
import vo.PaymentReceiptVO;

public class CostIncomeReceiptBL implements CostIncomeReceiptBLService{
	double cost;
	double income;
	double profit;
	
	public CostIncomeReceiptBL() {
		// TODO Auto-generated constructor stub
	}
	
	public int creatCostIncomeList(CostIncomeReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	public CostIncomeReceiptVO getCostIncomeList(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<CostIncomeReceiptVO> getAllCostIncomeList() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<CollectionReceiptVO> getCollection() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<PaymentReceiptVO> getPayment() {
		// TODO Auto-generated method stub
		return null;
	}
	public double getIncome(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		return vo.getIncome();
	}
	public double getCost(PaymentReceiptVO vo) {
		// TODO Auto-generated method stub
		return vo.getMoney();
	}
	public double getProfit(double income, double cost) {
		// TODO Auto-generated method stub
		return income-cost;
	}
	public String getCostIncomeListID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
