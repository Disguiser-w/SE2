package businesslogic.managebl.controller;

import vo.CostIncomeReceiptVO;
import businesslogic.managebl.CheckCostIncomeReceiptBL;

public class CheckCostIncomeReceiptController {

	private CheckCostIncomeReceiptBL ccir;
	
	public CostIncomeReceiptVO getCostIncomeList(String s){
		return ccir.getCostIncomeList(s);
	}
	
}
