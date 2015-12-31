package presentation.intermediateui;

import presentation.commonui.UserFrame;
import vo.UserVO;

public class IntermediateFrame extends UserFrame {
	public IntermediateFrame(UserVO intermediate) {
		super(intermediate.organization, intermediate.userID);
	}
}
