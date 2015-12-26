package presentation.userui;

import presentation.commonui.UserFrame;
import vo.UserVO;

public class AdminFrame extends UserFrame{
	
	private static final long serialVersionUID = 189L;

	public AdminFrame(UserVO vo){
		super(vo.userName, vo.userID);
	}
	
}
