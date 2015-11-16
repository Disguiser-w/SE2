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
	
	@Override
	public int creatCostIncomeList(CostIncomeReceiptVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public CostIncomeReceiptVO getCostIncomeList(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<CostIncomeReceiptVO> getAllCostIncomeList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<CollectionReceiptVO> getCollection() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<PaymentReceiptVO> getPayment() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double getIncome(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		return vo.getIncome();
	}
	@Override
	public double getCost(PaymentReceiptVO vo) {
		// TODO Auto-generated method stub
		return vo.getMoney();
	}
	@Override
	public double getProfit(double income, double cost) {
		// TODO Auto-generated method stub
		return income-cost;
	}
	@Override
	public String getCostIncomeListID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
