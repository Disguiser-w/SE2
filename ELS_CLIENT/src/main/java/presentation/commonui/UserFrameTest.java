package presentation.commonui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import presentation.businessui.OrderDistributePanel;
import presentation.businessui.OrderReceiveManagerPanel;
import presentation.expressui.AddOrderPanel;
import presentation.expressui.ChargeMessageCollectionPanel;
import presentation.expressui.QueryPanel;

public class UserFrameTest extends UserFrame {

	public UserFrameTest() {

		super();
		addFuncLabel(new OrderReceiveManagerPanel());

		addFuncLabel(new OrderDistributePanel());

		addFuncLabel(new ChargeMessageCollectionPanel());

		addFuncLabel(new AddOrderPanel());

		addFuncLabel(new QueryPanel());

		// addFuncLabel(new ChargeCollectionPanel());

		showFrame();
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println("key presssss");
			}
		});
		setFocusable(false);
	}

	public static void main(String[] args) {

		new UserFrameTest();

	}

}
