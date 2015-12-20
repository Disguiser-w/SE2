package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataservice.managedataservice.CityDistanceDataService;
import presentation.commonui.UserFrame;
import vo.PlaneVO;
import vo.TrainVO;
import vo.TruckVO;
import businesslogic.intermediatebl.controller.IntermediateMainController;

public class WatchPanel extends Management_modifyPanel {
	private JButton confirmButton;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	public WatchPanel(IntermediateMainController c, UserFrame f, PlaneVO p) {
		super(c, f, p);
		remove(function);
		function = new JLabel("飞机信息");
		add(function);
		setBaseInfo();
	}

	public WatchPanel(IntermediateMainController c, UserFrame f, TrainVO t) {
		super(c, f, t);
		remove(function);
		function = new JLabel("火车信息");
		add(function);
		setBaseInfo();
	}

	public WatchPanel(IntermediateMainController c, UserFrame f, TruckVO t) {
		super(c, f, t);
		remove(function);
		function = new JLabel("汽车信息");
		add(function);
		setBaseInfo();
	}

	private void setBaseInfo() {
		destination_input.setEnabled(false);
		farePrice_input.setEditable(false);

		confirmButton = new JButton("ok");
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
