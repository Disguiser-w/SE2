package businesslogic.financebl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businesslogic.financebl.CostIncomeReceiptBL;
import businesslogicservice.financeblservice.CostIncomeReceiptBLService;
import vo.CostIncomeReceiptVO;

public class CostIncomeReceiptBLController implements CostIncomeReceiptBLService{
	
	private CostIncomeReceiptBL costIncomeReceiptBL;
	
	public CostIncomeReceiptBLController() {
		costIncomeReceiptBL=new CostIncomeReceiptBL();
	}
	

	public int creatCostIncomeList(CostIncomeReceiptVO vo) {
		// TODO Auto-generated method stub
		return costIncomeReceiptBL.creatCostIncomeList(vo);
	}

	public double getIncome() {
		// TODO Auto-generated method stub
		return costIncomeReceiptBL.getIncome();
	}

	public double getCost() {
		// TODO Auto-generated method stub
		return costIncomeReceiptBL.getCost();
	}

	public double getProfit(double income, double cost) {
		// TODO Auto-generated method stub
		return costIncomeReceiptBL.getProfit(income, cost);
	}

	public String getCostIncomeListID() {
		// TODO Auto-generated method stub
		return costIncomeReceiptBL.getCostIncomeListID();
	}

	public CostIncomeReceiptVO getCostIncomeReceipt(String time) {
		// TODO Auto-generated method stub
		return costIncomeReceiptBL.getCostIncomeReceipt(time);
	}

	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException{
		CostIncomeReceiptBLController controller=new CostIncomeReceiptBLController();
		
		System.out.println("ID"+controller.getCostIncomeListID());
		System.out.println("Income: "+controller.getIncome());
		System.out.println("Cost: "+controller.getCost());
		System.out.println("Profit: "+controller.getProfit(controller.getIncome(), controller.getCost()));
		
	}
}
