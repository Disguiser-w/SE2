package businesslogicservice.financeblservice;

import vo.CostIncomeReceiptVO;

public class CostIncomeReceiptBLService_driver {
	public void drive(CostIncomeReceiptBLService cbs){
		String s="CBSYB-20151024-00001";
		cbs.creatCostIncomeList(new CostIncomeReceiptVO(s, s, null, null, null, null, 0, 0, 0));
//		cbs.getCostIncomeList(s);
//		cbs.getAllCostIncomeList();
//		cbs.getCollection();
//		cbs.getPayment();
		cbs.getIncome();
		cbs.getCost();
		cbs.getProfit(0, 0);
		cbs.getCostIncomeListID();
	}
	
	public void main(){
		CostIncomeReceiptBLService cbs=new CostIncomeReceiptBLService_stub();
		CostIncomeReceiptBLService_driver driver=new CostIncomeReceiptBLService_driver();
		driver.drive(cbs);
	}

}
