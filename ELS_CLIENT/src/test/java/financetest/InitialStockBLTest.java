package financetest;

import java.util.ArrayList;

import po.AccountPO;
import po.DriverPO;
import po.InitInfoPO;
import po.OrganizationPO;
import po.RepertoryPO;
import po.UserPO;
import po.VehiclePO;
import businesslogic.financebl.controller.InitialStockBLController;
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
import junit.framework.TestCase;

public class InitialStockBLTest extends TestCase{
	InitInfoVO vo1,vo2;
	InitialStockBLController initialStockBL;
	
	public void setUp(){
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
		
	   initialStockBL = new InitialStockBLController();
	}
	
	public void test1_1(){
		ArrayList<InitInfoVO> vos = initialStockBL.getAllInitInfo();
		assertEquals(4, vos.size());
	}
	
	public void test1_2(){
		InitInfoVO vo = initialStockBL.getInitInfo("20151220");
		assertEquals(4, vo.accoutVOs.size());
	}
	
	public void tset1_3(){
		
	}
}

