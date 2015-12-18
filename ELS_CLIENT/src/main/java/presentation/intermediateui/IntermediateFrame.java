package presentation.intermediateui;

import presentation.commonui.UserFrame;
import vo.IntermediateVO;

public class IntermediateFrame extends UserFrame {
	public IntermediateFrame(IntermediateVO intermediate) {
		super(intermediate.name, intermediate.ID);
	}
}
