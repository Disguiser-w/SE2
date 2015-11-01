package presentation.commonui;

import presentation.businessui.OrderDistributePanel;
import presentation.expressui.AddOrderPanel;
import presentation.expressui.ChargeMessageCollectionPanel;
import presentation.expressui.QueryPanel;

public class UserFrameTest extends UserFrame {

	public UserFrameTest() {

		super();

		addFuncLabel(new OrderDistributePanel());

		addFuncLabel(new ChargeMessageCollectionPanel());

		addFuncLabel(new AddOrderPanel());

		addFuncLabel(new QueryPanel());

		// addFuncLabel(new ChargeCollectionPanel());

		showFrame();
	}

	public static void main(String[] args) {

		new UserFrameTest();

	}

}
