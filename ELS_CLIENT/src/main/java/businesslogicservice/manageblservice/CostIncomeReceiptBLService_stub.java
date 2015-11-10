package businesslogicservice.manageblservice;

import java.util.ArrayList;

import vo.CollectionReceiptVO;
import vo.CostIncomeReceiptVO;
import vo.PaymentReceiptVO;

public class CostIncomeReceiptBLService_stub implements CostIncomeReceiptBLService{

	public int creatCostIncomeList(CostIncomeReceiptVO vo) {
		// TODO Auto-generated method stub
		System.out.println("Create CostIncomeList sucessfully!");
		return 0;
	}

	public CostIncomeReceiptVO getCostIncomeList(String s) {
		// TODO Auto-generated method stub
		System.out.println("Get CIL successfully!");
		return null;
	}

	public ArrayList<CostIncomeReceiptVO> getAllCostIncomeList() {
		// TODO Auto-generated method stub
		System.out.println("Get All CIL successfully!");
		return null;
	}

	public ArrayList<CollectionReceiptVO> getCollection() {
		// TODO Auto-generated method stub
		System.out.println("Get Collection successfully!");
		return null;
	}

	public ArrayList<PaymentReceiptVO> getPayment() {
		// TODO Auto-generated method stub
		System.out.println("Get Payment successfully!");
		return null;
	}

	public double getIncome(CollectionReceiptVO vo) {
		// TODO Auto-generated method stub
		System.out.println("Get income successfully!");
		return 0;
	}

	public double getCost(PaymentReceiptVO vo) {
		// TODO Auto-generated method stub
		System.out.println("Get cost successfully!");
		return 0;
	}

	public double getProfit(double income, double cost) {
		// TODO Auto-generated method stub
		System.out.println("Get profit successfully!");
		return 0;
	}

	public String getCostIncomeListID() {
		// TODO Auto-generated method stub
		System.out.println("Get ID successfully!");
		return null;
	}

}
