package businesslogic.managebl;

import java.util.ArrayList;

import vo.CollectionReceiptVO;
import vo.CostIncomeReceiptVO;
import vo.PaymentReceiptVO;
import businesslogicservice.manageblservice.CheckCostIncomeReceiptBLService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import businesslogic.financebl.CostIncomeReceiptBL;

public class CheckCostIncomeReceiptBL implements CheckCostIncomeReceiptBLService{

	public CostIncomeReceiptBL cirBL;

	public CostIncomeReceiptVO getCostIncomeList(String s){
		return cirBL.getCostIncomeList(s);
	}
	
}
