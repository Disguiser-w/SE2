package presentation.businessui;

import presentation.commonui.UserFrame;
import vo.BusinessVO;

public class BusinessFrame extends UserFrame {
	public BusinessFrame(BusinessVO vo) {

		super(vo.name, vo.ID);
		// addFuncLabel(new QueryPanel());
		// addFuncLabel(new ChargeMessageCollectionPanel());
		// addFuncLabel(new AddOrderPanel());
		// addFuncLabel(new FinishedOrderPanel());
		// 设置用户名和ID

	}
}
