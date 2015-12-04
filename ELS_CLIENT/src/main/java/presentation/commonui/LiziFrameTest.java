package presentation.commonui;

import presentation.repertoryui.WarehousingPanel;

public class LiziFrameTest extends UserFrame{

	private static final long serialVersionUID = 1L;

	public LiziFrameTest() {

		super();

		addFuncLabel(new WarehousingPanel());

		showFrame();

		// setFocusable(false);
	}

	public static void main(String[] args) {

		new LiziFrameTest();

	}
}
