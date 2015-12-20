package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.UserFrame;
import vo.PlaneVO;
import vo.TrainVO;
import vo.TruckVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

public class WatchPanel extends Management_modifyPanel {
	private MyLabel confirmButton;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	public WatchPanel(IntermediateMainController c, UserFrame f, MyTable m,
			PlaneVO p) {
		super(c, f, m, p);
		remove(function);
		function = new JLabel("飞机信息");
		add(function);
		setBaseInfo();
	}

	public WatchPanel(IntermediateMainController c, UserFrame f, MyTable m,
			TrainVO t) {
		super(c, f, m, t);
		remove(function);
		function = new JLabel("火车信息");
		add(function);
		setBaseInfo();
	}

	public WatchPanel(IntermediateMainController c, UserFrame f, MyTable m,
			TruckVO t) {
		super(c, f, m, t);
		remove(function);
		function = new JLabel("汽车信息");
		add(function);
		setBaseInfo();
	}

	private void setBaseInfo() {
		destination_input.setEnabled(false);
		farePrice_input.setEditable(false);

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
