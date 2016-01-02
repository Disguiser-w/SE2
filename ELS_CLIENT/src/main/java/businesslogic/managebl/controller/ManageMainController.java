package businesslogic.managebl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businesslogic.datafactory.DataFactory;
import businesslogic.financebl.controller.BusinessStatementReceiptBLController;
import businesslogic.financebl.controller.CostIncomeReceiptBLController;
import businesslogic.financebl.controller.LogDiaryBLController;
import businesslogic.userbl.controller.UserManageController;
import common.ImageGetter;
import dataservice.managedataservice.BasicSalaryDataService;
import dataservice.managedataservice.CityDistanceDataService;
import dataservice.managedataservice.CostDataService;
import dataservice.managedataservice.OrganizationDataService;
import dataservice.managedataservice.PerWageDataService;
import dataservice.userdataservice.UserDataService;
import po.BasicSalaryPO;
import po.CityDistancePO;
import po.CostPO;
import po.OrganizationPO;
import po.PerWagePO;
import po.RepertoryPO;
import po.UserPO;
import presentation.financeui.LogDiaryPanel;
import presentation.managerui.BasicDataManagePanel;
import presentation.managerui.CheckBusinessPanel;
import presentation.managerui.CheckIncomePanel;
import presentation.managerui.CheckReceiptPanel;
import presentation.managerui.ManageFrame;
import presentation.managerui.OrganizationManagePanel;
import presentation.managerui.StaffManagePanel;
import vo.BasicSalaryVO;
import vo.CityDistanceVO;
import vo.CostVO;
import vo.OrganizationVO;
import vo.PerWageVO;
import vo.RepertoryVO;
import vo.UserVO;

public class ManageMainController {

	public static UserVO manageVO;
	
	public static BasicSalaryDataService basicSalaryData;
	public static CityDistanceDataService cityDistanceData;
	public static CostDataService costData;
	public static PerWageDataService perWageData;
	public static OrganizationDataService organizationData;
	public static UserDataService userData;
	
	private BasicSalaryController basicSalaryController;
	private CityDistanceController cityDistanceController;
	private CostController costController;
	private PerWageController perWageController;
	private OrganizationManageController organizationManageController;
	private BusinessStatementReceiptBLController businessStatementReceiptController;
	private CostIncomeReceiptBLController costIncomeReceiptController;
	private ReviewReceiptController reviewReceiptController;
	private UserManageController userManageController;
	private LogDiaryBLController logDiaryController;
	
	private ManageFrame manageFrame;
	public ManageMainController(String managerID){
		// RMI
		try{
			basicSalaryData = DataFactory.getBasicSalaryData();
			cityDistanceData = DataFactory.getCityDistanceData();
			costData = DataFactory.getCostData();
			perWageData = DataFactory.getPerWageData();
			organizationData = DataFactory.getOrganizationData();
			userData  = DataFactory.getUserData();
			
			manageVO = userPOToVO(userData.findUserByID(managerID));
		}catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
			
		//初始化8个controller
		basicSalaryController = new BasicSalaryController();
		cityDistanceController = new CityDistanceController();
		costController = new CostController();
		perWageController = new PerWageController();
		organizationManageController = new OrganizationManageController();
		businessStatementReceiptController = new BusinessStatementReceiptBLController();
		costIncomeReceiptController = new CostIncomeReceiptBLController();
		reviewReceiptController = new ReviewReceiptController();
		userManageController = new UserManageController();
		logDiaryController = new LogDiaryBLController();
		
		manageFrame = new ManageFrame(manageVO);
		manageFrame.addFuncLabel(new StaffManagePanel(manageFrame, userManageController, organizationManageController), "用户管理", ImageGetter.getImage("userManager.png").getImage());
		manageFrame.addFuncLabel(new OrganizationManagePanel(manageFrame, organizationManageController), "机构管理", ImageGetter.getImage("organizationManager.png").getImage());
		manageFrame.addFuncLabel(new CheckReceiptPanel(reviewReceiptController), "单据审批", ImageGetter.getImage("reviewReceipt.png").getImage());
		manageFrame.addFuncLabel(new CheckBusinessPanel(businessStatementReceiptController), "查看经营情况表", ImageGetter.getImage("businessStatement.png").getImage());
		manageFrame.addFuncLabel(new CheckIncomePanel(costIncomeReceiptController), "查看成本收益表", ImageGetter.getImage("costIncome.png").getImage());
		manageFrame.addFuncLabel(new BasicDataManagePanel(manageFrame, perWageController, basicSalaryController, cityDistanceController, costController),"基础数据设置", ImageGetter.getImage("basicDataManager.png").getImage());
		manageFrame.addFuncLabel(new LogDiaryPanel(logDiaryController), "系统日志",ImageGetter.getImage("viewInventory.png").getImage());
		manageFrame.showFrame();
		
	}
	
	
	//vo和po的转化,static
	public static UserPO userVOToPO(UserVO uservo){
		UserPO userpo = new UserPO(uservo.userName,uservo.userID,uservo.password,uservo.profession,
					uservo.organization,uservo.salaryPlan,uservo.authority,uservo.grades);
		return userpo;
	}
	
	public static UserVO userPOToVO(UserPO userpo){
		UserVO uservo = new UserVO(userpo.getName(),userpo.getID(),userpo.getPassword(),userpo.getProfession(),
				userpo.getOrganization(),userpo.getSalaryPlan(),userpo.getAuthority(),userpo.getGrades());
		return uservo;
	}
	
	public static BasicSalaryPO basicSalaryVOToPO(BasicSalaryVO basicSalaryvo){
		return new BasicSalaryPO(basicSalaryvo.profession,basicSalaryvo.basicSalary);
	}
	
	public static BasicSalaryVO basicSalaryPOToVO(BasicSalaryPO basicSalarypo){
		return new BasicSalaryVO(basicSalarypo.getProfession(),basicSalarypo.getBasicSalary());
	}
	
	public static CityDistancePO cityDistanceVOToPO(CityDistanceVO cityDistancevo){
		return new CityDistancePO(cityDistancevo.cityA,cityDistancevo.cityB, cityDistancevo.distance);
	}
	
	public static CityDistanceVO cityDistancePOToVO(CityDistancePO cityDistancepo){
		return new CityDistanceVO(cityDistancepo.getCityA(),cityDistancepo.getCityB(), cityDistancepo.getDistance());
	}
	
	public static CostPO costVOToPO(CostVO costvo){
		return new CostPO(costvo.category, costvo.cost);
	}
	
	public static CostVO costPOToVO(CostPO costpo){
		return new CostVO(costpo.getExpressType(), costpo.getCost());
	}
	
	public static PerWagePO perWageVOToPO(PerWageVO perWagevo){
		return new PerWagePO(perWagevo.profession,perWagevo.perWage);
	}
	
	public static PerWageVO perWagePOToVO(PerWagePO perWagepo){
		return new PerWageVO(perWagepo.getProfession(),perWagepo.getPerWage());
	}
	
	public static OrganizationVO organizationPOToVO(OrganizationPO organizationpo){
		return new OrganizationVO(organizationpo.getCategory(),organizationpo.getOrganizationID(),organizationpo.getName(),repertoryPOToVO(organizationpo.getRepertory()));
	}
	
	public static OrganizationPO organizationVOToPO(OrganizationVO organizationvo){
		return new OrganizationPO(organizationvo.category,organizationvo.organizationID,organizationvo.name,repertoryVOToPO(organizationvo.repertory));
	}
	
	public static RepertoryVO repertoryPOToVO(RepertoryPO repertorypo){
		return new RepertoryVO(repertorypo.getRepertoryID(),repertorypo.getOwnerID(),repertorypo.getMaxRow(),repertorypo.getMaxShelf(), repertorypo.getMaxDigit(),repertorypo.getWarningRatio(), repertorypo.getStockNumArray());
	}
	
	public static RepertoryPO repertoryVOToPO(RepertoryVO repertoryvo){
		return new RepertoryPO(repertoryvo.repertoryID,repertoryvo.ownerID,repertoryvo.maxRow,repertoryvo.maxShelf, repertoryvo.maxDigit,repertoryvo.warningRatio,repertoryvo.stockNum);
	}
	
	
}
