package presentation.repertoryui;

import presentation.commonui.UserFrame;
import vo.UserVO;

public class RepertoryFrame extends UserFrame{

	private static final long serialVersionUID = -8083482529253764377L;

	public UserVO uservo;
	
	public WarehousingMainPanel warehousingMainPanel;
	public EXwarehousePanel exwarehousePanel;
	
	
	
	public RepertoryFrame(UserVO vo) {
		super(vo.userName, vo.userID);
		
		this.uservo = vo;
	}
	
}

