package presentation.repertoryui;

import presentation.commonui.UserFrame;
import vo.UserVO;
import type.ProfessionType;
import type.SalaryPlanType;
import type.AuthorityType;

public class RepertoryFrame extends UserFrame{

	private static final long serialVersionUID = -8083482529253764377L;

	public UserVO uservo;
	
	public RepertoryFrame(UserVO vo) {
		super();
		
		this.uservo = vo;
		
		setMessage(vo.userName, vo.userID);
		
		addFuncLabel(new InitializeInformationPanel(uservo));
		addFuncLabel(new WarehousingPanel(uservo));
		addFuncLabel(new EXwarehousePanel(uservo));
		addFuncLabel(new ViewInventoryPanel(uservo));
		addFuncLabel(new InventoryVerificationPanel(uservo));
		showFrame();
	}
	
	public static void main(String[] args){
		RepertoryFrame repertoryFrame = new RepertoryFrame(new UserVO("王卉","CK-00001","123456", ProfessionType.stockman, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.lowest, 0));
		repertoryFrame.setVisible(true);
	}
}
