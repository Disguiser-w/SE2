package presentation.businessui;

import presentation.commonui.UserFrame;
import vo.BusinessVO;

public class BusinessFrame extends UserFrame {
	public BusinessFrame(BusinessVO vo) {
		super(vo.userName, vo.userID);
	}
}
