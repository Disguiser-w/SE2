package businesslogic.financebl;

import java.util.ArrayList;

import po.FarePO;
import vo.FinanceCostVO;
import vo.MockFinanceCostVO;

public class PaymentReceiptBL {
	
	//根据条目和时间筛选
		String Clause;
		String Time;
		String PaymentID;
		String FareID;
		
		ArrayList<String> AllPaymentID;
		ArrayList<String> AllFareID;
		ArrayList<Double> money;
		//这里的totalmoney是一个日期内的totalmoney
		double totalMoney;
		
		FarePO farePO;
		MockFinanceCostVO costVO;
		
//		public PaymentReceiptBL(String id){
//			this.PaymentID=id;
//			farePO=new ArrayList<FarePO>();
//			money=new ArrayList<Double>();
//			totalMoney=0;
//		}
		
		//这个PO传过来的时候到底应该怎么传
		public void addPaymentItem(String Clause,String Time,double Money,String PaymentID,FarePO farepo){
		//	public void addPaymentItem(MockCostVO costVO){
			switch(Clause){
			case "fare":{
				if(farepo.getTime().equals(Time)){
					money.add(farepo.getMoney());
					totalMoney+=farepo.getMoney();
					AllPaymentID.add(PaymentID);
				}
			}
			case "salary":{
				money.add(Money);
				totalMoney+=Money;
				AllPaymentID.add(PaymentID);
			}
			case "rent":{
				money.add(Money);
				totalMoney+=Money;
				AllPaymentID.add(PaymentID);
			}
			}
			
		}
		
		public void deletePaymentItem(String PaymentID){
			int i=AllPaymentID.indexOf(PaymentID);
			AllPaymentID.remove(i);
			totalMoney-=money.get(i);
			money.remove(i);
		}
		
		//排序之后便于删除
		public String getPaymentIDByOrder(int i){
			return AllPaymentID.get(i);
		}
		
		public double getMoneyByOrder(int i){
			return money.get(i);
		}
		
		public double getMoney(FinanceCostVO vo){
			totalMoney=vo.getCost();
			return totalMoney;
		}
		

}
