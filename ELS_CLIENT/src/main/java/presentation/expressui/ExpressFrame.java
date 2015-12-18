package presentation.expressui;

import presentation.commonui.UserFrame;
import vo.ExpressVO;

public class ExpressFrame extends UserFrame {

	public ExpressFrame(ExpressVO vo) {
		super(vo.name, vo.ID);
	}
}
