package businesslogic.managebl;


import vo.CostIncomeReceiptVO;
import businesslogicservice.manageblservice.CheckCostIncomeReceiptBLService;
import businesslogic.financebl.CostIncomeReceiptBL;

public class CheckCostIncomeReceiptBL implements CheckCostIncomeReceiptBLService{

	public CostIncomeReceiptBL cirBL;

	public CostIncomeReceiptVO getCostIncomeList(String s){
		return cirBL.getCostIncomeReceipt(s);
	}
	
	
}
