package businesslogic.managebl.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import businesslogic.datafactory.DataFactory;
import po.BasicSalaryPO;
import po.CityDistancePO;
import po.CostPO;
import po.OrganizationPO;
import po.PerWagePO;
import po.RepertoryPO;
import po.UserPO;
import presentation.managerui.BasicDataManagePanel;
import presentation.managerui.CheckIncomePanel;
import presentation.managerui.CheckOperatingPanel;
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
import dataservice.managedataservice.*;
import dataservice.userdataservice.*;

public class ManageMainController {

	public static UserVO manageVO;
	
	public static BasicSalaryDataService basicSalaryData;
	public static CityDistanceDataService cityDistanceData;
	public static CostDataService costData;
	public static PerWageDataService perWageData;
	public static OrganizationDataService organizationData;
	public static UserDataService userData;
	
	public static BasicSalaryController basicSalaryController;
	public static CityDistanceController cityDistanceController;
	public static CostController costController;
	public static PerWageController perWageController;
	public static OrganizationController organizationController;
	public static CheckBusinessStatementReceiptController checkBusinessStatementReceiptController;
	public static CheckCostIncomeReceiptController checkCostIncomeReceiptController;
	public static ReviewReceiptController reviewReceiptController;
	
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
			organizationController = new OrganizationController();
			checkBusinessStatementReceiptController = new CheckBusinessStatementReceiptController();
			checkCostIncomeReceiptController = new CheckCostIncomeReceiptController();
			reviewReceiptController = new ReviewReceiptController();
			
			try{
				manageVO = userPOToVO(userData.findUserByID(managerID));
				manageFrame = new ManageFrame(manageVO);
				manageFrame.addFuncLabel(new StaffManagePanel(manageFrame), "用户管理");
				manageFrame.addFuncLabel(new OrganizationManagePanel(manageFrame), "机构管理");
				manageFrame.addFuncLabel(new CheckReceiptPanel(), "单据审批");
				manageFrame.addFuncLabel(new CheckOperatingPanel(), "查看经营情况表");
				manageFrame.addFuncLabel(new CheckIncomePanel(),"查看成本收益表");
				manageFrame.addFuncLabel(new BasicDataManagePanel(manageFrame),"基础数据设置");
				
				manageFrame.showFrame();
			}catch(RemoteException exception){
				exception.printStackTrace();
			}
		
	}
	
	
	// vo和po的转化,static
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
