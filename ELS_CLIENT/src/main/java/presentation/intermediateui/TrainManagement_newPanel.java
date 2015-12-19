package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.commonui.UserFrame;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import dataservice.managedataservice.CityDistanceDataService;

public class TrainManagement_newPanel extends JPanel {
	private UserFrame frame;

	private JButton OKButton;

	private JLabel function;

	private JLabel train_ID;
	private JLabel train_destination;
	private JLabel train_farePrice;

	private JTextField train_ID_input;
	private JComboBox train_destination_input;
	private JTextField train_farePrice_input;

	private IntermediateMainController controller;
	private CityDistanceDataService cityDistanceData;
	private ArrayList<String> citys = new ArrayList<String>();

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private int trainNum;
	private String intermediateCenterID;
	private String trainID;

	public TrainManagement_newPanel(IntermediateMainController c, UserFrame f) {
		this.controller = c;
		this.frame = f;

		try {
			cityDistanceData = DataFactory.getCityDistanceData();
			citys = cityDistanceData.getAllCitys();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (controller.getPlaneList().size() == 0)
			trainNum = 1;
		else
			trainNum = Integer
					.parseInt(controller.getTrainList().get(
							controller.getTrainList().size() - 1).ID.substring(
							controller.getTrainList().get(
									controller.getTrainList().size() - 1).ID
									.length() - 3,
							controller.getTrainList().get(
									controller.getTrainList().size() - 1).ID
									.length())) + 1;
		intermediateCenterID = controller.getIntermediateCentre().organizationID;

		if (trainNum < 10)
			trainID = intermediateCenterID + "00" + trainNum;
		else if (trainNum < 100 && trainNum >= 10)
			trainID = intermediateCenterID + "0" + trainNum;
		else
			trainID = intermediateCenterID + trainNum;

		OKButton = new JButton("ok");

		function = new JLabel("新增火车");

		train_ID = new JLabel("火车编号");
		train_destination = new JLabel("火车目的地");
		train_farePrice = new JLabel("火车租金（/个）");

		train_ID_input = new JTextField(trainID);

		train_destination_input = new JComboBox();
		for (String city : citys)
			train_destination_input.addItem(city);

		train_farePrice_input = new JTextField("0.2");

		OKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.getTrainManagerBL().addTrain(
						train_ID_input.getText(),
						train_destination_input.getSelectedItem().toString());
				frame.toMainPanel();
			}
		});

		setLayout(null);

		add(OKButton);
		add(function);
		add(train_ID);
		add(train_destination);
		add(train_farePrice);
		add(train_ID_input);
		add(train_destination_input);
		add(train_farePrice_input);

		setCmpLocation();
	}

	private void setCmpLocation() {
		// TODO 自动生成的方法存根
		OKButton.setBounds((int) (PANEL_WIDTH * 21.37784090909091 / 25),
				(int) (PANEL_HEIGHT * 1.8140589569160999 / 20),
				(int) (PANEL_WIDTH * 1.7400568181818181 / 25),
				(int) (PANEL_HEIGHT * 1.4965986394557824 / 20));
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		train_ID.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 5.26077097505669 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		train_destination.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 8.299319727891156 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		train_farePrice.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 11.29251700680272 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		train_ID_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 5.215419501133787 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
		train_destination_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 8.299319727891156 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
		train_farePrice_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 11.29251700680272 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
	}
}
