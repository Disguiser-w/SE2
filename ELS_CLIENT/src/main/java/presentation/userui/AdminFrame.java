package presentation.userui;

import presentation.commonui.UserFrame;
import presentation.userui.UserMainPanel;
import type.AuthorityType;
import type.ProfessionType;
import type.SalaryPlanType;
import vo.UserVO;

public class AdminFrame extends UserFrame{
	
	private static final long serialVersionUID = 189L;
	UserVO vo;
	public AdminFrame(UserVO vo){
		super();
		this.vo = vo;
		setMessage(vo.getName(), vo.getID());
	}
	
	public static void main(String[] args){
		AdminFrame adminFrame = new AdminFrame(new UserVO("汪文藻","GLY-00001","123456", ProfessionType.administrator, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.administrator, 0));
		adminFrame.addFuncLabel(new UserMainPanel(adminFrame));
		adminFrame.showFrame();
	}
	
}
