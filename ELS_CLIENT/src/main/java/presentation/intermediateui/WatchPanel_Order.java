package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.UserFrame;
import businesslogic.intermediatebl.controller.IntermediateMainController;

@SuppressWarnings("serial")
public class WatchPanel_Order extends Transfering_newPanel {
	private MyLabel confirmButton;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	public WatchPanel_Order(IntermediateMainController c, UserFrame f, MyTable m) {
		super(c, f, m);
		remove(function);
		remove(OKButton);
		setBaseInfo();
	}

	private void setBaseInfo() {
		order_ID_input.setEditable(false);

		confirmButton = new MyLabel("ok");
		confirmButton.setBounds((int) (PANEL_WIDTH * 21.37784090909091 / 25),
				(int) (PANEL_HEIGHT * 1.8140589569160999 / 20),
				(int) (PANEL_WIDTH * 1.7400568181818181 / 25),
				(int) (PANEL_HEIGHT * 1.4965986394557824 / 20));
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		add(confirmButton);

		confirmButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.toMainPanel();
			}
		});
	}
}
