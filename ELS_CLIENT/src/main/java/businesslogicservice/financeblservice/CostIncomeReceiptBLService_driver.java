package businesslogicservice.financeblservice;

import vo.CollectionReceiptVO;
import vo.CostIncomeReceiptVO;
import vo.PaymentReceiptVO;

public class CostIncomeReceiptBLService_driver {
	public void drive(CostIncomeReceiptBLService cbs){
		String s="CBSYB-20151024-00001";
		cbs.creatCostIncomeList(new CostIncomeReceiptVO(s, s, null, null, null, null, 0, 0, 0));
		cbs.getCostIncomeList(s);
		cbs.getAllCostIncomeList();
		cbs.getCollection();
		cbs.getPayment();
		cbs.getIncome(new CollectionReceiptVO(s, s, null, null, null, 0, s, s));
		cbs.getCost(new PaymentReceiptVO(s, s, null, null, s, 0, s, s, s));
		cbs.getProfit(0, 0);
		cbs.getCostIncomeListID();
	}
	
	public void main(){
		CostIncomeReceiptBLService cbs=new CostIncomeReceiptBLService_stub();
		CostIncomeReceiptBLService_driver driver=new CostIncomeReceiptBLService_driver();
		driver.drive(cbs);
	}

}
