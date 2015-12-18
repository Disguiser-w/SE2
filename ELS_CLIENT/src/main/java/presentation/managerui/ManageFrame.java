package presentation.managerui;

import presentation.commonui.UserFrame;
import vo.UserVO;

public class ManageFrame extends UserFrame{

private static final long serialVersionUID = 189L;
	
	public ManageFrame(UserVO vo){
		super(vo.userName, vo.userID);
	}
	
}
