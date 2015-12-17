package presentation.userui;

import presentation.commonui.UserFrame;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;

public class AdminFrame extends UserFrame{
	
	private static final long serialVersionUID = 189L;

	public AdminFrame(UserVO vo){
		super();
		
		setMessage(vo.userName, vo.userID);
		
		addFuncLabel(new UserMainPanel(this));
		
		showFrame();
	}
	
	public static void main(String[] args){
		AdminFrame adminFrame = new AdminFrame(new UserVO("魏彦淑","admin","admin", ProfessionType.administrator, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.administrator, 0));
		adminFrame.setVisible(true);
	}
	
}
