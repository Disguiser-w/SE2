package presentation.managerui;

import presentation.commonui.UserFrame;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;

public class ManageFrame extends UserFrame{

private static final long serialVersionUID = 189L;
	
	public ManageFrame(UserVO vo){
		super(vo.userName, vo.userID);
		
		addFuncLabel(new StaffManagePanel(this), "用户管理");
		addFuncLabel(new OrganizationManagePanel(this), "机构管理");
		addFuncLabel(new CheckReceiptPanel(), "单据审批");
		addFuncLabel(new CheckOperatingPanel(), "查看经营情况表");
		addFuncLabel(new CheckIncomePanel(),"查看成本收益表");
		addFuncLabel(new BasicDataManagePanel(this),"基础数据设置");
		
		showFrame();
	}
	
	public static void main(String[] args){
		ManageFrame manageFrame = new ManageFrame(new UserVO("魏彦淑","JL-00001","123456", ProfessionType.manager, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0));
	
		manageFrame.setVisible(true);
	}
	
}
