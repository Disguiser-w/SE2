package businesslogic.financebl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.businessdataservice.BusinessDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import dataservice.intermediatedataservice.IntermediateDataService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.userdataservice.UserDataService;
import po.FarePO;
import po.OrganizationPO;
import po.PaymentReceiptPO;
import po.UserPO;
import type.OrganizationType;
import type.SalaryPlanType;
import vo.PaymentItemVO;
import vo.PaymentReceiptVO;
import businesslogic.receiptbl.ReceiptBL;
import businesslogicservice.financeblservice.PaymentReceiptBLService;


public class PaymentReceiptBL extends ReceiptBL implements PaymentReceiptBLService{
	
	PaymentReceiptDataService prdService;
	UserDataService udService;
	BusinessDataService bdService;
	OrganizationDataService odService;
	IntermediateDataService idService;

	/**
	 * 创建付款单并发送给总经理
	 * */
	public int creatPaymentReceipt(PaymentReceiptVO vo){
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
			ppo=new PaymentReceiptPO(vo.getID(), vo.getUserID(), vo.getType(), vo.getState(),vo.getRent(), vo.getFare(),vo.getSalary(), vo.getDate(), vo.getAccount(), vo.getName());
			return ppo;
		}
	}
	/**
	 * 试运行
	 * paymentItems怎么用还没想好-------这个怎么写啊23333
	 * 本宝宝要改付款单的用例了，，，之前写的功能太复杂了23333
	 * 初步想法：所有判断付款类型，付款金额的操作都写在paymentItem里
	 * */
	public int excute(PaymentReceiptVO vo){
		AccountBL account=new AccountBL();
//		ArrayList<PaymentReceiptVO> pvos=vo.ge
//		ArrayList<PaymentItemVO> paymentItems=vo.getPaymentItems();
//		for(PaymentItemVO v:paymentItems){
//			account.delMoney(v.getAccount(), v.getMoney());
//		}
		account.delMoney(vo.getAccount(), vo.getCost());
		System.out.println("执行成功！");
		return 0;
	}

	public PaymentReceiptVO getPaymentReceipt(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取所有的付款单
	 * */
	public ArrayList<PaymentReceiptVO> getAllPaymentReceipt() {
		// TODO Auto-generated method stub
		return pposToVOs(prdService.getAllPaymentReceipt());
	}
	
	public PaymentReceiptVO ppoToVO(PaymentReceiptPO po){
		PaymentReceiptVO vo=new PaymentReceiptVO(po.getID(), po.getUserID(), po.getType(), po.getState(),po.getRent(), po.getFare(), po.getSalary(),po.getDate(), po.getAccount(), po.getName());
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
				PaymentReceiptVO vo=new PaymentReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(), p.getRent(),p.getFare(),p.getSalary(),p.getDate(), p.getAccount(),p.getName());
				paymentReceiptVOs.add(vo);
			}
			return paymentReceiptVOs;
		}
	}
	

	/**
	 * 自动获取ID
	 * */
	public String getPaymentReceiptListID() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 到时候ui层调用这个方法来计算成本，再加到vo里面
	 * */
	public double getSalary(String time){
		// TODO Auto-generated method stub
		double salary=0;
		try {
			ArrayList<UserPO> userpos=udService.showAllUsers();
			for(UserPO p:userpos){
				//这个地方要是从两个类中来好奇怪.......
				//快递员：提成+基础工资
				if(p.getSalaryPlan()==SalaryPlanType.courierSalaryPlan){
					salary+=p.getGrades();
				}
				//司机：计次
				else if(p.getSalaryPlan()==SalaryPlanType.driverSalaryPlan){
					salary+=p.getGrades();
				}
				else if(p.getSalaryPlan()==SalaryPlanType.countermanSalaryPlan){
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return salary;
	}

	
	/**
	 * 运费：但是清0怎么处理
	 * 参数作为Time比较好处理还是作为时间区间比较好处理
	 * */
	public double getFare(String time) {
		// TODO Auto-generated method stub
		ArrayList<OrganizationPO> organizationPOs=odService.showAllOrganizations();
		ArrayList<OrganizationPO> pos_intermedia=new ArrayList<OrganizationPO>();
		for(OrganizationPO p:organizationPOs){
			if(p.getCategory()==OrganizationType.intermediateCenter){
				pos_intermedia.add(p);
			}
		}
		ArrayList<FarePO> farepos=bdService.getFarePO(time);
		
		double fare=0;;
		if(farepos==null){
			System.out.println("获取farepos失败");
		}
		for(FarePO p:farepos){
			fare+=p.getMoney();
		}
		return fare;
	}

	
	/**
	 * ：统计总的机构数：营业厅租金默认为5000；中转中心租金默认为10000；
	 * ——一个月计算一次
	 * */
	public double getRent(String time) {
		// TODO Auto-generated method stub
		ArrayList<OrganizationPO> opos=odService.showAllOrganizations();
		int numOfBusinessHall=0;
		int numOfIntermedia=0;
		for(OrganizationPO p:opos){
			if(p.getCategory()==OrganizationType.businessHall){
				numOfBusinessHall++;
			}
			if(p.getCategory()==OrganizationType.intermediateCenter){
				numOfIntermedia++;
			}
		}
		double rent=5000*numOfBusinessHall+10000*numOfIntermedia;
		return rent;
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
