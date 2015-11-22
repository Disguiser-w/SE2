package businesslogic.financebl;

import java.util.ArrayList;

import dataservice.financedataservice.PaymentReceiptDataService;
import po.PaymentReceiptPO;
import vo.PaymentItemVO;
import vo.PaymentReceiptVO;
import businesslogic.receiptbl.ReceiptBL;
import businesslogicservice.financeblservice.PaymentReceiptBLService;


public class PaymentReceiptBL extends ReceiptBL implements PaymentReceiptBLService{
	
	PaymentReceiptDataService prdService;

	/**
	 * 创建表单是从vo到po的过程
	 * */
	@Override
	public int creatPaymentReceipt(PaymentReceiptVO vo) {
		// TODO Auto-generated method stub
		PaymentReceiptPO po=pvoToPO(vo);
		update(vo);
		return prdService.creatPaymentReceipt(po);
	}
	
	public PaymentReceiptPO pvoToPO(PaymentReceiptVO vo){
		PaymentReceiptPO ppo;
		if(vo==null){
			System.out.println("PaymentReceiptVO vo is null-----------PaymentReceiptBL");
			return null;
		}
		else{
			ppo=new PaymentReceiptPO(vo.getID(), vo.getUserID(), vo.getType(), vo.getState(), vo.getClause(), vo.getMoney(), vo.getDate(), vo.getAccount(), vo.getName());
			return ppo;
		}
	}
	/**
	 * 试运行
	 * paymentItems怎么用还没想好-------这个怎么写啊23333
	 * 初步想法：所有判断付款类型，付款金额的操作都写在paymentItem里
	 * */
	public int excute(PaymentReceiptVO vo){
		AccountBL account=new AccountBL();
//		ArrayList<PaymentReceiptVO> pvos=vo.ge
		ArrayList<PaymentItemVO> paymentItems=vo.getPaymentItems();
		return 0;
	}
	@Override
	public PaymentReceiptVO getPaymentReceipt(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取所有的付款单
	 * */
	@Override
	public ArrayList<PaymentReceiptVO> getAllPaymentReceipt() {
		// TODO Auto-generated method stub
		return pposToVOs(prdService.getAllPaymentReceipt());
	}
	
	public PaymentReceiptVO ppoToVO(PaymentReceiptPO po){
		PaymentReceiptVO vo=new PaymentReceiptVO(po.getID(), po.getUserID(), po.getType(), po.getState(), po.getClause(), po.getMoney(), po.getDate(), po.getAccount(), po.getName());
		return vo;
	}
	public ArrayList<PaymentReceiptVO> pposToVOs(ArrayList<PaymentReceiptPO> pos){
		ArrayList<PaymentReceiptVO> paymentReceiptVOs=new ArrayList<PaymentReceiptVO>();
		if(pos==null){
			System.out.println("ArrayList<PaymentReceiptPO> pos is null------PaymentReceiptBL");
			return null;
		}
		else{
			for(PaymentReceiptPO p:pos){
				PaymentReceiptVO vo=new PaymentReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(), p.getClause(), p.getMoney(), p.getDate(), p.getAccount(),p.getName());
				paymentReceiptVOs.add(vo);
			}
			return paymentReceiptVOs;
		}
	}
	

	/**
	 * 自动获取ID
	 * */
	@Override
	public String getPaymentReceiptListID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
//	//根据条目和时间筛选
//		String Clause;
//		String Time;
//		String PaymentID;
//		String FareID;
//		
//		ArrayList<String> AllPaymentID;
//		ArrayList<String> AllFareID;
//		ArrayList<Double> money;
//		//这里的totalmoney是一个日期内的totalmoney
//		double totalMoney;
//		
//		FarePO farePO;
//		MockFinanceCostVO costVO;
//		
////		public PaymentReceiptBL(String id){
////			this.PaymentID=id;
////			farePO=new ArrayList<FarePO>();
////			money=new ArrayList<Double>();
////			totalMoney=0;
////		}
//		
//		//这个PO传过来的时候到底应该怎么传
//		public void addPaymentItem(String Clause,String Time,double Money,String PaymentID,FarePO farepo){
//		//	public void addPaymentItem(MockCostVO costVO){
//			switch(Clause){
//			case "fare":{
//				if(farepo.getTime().equals(Time)){
//					money.add(farepo.getMoney());
//					totalMoney+=farepo.getMoney();
//					AllPaymentID.add(PaymentID);
//				}
//			}
//			case "salary":{
//				money.add(Money);
//				totalMoney+=Money;
//				AllPaymentID.add(PaymentID);
//			}
//			case "rent":{
//				money.add(Money);
//				totalMoney+=Money;
//				AllPaymentID.add(PaymentID);
//			}
//			}
//			
//		}
//		
//		public void deletePaymentItem(String PaymentID){
//			int i=AllPaymentID.indexOf(PaymentID);
//			AllPaymentID.remove(i);
//			totalMoney-=money.get(i);
//			money.remove(i);
//		}
//		
//		//排序之后便于删除
//		public String getPaymentIDByOrder(int i){
//			return AllPaymentID.get(i);
//		}
//		
//		public double getMoneyByOrder(int i){
//			return money.get(i);
//		}
//		
//		public double getMoney(FinanceCostVO vo){
//			totalMoney=vo.getCost();
//			return totalMoney;
//		}
//		

}
