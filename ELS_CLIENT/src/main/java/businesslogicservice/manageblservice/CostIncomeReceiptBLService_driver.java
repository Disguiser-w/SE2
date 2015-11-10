package businesslogicservice.manageblservice;

import vo.CollectionReceiptVO;
import vo.CostIncomeReceiptVO;
import vo.PaymentReceiptVO;

public class CostIncomeReceiptBLService_driver {
	public void drive(CostIncomeReceiptBLService cbs){
		String s="CBSYB-20151024-00001";
		cbs.getCostIncomeList(s);
		cbs.getAllCostIncomeList();
		cbs.getCollection();
		cbs.getPayment();
		cbs.getIncome(new CollectionReceiptVO());
		cbs.getCost(new PaymentReceiptVO());
		cbs.getProfit(0, 0);
		cbs.getCostIncomeListID();
	}
	
	public void main(){
		CostIncomeReceiptBLService cbs=new CostIncomeReceiptBLService_stub();
		CostIncomeReceiptBLService_driver driver=new CostIncomeReceiptBLService_driver();
		driver.drive(cbs);
	}

}
