package dataservice.financedataservice;

import java.rmi.RemoteException;

import po.CostIncomeReceiptPO;

public class CostIncomeReceiptDataService_driver {
	public void driver(CostIncomeReceiptDataService cds){
		try {
			cds.creatCostIncomeList(new CostIncomeReceiptPO());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			cds.getAllCostIncomeList();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		cds.findByID(null);
//		cds.getCollection();
//		cds.getPayment();
//		cds.getCost(null);
//		cds.getIncome(null);
//		cds.getNum();
//		cds.getProfit(0, 0);
//		cds.modify(null);
	}
	
	public static void main(String[] args){
		CostIncomeReceiptDataService cds=new CostIncomeReceiptDataService_stub();
		CostIncomeReceiptDataService_driver driver=new CostIncomeReceiptDataService_driver();
		driver.driver(cds);
	}

}
