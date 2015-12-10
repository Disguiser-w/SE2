package presentation.repertoryui;

import presentation.commonui.UserFrame;
import vo.UserVO;

public class RepertoryFrame extends UserFrame{

	public RepertoryFrame(UserVO vo) {
		super();
		setMessage(vo.getName(), vo.getID());
	}
}
