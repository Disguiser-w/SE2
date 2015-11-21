package presentation.expressui;

import presentation.commonui.UserFrame;

public class ExpressFrame extends UserFrame {

	public ExpressFrame(String name, String ID) {
		super();
		addFuncLabel(new QueryPanel());
		addFuncLabel(new ChargeMessageCollectionPanel());
		addFuncLabel(new AddOrderPanel());

		addFuncLabel(new FinishedOrderPanel());
		// 设置用户名密码
		setMessage(name, ID);
		showFrame();
	}

	// test
	public static void main(String[] args) {
		new ExpressFrame("狗盛", "yyy-gs123");
	}
}
