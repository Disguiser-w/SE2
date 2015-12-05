package presentation.commonui;

import presentation.repertoryui.WarehousingPanel;
import presentation.userui.UserPanel_new;
import presentation.userui.userPanel_main;
import presentation.expressui.ChargeMessageCollectionPanel;

public class LiziFrameTest extends UserFrame{

	private static final long serialVersionUID = 1L;

	public LiziFrameTest() {

		super();

		//addFuncLabel(new WarehousingPanel());
		//addFuncLabel(new UserPanel_new());
		addFuncLabel(new userPanel_main());
		//addFuncLabel(new ChargeMessageCollectionPanel());
		showFrame();

		// setFocusable(false);
	}

	public static void main(String[] args) {

		new LiziFrameTest();

	}
}
