package presentation.userui;

import presentation.commonui.UserFrame;
//import type.AuthorityType;
//import type.ProfessionType;
//import type.SalaryPlanType;
import vo.UserVO;

public class AdminFrame extends UserFrame{
	
	private static final long serialVersionUID = 189L;

	public UserVO uservo;
	
	public AdminFrame(UserVO vo){
		super();
		
		this.uservo = vo;
		
		setMessage(vo.getName(), vo.getID());
	}
	
	/*public static void main(String[] args){
		AdminFrame adminFrame = new AdminFrame(new UserVO("汪文藻","GLY-00001","123456", ProfessionType.administrator, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.administrator, 0));
	}*/
	
}
