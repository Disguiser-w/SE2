package businesslogic.managebl;

import java.util.ArrayList;

import vo.CollectionReceiptVO;
import vo.CostIncomeReceiptVO;
import vo.PaymentReceiptVO;
import businesslogicservice.manageblservice.CostIncomeReceiptBLService;
import dataservice.financedataservice.CostIncomeReceiptDataService;

public class CostIncomeReceiptBL implements CostIncomeReceiptBLService{

	public CostIncomeReceiptDataService cirdService;

	public CostIncomeReceiptVO getCostIncomeList(String s){
		return cirdService.findByID(s);
	}
	
	public ArrayList<CostIncomeReceiptVO> getAllCostIncomeList(){
		return cirdService.getAllCostIncomeList();
		
	}
	/*public ArrayList<CollectionReceiptVO> getCollection(){}
	public ArrayList<PaymentReceiptVO>getPayment(){}
	public double getIncome(CollectionReceiptVO vo){}
	public double getCost(PaymentReceiptVO vo){}
	public double getProfit(double income,double cost){}
	public String getCostIncomeListID(){}*/
	
	public CostIncomeReceiptVO CIRPOToVO(CostIncomeReceiptPO cirpo){
		return new CostInomeReceipt();
	}
}
