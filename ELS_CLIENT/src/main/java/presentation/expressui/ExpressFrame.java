package presentation.expressui;

import java.util.ArrayList;

import presentation.commonui.UserFrame;
import vo.ExpressVO;

public class ExpressFrame extends UserFrame {

	public ExpressFrame(ExpressVO vo) {
		super();
		// addFuncLabel(new QueryPanel());
		// addFuncLabel(new ChargeMessageCollectionPanel());
		// addFuncLabel(new AddOrderPanel());
		// addFuncLabel(new FinishedOrderPanel());
		// 设置用户名和ID

		setMessage(vo.name, vo.ID);
	}

	// test
}
