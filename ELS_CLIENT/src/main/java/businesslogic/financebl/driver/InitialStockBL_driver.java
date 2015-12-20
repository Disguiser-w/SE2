package businesslogic.financebl.driver;

import java.awt.Container;
import java.util.ArrayList;

import type.AuthorityType;
import type.OrganizationType;
import type.ProfessionType;
import type.SalaryPlanType;
import type.Sexuality;
import vo.AccountVO;
import vo.DriverVO;
import vo.InitInfoVO;
import vo.OrganizationVO;
import vo.RepertoryVO;
import vo.UserVO;
import vo.VehicleVO;
import businesslogic.financebl.controller.InitialStockBLController;

public class InitialStockBL_driver {
	public static void main(String[] args){
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
		
		InitInfoVO vo = new InitInfoVO("20151221", userPOs, organizationPOs, vehiclePOs, repertoryPOs, accountPOs);
		
		initController.initInfo(vo, "20151221");
		
		ArrayList<InitInfoVO> ivos=initController.getAllInitInfo();
		System.out.println(ivos.get(0).time);
		
		InitInfoVO ivo=initController.getInitInfo("20151220");
		System.out.println(ivo.time);
		
		ArrayList<VehicleVO> vvos=initController.getVehicleInfo();
		System.out.println(vvos.get(0).destinationCity);
		
	}

}
