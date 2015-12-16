package presentation.managerui;

import presentation.commonui.UserFrame;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;

public class ManageFrame extends UserFrame{

private static final long serialVersionUID = 189L;
	
	public ManageFrame(UserVO vo){
		super();
		
		setMessage(vo.userName, vo.userID);
		
		addFuncLabel(new StaffManagePanel(this));
		addFuncLabel(new OrganizationManagePanel(this));
		addFuncLabel(new CheckReceiptPanel());
		addFuncLabel(new CheckOperatingPanel());
		addFuncLabel(new CheckIncomePanel());
		addFuncLabel(new BasicDataManagePanel(this));
		
		showFrame();
	}
	
	public static void main(String[] args){
		ManageFrame manageFrame = new ManageFrame(new UserVO("魏彦淑","JL-00001","123456", ProfessionType.manager, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.highest, 0));
	
		manageFrame.setVisible(true);
	}
	
}
