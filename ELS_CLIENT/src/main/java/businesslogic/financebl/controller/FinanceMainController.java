package businesslogic.financebl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.businessdataservice.BusinessDataService;
import dataservice.financedataservice.AccountDataService;
import dataservice.financedataservice.CollectionReceiptDataService;
import dataservice.financedataservice.CostIncomeReceiptDataService;
import dataservice.financedataservice.InitialStockDataService;
import dataservice.financedataservice.PaymentReceiptDataService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.repertorydataservice.RepertoryDataService;
import dataservice.userdataservice.UserDataService;
import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import po.AccountPO;
import po.BusinessStatementReceiptPO;
import po.CollectionReceiptPO;
import po.CostIncomeReceiptPO;
import po.GatheringReceiptPO;
import po.InitInfoPO;
import po.OrganizationPO;
import po.PaymentReceiptPO;
import po.RepertoryPO;
import po.UserPO;
import po.VehiclePO;
import presentation.financeui.AccountManagementPanel_main;
import presentation.financeui.BusinessStateReceiptPanel;
import presentation.financeui.CostIncomeReceiptPanel_new;
import presentation.financeui.FinanceFrame;
import presentation.financeui.InitialStockPanel_main;
import presentation.financeui.ReceiptPanel_new;
import type.ReceiptType;
import vo.AccountVO;
import vo.BusinessStatementReceiptVO;
import vo.CollectionReceiptVO;
import vo.CostIncomeReceiptVO;
import vo.GatheringReceiptVO;
import vo.InitInfoVO;
import vo.OrganizationVO;
import vo.PaymentReceiptVO;
import vo.RepertoryVO;
import vo.UserVO;
import vo.VehicleVO;

public class FinanceMainController {
	public static UserDataService userData;
	public static OrganizationDataService organizationData;
	public static BusinessDataService businessData;
	public static  RepertoryDataService repertoryData;
	public static AccountDataService accountData;
	
	public static CollectionReceiptDataService collectionData;
	public static PaymentReceiptDataService paymentData;
	public static CostIncomeReceiptDataService costincomeData;
	public static InitialStockDataService initData;
	
	public static ArrayList<CollectionReceiptVO> collectionReceiptVOs;
	public static ArrayList<PaymentReceiptVO> paymentReceiptVOs;
	public static ArrayList<CostIncomeReceiptVO> costIncomeReceiptVOs;
	public static ArrayList<InitInfoVO> initVOs;
	public static ArrayList<AccountVO> accountVOs;
	
	private  AccountBLController accountBLController;
	private  CollectionReceiptBLController collectionReceiptBLController;
	private  PaymentReceiptBLController paymentReceiptBLController;
	private  CostIncomeReceiptBLController costIncomeReceiptBLController;
	private  BusinessStatementReceiptBLController businessStatementReceiptBLController;
	private  InitialStockBLController initialStockBLController;
	
	private  FinanceFrame financeFrame;
	/**
	 * 财务部分的初始化在这里进行
	 * @throws Exception 
	 * */
	public FinanceMainController(String financeID) throws Exception{
		try {
			collectionData=DataFactory.getCollectionReceiptData();
			paymentData=DataFactory.getPaymentReceiptData();
			costincomeData=DataFactory.getCostIncomeData();
			initData=DataFactory.getInitialStockData();
			accountData=DataFactory.getAccountData();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//初始化四个controller：把操作拿出来做controller吗
				try {
					accountBLController=new AccountBLController();
					collectionReceiptBLController=new CollectionReceiptBLController();
					paymentReceiptBLController=new PaymentReceiptBLController();
					costIncomeReceiptBLController=new CostIncomeReceiptBLController();
					businessStatementReceiptBLController=new BusinessStatementReceiptBLController();
					initialStockBLController=new InitialStockBLController();
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
				
		
				//初始化界面
				financeFrame =new FinanceFrame();
				financeFrame.addFuncLabel(new AccountManagementPanel_main(accountBLController,financeFrame));
//				financeFrame.addFuncLabel(new ReceiptPanel_new(collectionReceiptBLController, paymentReceiptBLController, costIncomeReceiptBLController));
				financeFrame.addFuncLabel(new ReceiptPanel_new(collectionReceiptBLController, paymentReceiptBLController, financeFrame));
//				financeFrame.addFuncLabel(new CollectionReceiptPanel(collectionReceiptBLController,financeFrame));
//				financeFrame.addFuncLabel(new PaymentReceiptPanel(paymentReceiptBLController));
				financeFrame.addFuncLabel(new CostIncomeReceiptPanel_new(costIncomeReceiptBLController,financeFrame));
				financeFrame.addFuncLabel(new BusinessStateReceiptPanel(businessStatementReceiptBLController));
				financeFrame.addFuncLabel(new InitialStockPanel_main(initialStockBLController,financeFrame));
				financeFrame.showFrame();
				
		
//		try {
//			collectionReceiptVOs=cposToVOs(collectionData.getAllCollection());
//			paymentReceiptVOs=pposToVOs(paymentData.getAllPaymentReceipt());
//			costIncomeReceiptVOs=civosToPOs(costincomeData.getAllCostIncomeList());
//			initVOs=iposToVOs(initData.getAllInitInfo());
//			accountVOs=aposToVOs(accountData.showAll());
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
	}
	
	/**
	 * Account单个po转化为单个vo
	 * */
	public static AccountVO apoToVO(AccountPO po){
		String name=po.getName();
		double money=po.getMoney();
		AccountVO vo=new AccountVO(name, money);
		return vo;
	}
	
	/**
	 * Account po集合转化为vo集合
	 * */
	public static ArrayList<AccountVO> aposToVOs(ArrayList<AccountPO> pos){
		ArrayList<AccountVO> vos=new ArrayList<AccountVO>();
		for(AccountPO po: pos){
			AccountVO vo=apoToVO(po);
			vos.add(vo);
		}
		return vos;
	}
	/**
	 * Account单个vo转化为po
	 * */
	public static AccountPO avoToPO(AccountVO vo){
		String name=vo.getName();
		double money=vo.getMoney();
		AccountPO po=new AccountPO(name, money);
		return po;
	}
	
	
	/**
	 * 从Collectionpo和PaymentPo中取出需要的数据转化为vo
	 * 这里有比较多的代码重复——可以写成controller(静态方法)
	 * */
	public static BusinessStatementReceiptVO bpoToVO(BusinessStatementReceiptPO businessstatementReceiptPO){

		BusinessStatementReceiptVO businessStatementReceiptVO;
	
//		ArrayList<CollectionReceiptPO> collectionReceiptPOs=crdService.getAllCollection();
		ArrayList<CollectionReceiptPO> collectionReceiptPOs=businessstatementReceiptPO.getCollectionPOs();
		ArrayList<CollectionReceiptVO> collectionReceiptVOs;
		if(collectionReceiptPOs==null){
			System.out.println("CollectionReceiptPOs is null-------BusinessstatementReceiptBL");
			collectionReceiptVOs=null;
		}
		else{
			collectionReceiptVOs=new ArrayList<CollectionReceiptVO>();
			for(CollectionReceiptPO p:collectionReceiptPOs){
				CollectionReceiptVO vo=new CollectionReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(), p.getIncome(), p.getDate(), p.getAccount());
				collectionReceiptVOs.add(vo);
			}
		}		

//		ArrayList<PaymentReceiptPO> paymentReceiptPOs=prdService.getAllPaymentReceipt();
		ArrayList<PaymentReceiptPO> paymentReceiptPOs=businessstatementReceiptPO.getPaymentPOs();
		ArrayList<PaymentReceiptVO> paymentReceiptVOs;
		if(paymentReceiptPOs==null){
			System.out.println("ArrayList<PaymentReceiptPO> pos is null  ------------------BusinessstatementReceiptBL");
		    paymentReceiptVOs=null;
		}
		else{
			paymentReceiptVOs=new ArrayList<PaymentReceiptVO>();
			for(PaymentReceiptPO p:paymentReceiptPOs){
				PaymentReceiptVO vo=new PaymentReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(), p.getRent(), p.getFare(),p.getSalary(), p.getDate() , p.getAccount(), p.getName());
				paymentReceiptVOs.add(vo);
			}
		}
		
		businessStatementReceiptVO=new BusinessStatementReceiptVO(businessstatementReceiptPO.getBeginTime(), businessstatementReceiptPO.getEndTime(), collectionReceiptVOs, paymentReceiptVOs);
		return businessStatementReceiptVO;
	}

	/**
	 * 将vo转化为po存入BusinessstatementReceiptPO
	 * */
	public static BusinessStatementReceiptPO bvoToPO(BusinessStatementReceiptVO vo){
		ArrayList<CollectionReceiptVO> collectionReceiptVOs=vo.cvos;
		ArrayList<PaymentReceiptVO> paymentReceiptVOs=vo.pvos;
		ArrayList<CollectionReceiptPO> collectionReceiptPOs=new ArrayList<CollectionReceiptPO>();
		ArrayList<PaymentReceiptPO> paymentReceiptPOs=new ArrayList<PaymentReceiptPO>();
		for(CollectionReceiptVO v1:collectionReceiptVOs){
			CollectionReceiptPO po=new CollectionReceiptPO(v1.getID()	,v1.getUserID(),v1.getType(), v1.getState(), v1.getIncome(),v1.getDate(),v1.getAccount());
			collectionReceiptPOs.add(po);
		}
		for(PaymentReceiptVO v2:paymentReceiptVOs){
			PaymentReceiptPO po=new PaymentReceiptPO(v2.getID(), v2.getUserID(),v2.getType(), v2.getState(), v2.getRent(), v2.getFare(),v2.getSalary(), v2.getDate(), v2.getAccount(), v2.getName());
			paymentReceiptPOs.add(po);
		}
		BusinessStatementReceiptPO po=new BusinessStatementReceiptPO(vo.beginTime, vo.endTime, collectionReceiptPOs, paymentReceiptPOs);
		return po;
	}
	
	
	/**
	 * CollectionVO to PO
	 * */
	public static CollectionReceiptPO cvoToPO(CollectionReceiptVO vo){
		CollectionReceiptPO collectionReceiptPO=new CollectionReceiptPO(vo.getID(), vo.getUserID(), vo.getType(), vo.getState(), vo.getIncome(), vo.getDate(), vo.getAccount());
		return collectionReceiptPO;
	}
	/**
	 * ArrayList<CollectionVO> to PO 
	 * */
	public static ArrayList<CollectionReceiptPO> cvosToPOs(ArrayList<CollectionReceiptVO> vos){
		return null;
	}
	/**
	 * GatheringPO to VO,显示所有的vo
	 * */
	public static GatheringReceiptVO gpoToVO(GatheringReceiptPO po){
		if(po==null){
			return null;
		}
		else{
			GatheringReceiptVO gatheringReceiptVO;
			gatheringReceiptVO=new GatheringReceiptVO(IntermediateMainController.poToVO(po.getBusinesShall()), po.getTime(), po.getExpressIDs(), po.getMoney(), po.getTotalmoney(), po.getReceiptID(), po.getReceiptState());
		return gatheringReceiptVO;
		}
	}
	
	/**
	 * ArrayList<GatheringPO> to  VO
	 * */
	public static ArrayList<GatheringReceiptVO> gposToVOs(ArrayList<GatheringReceiptPO> pos){
		ArrayList<GatheringReceiptVO> gatheringReceiptVOs;
		if(pos==null){
			return null;
		}
		else{
			gatheringReceiptVOs=new ArrayList<GatheringReceiptVO>();
			for(GatheringReceiptPO p:pos){
				GatheringReceiptVO vo=gpoToVO(p);
				gatheringReceiptVOs.add(vo);
			}
		}
		return null;
	}
	/**
	 * ColletionPO to VO
	 * */
	public static CollectionReceiptVO cpoToVO(CollectionReceiptPO po){
		CollectionReceiptVO collectionReceiptVO;
		if(po==null){
			return null;
		}
		else{
			collectionReceiptVO=new CollectionReceiptVO(po.getID(), po.getUserID(), po.getType(), po.getState(), po.getIncome() , po.getDate(), po.getAccount());
			return collectionReceiptVO;
		}
	}
	/**
	 * CollectionPO to VO
	 * */
	public static ArrayList<CollectionReceiptVO> cposToVOs(ArrayList<CollectionReceiptPO> pos){
		ArrayList<CollectionReceiptVO> collectionReceiptVOs;
		if(pos==null){
			return null;
		}
		else{
			collectionReceiptVOs=new ArrayList<CollectionReceiptVO>();
			for(CollectionReceiptPO p:pos){
				CollectionReceiptVO vo=new CollectionReceiptVO(p.getID(), p.getUserID(), p.getType(), p.getState(),p.getIncome() , p.getDate(), p.getAccount());
				collectionReceiptVOs.add(vo);
			}
			return collectionReceiptVOs;
		}
	}

	/**
	 * 成本收益表
	 * */
	public static CostIncomeReceiptPO voToPO(CostIncomeReceiptVO vo) {
		// TODO Auto-generated method stub
		CostIncomeReceiptPO po=new CostIncomeReceiptPO(vo.getID(),vo.getUserID(),ReceiptType.COSTINCOMERECEPTION,vo.getState(),vo.getCost(),vo.getIncome(), vo.getProfit());
		return po;
	}
	
	/**
	 * 期初init
	 * */
	/**
	 * 取出各个PO中的数据并po变vo，添加到InitInfoVO中
	 * Q：这里取PO是通过每个DataService去调吗
	 * @throws RemoteException 
	 * */
	public static InitInfoVO ipoToVO(InitInfoPO po) throws RemoteException{
		if(po==null)
			return null;
		
//		ArrayList<UserPO> userPOs=po.getUserPOs();
		ArrayList<UserPO> userPOs=userData.showAllUsers();
		ArrayList<UserVO> userVOs;
		if(userPOs==null){
			userVOs=null;
		}
		else{
			userVOs=new ArrayList<UserVO>();
			for(UserPO p:userPOs){
				UserVO vo=new UserVO(p.getName(), p.getID(), p.getPassword(), p.getProfession(), p.getOrganization(), p.getSalaryPlan(), p.getAuthority(), p.getGrades());
				userVOs.add(vo);
			}
		}
		
//		ArrayList<OrganizationPO> organizationPOs=po.getOrganizationPOs();
		ArrayList<OrganizationPO> organizationPOs=organizationData.showAllOrganizations();
		ArrayList<OrganizationVO> organizationVOs;
		if(organizationPOs==null){
			organizationVOs=null;
		}
		else{
			organizationVOs=new ArrayList<OrganizationVO>();
			for(OrganizationPO p:organizationPOs){
				OrganizationVO vo=new OrganizationVO(p.getCategory(), p.getOrganizationID(), p.getName());
				organizationVOs.add(vo);
			}
		}
		
//		ArrayList<VehiclePO> vehiclePOs=po.getVehiclePOs();
		ArrayList<VehiclePO> vehiclePOs=businessData.getVehicleInfos(null);
		ArrayList<VehicleVO> vehicleVOs;
		if(vehiclePOs==null){
			vehicleVOs=null;
		}
		else{
		vehicleVOs=new ArrayList<VehicleVO>();
		for(VehiclePO p:vehiclePOs){
			VehicleVO vo=new VehicleVO(p.getID(), p.getEngineNumber(), p.getLicensePlateNumber(), p.getLowNumberPlate(), p.getBuyTime(), p.getBuyTime(),IntermediateMainController.poToVO(p.getDestination()), p.getDestinationCity(),IntermediateMainController.poToVO(p.getLocal()), BusinessMainController.driverPOToVO(p.getDriver()));
			vehicleVOs.add(vo);
		}
		}
		
	
		
//		ArrayList<RepertoryPO> repertoryPOs=po.getRepertoryPOs();
		ArrayList<RepertoryPO> repertoryPOs=repertoryData.showAllRepertorys();
		ArrayList<RepertoryVO> repertoryVOs;
		if(repertoryPOs==null){
			repertoryVOs=null;
		}
		else{
			repertoryVOs=new ArrayList<RepertoryVO>();
			for(RepertoryPO p:repertoryPOs){
				RepertoryVO vo=new RepertoryVO(p.getRepertoryID(), p.getOwnerID());
				repertoryVOs.add(vo);
			}
		}
		
//		ArrayList<AccountPO> accountPOs=po.getAccountPOs();
		ArrayList<AccountPO> accountPOs=accountData.showAll();
		ArrayList<AccountVO> accountVOs;
		if(accountPOs==null){
			accountVOs=null;
		}
		else{
			accountVOs=new ArrayList<AccountVO>();
			for(AccountPO p:accountPOs){
				AccountVO vo=new AccountVO(p.getName(), p.getMoney());
				accountVOs.add(vo);
			}
		}
		InitInfoVO initInfoVO=new InitInfoVO(po.getDate(), userVOs, organizationVOs, vehicleVOs, repertoryVOs, accountVOs);
		return initInfoVO;
	}
	
	/**
	 * pos变vos
	 * @throws RemoteException 
	 * */
	public static ArrayList<InitInfoVO> iposToVOs(ArrayList<InitInfoPO> pos) throws RemoteException{
		ArrayList<InitInfoVO> initInfoVOs;
		if(pos==null){
			initInfoVOs=null;
		}
		else{
		initInfoVOs=new ArrayList<InitInfoVO>();
		for(InitInfoPO p:pos){
			InitInfoVO vo=ipoToVO(p);
			initInfoVOs.add(vo);
		}
		}
		return initInfoVOs;
	}
	
	/**
	 * 将已获取的vo变po，存入持久化变量
	 * */
	 public static InitInfoPO ivoToPO(InitInfoVO vo) {
		// TODO Auto-generated method stub
		if(vo==null)
			return null;
		
		//-----------------------------------------需要的参数------------------------------------------------------------
		ArrayList<UserVO> userVOs=vo.getUserVOs();
		ArrayList<UserPO> userPOs;
		if(userVOs==null){
			userPOs=null;
		}
		else{
			userPOs=new ArrayList<UserPO>();
			for(UserVO v:userVOs){
				UserPO po=new UserPO(v.getName(), v.getID(), v.getProfession(), v.getOrganization(), v.getSalaryPlan(), v.getAuthority(), v.getGrades());
				userPOs.add(po);
			}
		}
		
		
		ArrayList<OrganizationVO>organizationVOs=vo.getOrganizationVOs();
		ArrayList<OrganizationPO> organizationPOs;
		if(organizationVOs==null){
			organizationPOs=null;
		}
		else{
			organizationPOs=new ArrayList<OrganizationPO>();
			for(OrganizationVO v:organizationVOs){
				OrganizationPO po=new OrganizationPO(v.getCategory(), v.getOrganizationID(), v.getName());
				organizationPOs.add(po);
			}
		}
		
		ArrayList<VehicleVO> vehicleVOs=vo.getVehicleVOs();
		ArrayList<VehiclePO> vehiclePOs;
		if(vehicleVOs==null){
			vehiclePOs=null;
		}
		else{
			vehiclePOs=new ArrayList<VehiclePO>();
			for(VehicleVO v:vehicleVOs){
				VehiclePO po=new VehiclePO(v.ID, v.engineNumber, v.licensePlateNumber, v.lowNumberPlate, v.buyTime, v.serviceTime, IntermediateMainController.voToPO(v.destination), v.destinationCity, IntermediateMainController.voToPO(v.local), BusinessMainController.driverVOToPO(v.driver));
				vehiclePOs.add(po);
			}
		}
		
		ArrayList<RepertoryVO> repertoryVOs=vo.getRepertoryVOs();
		ArrayList<RepertoryPO> repertoryPOs;
		if(repertoryVOs==null){
			repertoryPOs=null;
		}
		else{
			repertoryPOs=new ArrayList<RepertoryPO>();
			for(RepertoryVO v:repertoryVOs){
				RepertoryPO po=new RepertoryPO(v.getRepertoryID(), v.getOwnerID(), v.getMaxRow(), v.getMaxShelf(), v.getMaxDigit(), v.getWarningRatio(), v.getStockNumArray());
				repertoryPOs.add(po);
			}
		}
		
		ArrayList<AccountVO> accountVOs=vo.getAccountVOs();
		ArrayList<AccountPO> accountPOs;
		if(accountVOs==null){
			accountPOs=null;
		}
		else{
			accountPOs=new ArrayList<AccountPO>();
			for(AccountVO v:accountVOs){
				AccountPO po=new AccountPO(v.getName(), v.getMoney());
				accountPOs.add(po);
			}
		}
		
		InitInfoPO initPO;
		try {
			initPO = new InitInfoPO(vo.getTime(), userPOs, organizationPOs, vehiclePOs, repertoryPOs, accountPOs);
			return initPO;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	
	/**
	 * vos to pos
	 * */
	public static ArrayList<InitInfoPO> ivosToPOs(ArrayList<InitInfoVO> vo){
		if(vo==null){
			return null;
		}
		else{
			ArrayList<InitInfoPO> pos=new ArrayList<InitInfoPO>();
			for(InitInfoVO v:vo){
				InitInfoPO po=ivoToPO(v);
				pos.add(po);
		}
			return pos;
		}
	
	}

	
	public static PaymentReceiptPO pvoToPO(PaymentReceiptVO vo){
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

	
	public static PaymentReceiptVO ppoToVO(PaymentReceiptPO po){
		PaymentReceiptVO vo=new PaymentReceiptVO(po.getID(), po.getUserID(), po.getType(), po.getState(),po.getRent(), po.getFare(), po.getSalary(),po.getDate(), po.getAccount(), po.getName());
		return vo;
	}
	public static ArrayList<PaymentReceiptVO> pposToVOs(ArrayList<PaymentReceiptPO> pos){
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
	
	public static CostIncomeReceiptVO cipoToVO(CostIncomeReceiptPO po){
		return new CostIncomeReceiptVO(po.getID(), po.getUserID(), po.getType(), po.getState(), po.getCost(), po.getIncome(), po.getProfit());
	}
	
	public static ArrayList<CostIncomeReceiptVO> civosToPOs(ArrayList<CostIncomeReceiptPO> pos){
		ArrayList<CostIncomeReceiptVO> vos=new ArrayList<CostIncomeReceiptVO>();
		for(CostIncomeReceiptPO p:pos){
			CostIncomeReceiptVO vo=cipoToVO(p);
			vos.add(vo);
		}
		return vos;
	}
	
	
	public static void main(String[] args) throws Exception{
		new FinanceMainController("CW-00001");
	}
	}
