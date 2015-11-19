package presentation.commonui;

import presentation.expressui.AddOrderPanel;
import presentation.expressui.ChargeMessageCollectionPanel;
import presentation.expressui.FinishedOrderPanel;
import presentation.expressui.QueryPanel;

public class UserFrameTest extends UserFrame {

	public UserFrameTest() {

		super();

//		addFuncLabel(new AddOrderPanel());
//		addFuncLabel(new ChargeMessageCollectionPanel());
//		addFuncLabel(new FinishedOrderPanel());
		addFuncLabel(new QueryPanel());

		showFrame();

		setFocusable(false);
	}

	public static void main(String[] args) {

		new UserFrameTest();

	}

}
