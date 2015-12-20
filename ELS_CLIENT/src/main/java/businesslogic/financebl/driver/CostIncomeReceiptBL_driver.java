package businesslogic.financebl.driver;

import type.ReceiptState;
import type.ReceiptType;
import vo.CostIncomeReceiptVO;
import businesslogic.financebl.controller.CostIncomeReceiptBLController;

public class CostIncomeReceiptBL_driver {
	public static void main(String[] args){
		CostIncomeReceiptBLController costIncomeReceiptBLController = new CostIncomeReceiptBLController();
		CostIncomeReceiptVO civo = new CostIncomeReceiptVO("CBSYB-20151220", "CW-00001", ReceiptType.COSTINCOMERECEPTION,
				ReceiptState.DISAPPROVE	, 3000, 5000, 2000);
		costIncomeReceiptBLController.creatCostIncomeList(civo);
		String costIncomeID=costIncomeReceiptBLController.getCostIncomeListID();
		System.out.println(costIncomeID);
		
		double cost =costIncomeReceiptBLController.getCost();
		double income=costIncomeReceiptBLController.getIncome();
		double profit = costIncomeReceiptBLController.getProfit(income, cost);
		System.out.println(cost+" "+income+" "+profit);
//		costIncomeReceiptBLController.getCostIncomeReceipt("20151220");
	}

}
