package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.CostIncomeReceiptPO;
import po.PaymentReceiptPO;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import businesslogic.datafactory.DataFactory;
import businesslogic.financebl.controller.FinanceMainController;
import businesslogic.receiptbl.ReceiptBL;
import businesslogic.receiptbl.getDate;
import vo.CostIncomeReceiptVO;

public class CostIncomeReceiptBL extends ReceiptBL{
	double cost;
	double income;
	double profit;
	CostIncomeReceiptDataService costIncomeData;
	CollectionReceiptDataService collectionData;
	PaymentReceiptDataService paymentData;
	
	public CostIncomeReceiptBL()  {
		// TODO Auto-generated constructor stub
		super();
		try {
			collectionData=DataFactory.getCollectionReceiptData();
			paymentData=DataFactory.getPaymentReceiptData();
			costIncomeData=DataFactory.getCostIncomeData();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * create方法还没有写，宝宝委屈==
	 * 还有voToPO的转化
	 * */
	public int creatCostIncomeList(CostIncomeReceiptVO vo) {
		// TODO Auto-generated method stub
		CostIncomeReceiptPO po=FinanceMainController.voToPO(vo);
		try {
			return costIncomeData.creatCostIncomeList(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("创建成本收益表失败");
			return 0;
		}
	}
	


	/**
	 * 计算income
	 * doge说这玩意儿写在bl里.........
	 * */
	public double getIncome() {
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs;
		try {
			collectionReceiptPOs = collectionData.getAllCollection();
			double income=0;
			if(collectionReceiptPOs==null){
				System.out.println("collectionReceiptPOs is null");
				return 0;
			}
			else{
			for(CollectionReceiptPO p:collectionReceiptPOs){
				income+=p.getIncome();
			}
			return income;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取所有的入款单失败");
			return 0;
		}
		
	}
	
	/**
	 * 计算cost
	 * */
	public double getCost() {
		// TODO Auto-generated method stub
		ArrayList<PaymentReceiptPO> paymentReceiptPOs;
		try {
			paymentReceiptPOs = paymentData.getAllPaymentReceipt();
			double cost=0;
			if(paymentReceiptPOs==null){
				System.out.println("paymentReceiptPOs is null------CostIncomeReceiptBL");
				return 0;
			}
			else{
				for(PaymentReceiptPO p:paymentReceiptPOs){
					cost+=p.getCost();
				}
				return cost;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取所有的付款单失败");
			return 0;
		}
	
	}
	
	/**
	 * 计算profit
	 * */
	public double getProfit(double income, double cost) {
		// TODO Auto-generated method stub
		return income-cost;
	}
	
	/**
	 * 自动获取ID
	 * 成本收益表是从期初到当天日期
	 * 所以其实每次都会覆盖前面的
	 * CBSYB-20151129
	 * */
	public String getCostIncomeListID() {
		// TODO Auto-generated method stub
		return "CBSYB-"+getDate.getdate();
	}
	
	/**
	 * 总经理查询经营情况表
	 * */
	public CostIncomeReceiptVO getCostIncomeReceipt(String time){
		try {
			return FinanceMainController.cipoToVO(costIncomeData.getCostIncomeReceipt(time));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取成本收益表失败");
			return null;
		}
	}
	
	public static void main(String[] args){
		CostIncomeReceiptBL bl;
		bl = new CostIncomeReceiptBL();
		System.out.println("ID"+bl.getCostIncomeListID());

		
	}
	

}
