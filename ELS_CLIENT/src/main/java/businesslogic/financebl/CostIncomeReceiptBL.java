package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CollectionReceiptPO;
import po.CostIncomeReceiptPO;
import po.PaymentReceiptPO;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import businesslogic.receiptbl.ReceiptBL;
import businesslogic.receiptbl.getDate;
import businesslogicservice.financeblservice.CostIncomeReceiptBLService;
import type.ReceiptType;
import vo.CostIncomeReceiptVO;

public class CostIncomeReceiptBL extends ReceiptBL implements CostIncomeReceiptBLService{
	double cost;
	double income;
	double profit;
	CostIncomeReceiptDataService cirdService;
	CollectionReceiptDataService crdService;
	PaymentReceiptDataService prdService;
	
	public CostIncomeReceiptBL() throws MalformedURLException, RemoteException, NotBoundException {
		// TODO Auto-generated constructor stub
		super();
		cirdService=(CostIncomeReceiptDataService) Naming.lookup("rmi://172.26.209.182:8888/CostIncomeReceiptDataService");
		crdService=(CollectionReceiptDataService) Naming.lookup("rmi://172.26.209.182:8888/CollectionReceiptDataService");
		prdService=(PaymentReceiptDataService) Naming.lookup("rmi://172.26.209.182:8888/PaymentReceiptDataService");
	}
	
	/**
	 * 
	 * create方法还没有写，宝宝委屈==
	 * 还有voToPO的转化
	 * */
	public int creatCostIncomeList(CostIncomeReceiptVO vo) {
		// TODO Auto-generated method stub
		CostIncomeReceiptPO po=voToPO(vo);
		return cirdService.creatCostIncomeList(po);
	}
	
	public CostIncomeReceiptPO voToPO(CostIncomeReceiptVO vo) {
		// TODO Auto-generated method stub
		CostIncomeReceiptPO po=new CostIncomeReceiptPO(vo.getID(),vo.getUserID(),ReceiptType.COSTINCOMERECEPTION,vo.getState(),vo.getCost(),vo.getIncome(), vo.getProfit());
		return po;
	}

	/**
	 * 计算income
	 * doge说这玩意儿写在bl里.........
	 * */
	public double getIncome() {
		// TODO Auto-generated method stub
		ArrayList<CollectionReceiptPO> collectionReceiptPOs;
		try {
			collectionReceiptPOs = crdService.getAllCollection();
			double income=0;
			if(collectionReceiptPOs==null){
				System.out.println("collectionReceiptPOs is null");
				return -1;
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
			paymentReceiptPOs = prdService.getAllPaymentReceipt();
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
	
	public static void main(String[] args){
		try {
			CostIncomeReceiptDataService costIncomeData=(CostIncomeReceiptDataService)Naming.lookup("rmi://172.26.209.182:8888/CostIncomeReceiptDataService");
			costIncomeData.creatCostIncomeList(new CostIncomeReceiptPO("CBSYB-20151129", "lll", null, null, 2222, 3333, 1111));
			ArrayList<CostIncomeReceiptPO> pos=costIncomeData.getAllCostIncomeList();
			for(CostIncomeReceiptPO p:pos){
				System.out.println("ID: "+p.getID());
			}
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
	

}
