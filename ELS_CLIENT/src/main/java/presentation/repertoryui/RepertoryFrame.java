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
		super(vo.userName, vo.userID);
		
		this.uservo = vo;
		
		addFuncLabel(new InitializeInformationPanel(uservo), "库存信息初始化");
		addFuncLabel(new WarehousingPanel(uservo), "入库");
		addFuncLabel(new EXwarehousePanel(uservo), "出库");
		addFuncLabel(new ViewInventoryPanel(uservo),"库存盘点");
		addFuncLabel(new InventoryVerificationPanel(uservo), "库存查看");
		showFrame();
	}
	
	public static void main(String[] args){
		RepertoryFrame repertoryFrame = new RepertoryFrame(new UserVO("王卉","CK-00001","123456", ProfessionType.stockman, 
				"总部", SalaryPlanType.basicStaffSalaryPlan, AuthorityType.lowest, 0));
		repertoryFrame.setVisible(true);
	}
}
