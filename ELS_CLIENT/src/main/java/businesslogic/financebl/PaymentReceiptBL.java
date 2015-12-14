package businesslogic.financebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.businessdataservice.BusinessDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import dataservice.intermediatedataservice.IntermediateDataService;
import dataservice.managedataservice.BasicSalaryDataService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.managedataservice.PerWageDataService;
import dataservice.userdataservice.UserDataService;
import po.OrganizationPO;
import po.PaymentReceiptPO;
import po.UserPO;
import type.OrganizationType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.PaymentReceiptVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.financebl.controller.FinanceMainController;
import businesslogic.receiptbl.ReceiptBL;
import businesslogic.receiptbl.getDate;


public class PaymentReceiptBL extends ReceiptBL {
	
	PaymentReceiptDataService paymentData;
	UserDataService userData;
	BusinessDataService businessData;
	OrganizationDataService organizationData;
	IntermediateDataService intermediateData;
	BasicSalaryDataService basicSalaryData;
	PerWageDataService perWageData;
	

	public PaymentReceiptBL(){
		super();
			try {
			userData=DataFactory.getUserData();
			businessData=DataFactory.getBusinessData();
			organizationData=DataFactory.getOrganizationData();
			intermediateData=DataFactory.getIntermediateData();
			basicSalaryData=DataFactory.getBasicSalaryData();
			perWageData= DataFactory.getPerWageData();
			
			paymentData=DataFactory.getPaymentReceiptData();
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
	 * 创建付款单并发送给总经理
	 * */
	public int creatPaymentReceipt(PaymentReceiptVO vo){
		// TODO Auto-generated method stub
		PaymentReceiptPO po=FinanceMainController.pvoToPO(vo);
		update(vo);
		try {
			return paymentData.creatPaymentReceipt(po);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("创建付款单失败");
			return 2;
		}
	}
	
//	public PaymentReceiptPO pvoToPO(PaymentReceiptVO vo){
//		PaymentReceiptPO ppo;
//		if(vo==null){
//			System.out.println("PaymentReceiptVO vo is null-----------PaymentReceiptBL");
//			return null;
//		}
//		else{
//			ppo=new PaymentReceiptPO(vo.getID(), vo.getUserID(), vo.getType(), vo.getState(),vo.getRent(), vo.getFare(),vo.getSalary(), vo.getDate(), vo.getAccount(), vo.getName());
//			return ppo;
//		}
//	}
	/**
	 * 试运行
	 * paymentItems怎么用还没想好-------这个怎么写啊23333
	 * 本宝宝要改付款单的用例了，，，之前写的功能太复杂了23333
	 * 初步想法：所有判断付款类型，付款金额的操作都写在paymentItem里
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 * */
	public int excute(PaymentReceiptVO vo) throws MalformedURLException, RemoteException, NotBoundException{
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
		try {
			return FinanceMainController.pposToVOs(paymentData.getAllPaymentReceipt());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取所有的付款单失败");
			return null;
		}
	}
	
//	
	

	/**
	 * 自动获取ID
	 * 入款单相当于是一个月或两个星期产生一次
	 * 所以RKD-20151129
	 * */
	public String getPaymentReceiptListID() {
		// TODO Auto-generated method stub
		return "RKD-"+getDate.getdate();
	}

	/**
	 * 到时候ui层调用这个方法来计算成本，再加到vo里面
	 * 频率：一个月计算一次(get)
	 * */
	public double getSalary(String time){
		// TODO Auto-generated method stub
		double salary=0;
		try {
			ArrayList<UserPO> userpos=userData.showAllUsers();
			for(UserPO p:userpos){
				//快递员：提成+基础工资
				if(p.getSalaryPlan()==SalaryPlanType.courierSalaryPlan){
					double perWage=perWageData.findPerWage(ProfessionType.courier).getPerWage();
					double basicSalary=basicSalaryData.findBasicSalary(ProfessionType.courier).getBasicSalary();
					salary+=p.getGrades()*perWage+basicSalary;
				}
				//司机：计次
				else if(p.getSalaryPlan()==SalaryPlanType.driverSalaryPlan){
					double perWage=perWageData.findPerWage(ProfessionType.driver).getPerWage();
					salary+=p.getGrades()*perWage;
				}
				//营业厅业务员
				else if(p.getSalaryPlan()==SalaryPlanType.basicStaffSalaryPlan){
				   double basicSalary=basicSalaryData.findBasicSalary(ProfessionType.businessHallCounterman).getBasicSalary();
				   salary+=basicSalary;
				}
				//系统管理员
				else if(p.getSalaryPlan()==SalaryPlanType.basicStaffSalaryPlan){
					   double basicSalary=basicSalaryData.findBasicSalary(ProfessionType.administrator).getBasicSalary();
					   salary+=basicSalary;
				}
				//仓库管理员
				else if(p.getSalaryPlan()==SalaryPlanType.basicStaffSalaryPlan){
					   double basicSalary=basicSalaryData.findBasicSalary(ProfessionType.stockman).getBasicSalary();
					   salary+=basicSalary;
				}
				//财务人员
				else if(p.getSalaryPlan()==SalaryPlanType.basicStaffSalaryPlan){
					   double basicSalary=basicSalaryData.findBasicSalary(ProfessionType.financialStaff).getBasicSalary();
					   salary+=basicSalary;
				}
				//总经理
				else if(p.getSalaryPlan()==SalaryPlanType.basicStaffSalaryPlan){
					   double basicSalary=basicSalaryData.findBasicSalary(ProfessionType.manager).getBasicSalary();
					   salary+=basicSalary;
				}
			}
			return salary;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("工资计算出现异常");
			return 0.0;
		}
	}

	
	/**
	 * 运费：但是清0怎么处理
	 * 参数作为Time比较好处理还是作为时间区间比较好处理
	 * 这个等盛盛更完再写
	 * */
	public double getFare(String time) {
		// TODO Auto-generated method stub
		ArrayList<OrganizationPO> organizationPOs;
		try {
			organizationPOs = organizationData.showAllOrganizations();
			ArrayList<OrganizationPO> pos_intermedia=new ArrayList<OrganizationPO>();
			for(OrganizationPO p:organizationPOs){
				if(p.getCategory()==OrganizationType.intermediateCenter){
					pos_intermedia.add(p);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	
	/**
	 * ：统计总的机构数：营业厅租金默认为5000；中转中心租金默认为10000；
	 * ——一个月计算一次
	 * */
	public double getRent(String time) {
		// TODO Auto-generated method stub
		ArrayList<OrganizationPO> opos;
		try {
			opos = organizationData.showAllOrganizations();
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
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取租金信息失败");
			return 0.0;
		}
		
	}

	/**
	 * 获取未审批的所有付款单,审批需要
	 * */
	public ArrayList<PaymentReceiptVO> getUnapprovedPaymentReceipt() {
		// TODO Auto-generated method stub
		try {
			return FinanceMainController.pposToVOs(paymentData.getUnapprovedPaymentReceipt());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("获取未审批的付款单失败");
			return null;
		}
	}
	
	

	public static void main(String[] args){
		try {
			PaymentReceiptDataService paymentData=(PaymentReceiptDataService)Naming.lookup("rmi://172.26.209.182:8888/PaymentReceiptDataService");

//			PaymentReceiptPO po1=new PaymentReceiptPO("FKD-20110101-00001", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20110101", "boss", "本宝宝");
//			PaymentReceiptPO po2=new PaymentReceiptPO("FKD-20110101-00002", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20110101", "boss", "本宝宝");
//			PaymentReceiptPO po3=new PaymentReceiptPO("FKD-20151126-00001", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151126", "boss", "本宝宝");
//			PaymentReceiptPO po4=new PaymentReceiptPO("FKD-20151127-00001", "=.=", ReceiptType.PAYMENTRECEIPT, ReceiptState.DRAFT, 2000, 1000, 1000, "20151127", "boss", "本宝宝");
//			paymentData.creatPaymentReceipt(po1);
//			paymentData.creatPaymentReceipt(po2);
//			paymentData.creatPaymentReceipt(po3);
//			paymentData.creatPaymentReceipt(po4);
			
			ArrayList<PaymentReceiptPO> pos=paymentData.getAllPaymentReceipt();
			for(PaymentReceiptPO p:pos){
				System.out.println("Name："+p.getID());
			}
			System.out.println("------------------------------------------------------------------------------");
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
	
	
//	public PaymentReceiptVO ppoToVO(PaymentReceiptPO po){
//		PaymentReceiptVO vo=new PaymentReceiptVO(po.getID(), po.getUserID(), po.getType(), po.getState(),po.getRent(), po.getFare(), po.getSalary(),po.getDate(), po.getAccount(), po.getName());
//		return vo;
//	}
//	public ArrayList<PaymentReceiptVO> pposToVOs(ArrayList<PaymentReceiptPO> pos){
//		ArrayList<PaymentReceiptVO> paymentReceiptVOs=new ArrayList<PaymentReceiptVO>();
//		if(pos==null){
//			System.out.println("ArrayList<PaymentReceiptPO> pos is null------PaymentReceiptBL");
//			return null;
//		}
//		else{
//			for(PaymentReceiptPO p:pos){
//				PaymentReceiptVO vo=new PaymentReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(), p.getRent(),p.getFare(),p.getSalary(),p.getDate(), p.getAccount(),p.getName());
//				paymentReceiptVOs.add(vo);
//			}
//			return paymentReceiptVOs;
//		}
//	}

}
