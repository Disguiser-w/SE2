package presentation.commonui;

import presentation.expressui.ChargeMessageCollectionPanel;

public class UserFrameTest extends UserFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserFrameTest() {

		super();

		// addFuncLabel(new OrganizationManagerPanel());
		// addFuncLabel(new BasicDataManagerPanel());
		// addFuncLabel(new CheckIncomePanel());
		// addFuncLabel(new CheckOperatingPanel());
//		 addFuncLabel(new StaffManagerPanel());
		// addFuncLabel(new CheckReceiptPanel());

		// addFuncLabel(new InitializeInformationPanel());
		// addFuncLabel(new EXwarehousePanel());
		 addFuncLabel(new ChargeMessageCollectionPanel());

		showFrame();

		// setFocusable(false);
	}

	public static void main(String[] args) {

		new UserFrameTest();

	}

}
