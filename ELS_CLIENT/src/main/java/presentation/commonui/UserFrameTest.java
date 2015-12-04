package presentation.commonui;

import presentation.managerui.BasicDataManagerPanel;
import presentation.managerui.CheckIncomePanel;
import presentation.managerui.CheckOperatingPanel;
import presentation.managerui.CheckReceiptPanel;
import presentation.managerui.OrganizationManagerPanel;
import presentation.managerui.StaffManagerPanel;
import presentation.repertoryui.EXwarehousePanel;
import presentation.repertoryui.InitializeInformationPanel;
import presentation.repertoryui.WarehousingPanel;

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
		addFuncLabel(new StaffManagerPanel());
		addFuncLabel(new CheckReceiptPanel());

		 addFuncLabel(new InitializeInformationPanel());
		 addFuncLabel(new EXwarehousePanel());
		 addFuncLabel(new WarehousingPanel());
		showFrame();

		// setFocusable(false);
	}

	public static void main(String[] args) {

		new UserFrameTest();

	}

}
