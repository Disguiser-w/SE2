package presentation.userui;

import presentation.commonui.UserFrame;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;

public class AdminFrame extends UserFrame{
	
	private static final long serialVersionUID = 189L;

	public AdminFrame(UserVO vo){
		super(vo.userName, vo.userID);
	}
	
	public static void main(String[] args){
		new AdminFrame(new UserVO("魏彦淑", "admin", "admin", ProfessionType.administrator,
				"", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.administrator, 0));
	}
}
