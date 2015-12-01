package businesslogic.financebl.controller;

import vo.CostIncomeReceiptVO;
import businesslogic.financebl.CostIncomeReceiptBL;
import businesslogicservice.financeblservice.CostIncomeReceiptBLService;

public class CostIncomeReceiptBLController implements CostIncomeReceiptBLService{
	
	private CostIncomeReceiptBL costIncomeReceiptBL;

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

}
