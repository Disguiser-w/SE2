package businesslogic.financebl.driver;

import java.util.ArrayList;

import type.AuthorityType;
import type.OrganizationType;
import type.ProfessionType;
import type.ReceiptState;
import type.ReceiptType;
import type.SalaryPlanType;
import type.Sexuality;
import vo.AccountVO;
import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.CostIncomeReceiptVO;
import vo.DriverVO;
import vo.GatheringReceiptVO;
import vo.InitInfoVO;
import vo.OrganizationVO;
import vo.PaymentReceiptVO;
import vo.RepertoryVO;
import vo.UserVO;
import vo.VehicleVO;
import businesslogic.financebl.AccountBL;
import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import businesslogic.financebl.controller.CollectionReceiptBLController;
import businesslogic.financebl.controller.CostIncomeReceiptBLController;
import businesslogic.financebl.controller.InitialStockBLController;
import businesslogic.financebl.controller.PaymentReceiptBLController;
import businesslogic.receiptbl.GetDate;

public class FinanceBL_driver {

	public static void main(String[] args){
		AccountBL accountController = new AccountBL();
		AccountVO test = new AccountVO("狗剩", 200);
		int result = accountController.addAccount(test);
		System.out.println(result);
		ArrayList<AccountVO> vos=accountController.findByKeyword("狗");
		System.out.println(vos.get(0).name);
		AccountVO vo=accountController.findbyName("狗剩");
		
		System.out.println(vo.money);
		 accountController.modifyAccount(vo, "doge");
		 accountController.showAll();
		 
		 accountController.addMoney("doge", 100);
		 accountController.delMoney("doge", 100);
		accountController.deleteAccount("doge");
		
		
		BusinessStatementReceiptBLController businessStatementReceiptBLController= new BusinessStatementReceiptBLController();
		BusinessStatementReceiptVO bvo=businessStatementReceiptBLController.showBSList("20101010", "20160101");
		System.out.println(bvo.cvos.get(0));
		businessStatementReceiptBLController.export(bvo);
		
		CollectionReceiptBLController controller = new CollectionReceiptBLController();
		CollectionReceiptVO cvo = new CollectionReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
				ReceiptState.SUBMIT, 3000, GetDate.getdate(), "鼓楼");
		CollectionReceiptVO vol = new CollectionReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
				ReceiptState.APPROVE, 3000, GetDate.getdate(), "鼓楼");
		controller.creatCollection(cvo);
		ArrayList<CollectionReceiptVO> cvos=controller.getAllCollection();
		System.out.println(cvos.size());
		controller.getCollectionListID();
		ArrayList<GatheringReceiptVO> gvos=controller.getGatheringByTime("20151220");
		controller.getUnapprovedCollectionReceipt();
		controller.getCollection("HJSKD-20151220");
		double money=controller.getTotalMoney(gvos);
		System.out.println(money);
		controller.saveSubmittedCollectionReceiptInfo(vol);
		
		CostIncomeReceiptBLController costIncomeReceiptBLController = new CostIncomeReceiptBLController();
		CostIncomeReceiptVO civo = new CostIncomeReceiptVO("CBSYB-20151220", "CW-00001", ReceiptType.COSTINCOMERECEPTION,
				ReceiptState.DISAPPROVE	, 3000, 5000, 2000);
		costIncomeReceiptBLController.creatCostIncomeList(civo);
		String costIncomeID=costIncomeReceiptBLController.getCostIncomeListID();
		System.out.println(costIncomeID);
		
		double cost =costIncomeReceiptBLController.getCost();
		double income=costIncomeReceiptBLController.getIncome();
		double profit = costIncomeReceiptBLController.getProfit(income, cost);
		System.out.println(cost+" "+income+" "+profit);
		
		InitialStockBLController initController = new InitialStockBLController();
		ArrayList<UserVO> userPOs=new ArrayList<UserVO>();
		ArrayList<OrganizationVO> organizationPOs=new ArrayList<OrganizationVO>();
		ArrayList<VehicleVO> vehiclePOs=new ArrayList<VehicleVO>();
		ArrayList<RepertoryVO> repertoryPOs=new ArrayList<RepertoryVO>();
		ArrayList<AccountVO> accountPOs=new ArrayList<AccountVO>();

		UserVO user=new UserVO("ben", "CW-00001","******", ProfessionType.financialStaff, "总部",SalaryPlanType.basicStaffSalaryPlan, AuthorityType.commonFianacialStaff,0);
		OrganizationVO org=new OrganizationVO(OrganizationType.businessHall, "025001", "鼓楼");
		VehicleVO vehicle=new VehicleVO("SJ", "10", "320", "10", "2011", "3", org, "鼓楼", org, new DriverVO("SJ", "haha", "1988", "32", "32", org, Sexuality.FEMALE, "hh", 0));
		RepertoryVO repertory=new RepertoryVO("0251", "SJ");
		AccountVO account=new AccountVO("CW", 100);
		userPOs.add(user);
		organizationPOs.add(org);
		vehiclePOs.add(vehicle);
		repertoryPOs.add(repertory);
		accountPOs.add(account);
		
		InitInfoVO ivo = new InitInfoVO("20151221", "CW-00001",userPOs, organizationPOs, vehiclePOs, repertoryPOs, accountPOs);
		
		initController.initInfo(ivo, "20151221");
		
		ArrayList<InitInfoVO> ivos=initController.getAllInitInfo();
		System.out.println(ivos.get(0).time);
		
		InitInfoVO ivo1=initController.getInitInfo("20151220");
		System.out.println(ivo1.time);
		
		ArrayList<VehicleVO> vvos=initController.getVehicleInfo();
		System.out.println(vvos.get(0).destinationCity);
		
		PaymentReceiptBLController paymentController = new PaymentReceiptBLController();
		PaymentReceiptVO pvo = new PaymentReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
				ReceiptState.SUBMIT, 3000,2000,3000, GetDate.getdate(), "鼓楼","doge");
		PaymentReceiptVO pvol = new PaymentReceiptVO("HJSKD-20151220", "CW-00001", ReceiptType.COLLECTIONRECEIPT,
				ReceiptState.APPROVE, 3000,2000,3000, GetDate.getdate(), "鼓楼","doge");
		paymentController.creatPaymentReceipt(pvo);
		ArrayList<PaymentReceiptVO> pvos=paymentController.getAllPaymentReceipt();
		System.out.println(pvos.size());
		paymentController.getPaymentReceiptListID();
		paymentController.getUnapprovedPaymentReceipt();
		paymentController.getFare("20151220");
		paymentController.getRent("20151220");
		double cost1=paymentController.getSalary("20151020");
		System.out.println(cost1);
		paymentController.saveSubmittedPaymentReceiptInfo(pvol);


	}
}
