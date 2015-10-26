package dataservice.financedataservice;

import po.CostIncomeReceiptPO;

public class CostIncomeReceiptDataService_driver {
	public void driver(CostIncomeReceiptDataService cds){
		cds.creatCostIncomeList(new CostIncomeReceiptPO());
		cds.getAllCostIncomeList();
		cds.findByID(null);
		cds.getCollection();
		cds.getPayment();
//		cds.getCost(null);
//		cds.getIncome(null);
		cds.getNum();
//		cds.getProfit(0, 0);
		cds.modify(null);
	}
	
	public static void main(String[] args){
		CostIncomeReceiptDataService cds=new CostIncomeReceiptDataService_stub();
		CostIncomeReceiptDataService_driver driver=new CostIncomeReceiptDataService_driver();
		driver.driver(cds);
	}

}
