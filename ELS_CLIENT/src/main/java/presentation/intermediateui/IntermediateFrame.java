package presentation.intermediateui;

import presentation.commonui.UserFrame;
import vo.UserVO;

@SuppressWarnings("serial")
public class IntermediateFrame extends UserFrame {
	public IntermediateFrame(UserVO intermediate) {
		super(intermediate.userName, intermediate.userID);
	}
}
