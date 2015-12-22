package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;
import vo.TruckVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import dataservice.managedataservice.CityDistanceDataService;

public class Management_newPanel extends OperationPanel {
	private UserFrame frame;

	private MyTable messageTable;

	private MyLabel OKButton;

	private JLabel function;

	private JLabel ID;
	private JLabel destination;
	private JLabel farePrice;

	private MyTextField ID_input;
	private JComboBox destination_input;
	private MyTextField farePrice_input;

	private IntermediateMainController controller;
	private CityDistanceDataService cityDistanceData;
	private ArrayList<String> citys = new ArrayList<String>();

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private int Num;
	private String intermediateCenterID;
	private String id;

	private String type;

	public Management_newPanel(IntermediateMainController c, UserFrame f,
			MyTable m, String s) {
		this.controller = c;
		this.frame = f;
		this.messageTable = m;
		this.type = s;

		switch (type) {
		case "plane":
			if (controller.getPlaneList().size() == 0)
				Num = 1;
			else
				Num = Integer.parseInt(controller.getPlaneList().get(
						controller.getPlaneList().size() - 1).ID.substring(
						controller.getPlaneList().get(
								controller.getPlaneList().size() - 1).ID
								.length() - 3,
						controller.getPlaneList().get(
								controller.getPlaneList().size() - 1).ID
								.length())) + 1;

			function = new JLabel("新增汽车");
			ID = new JLabel("汽车编号");
			destination = new JLabel("汽车目的地");
			farePrice = new JLabel("汽车租金（/个）");
			OKButton = new MyLabel("ok");

			OKButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					try {
						controller.getPlaneManagerBL().addPlane(
								ID_input.getText(),
								destination_input.getSelectedItem().toString());
						messageTable.addRow(new String[] { ID_input.getText(),
								controller.getIntermediateCentre().name,
								destination_input.getSelectedItem().toString(),
								farePrice_input.getText(), "工作中" });
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					frame.toMainPanel();
				}
			});
			break;
		case "train":
			if (controller.getTrainList().size() == 0)
				Num = 1;
			else
				Num = Integer.parseInt(controller.getTrainList().get(
						controller.getTrainList().size() - 1).ID.substring(
						controller.getTrainList().get(
								controller.getTrainList().size() - 1).ID
								.length() - 3,
						controller.getTrainList().get(
								controller.getTrainList().size() - 1).ID
								.length())) + 1;

			function = new JLabel("新增火车");
			ID = new JLabel("火车编号");
			destination = new JLabel("火车目的地");
			farePrice = new JLabel("火车租金（/个）");
			OKButton = new MyLabel("ok");

			OKButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					try {
						controller.getTrainManagerBL().addTrain(
								ID_input.getText(),
								destination_input.getSelectedItem().toString());
						messageTable.addRow(new String[] { ID_input.getText(),
								controller.getIntermediateCentre().name,
								destination_input.getSelectedItem().toString(),
								farePrice_input.getText(), "工作中" });
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					frame.toMainPanel();
				}
			});
			break;
		case "truck":
			if (controller.getTruckList().size() == 0)
				Num = 1;
			else
				Num = Integer.parseInt(controller.getTruckList().get(
						controller.getTruckList().size() - 1).ID.substring(
						controller.getTruckList().get(
								controller.getTruckList().size() - 1).ID
								.length() - 3,
						controller.getTruckList().get(
								controller.getTruckList().size() - 1).ID
								.length())) + 1;

			function = new JLabel("新增汽车");
			ID = new JLabel("汽车编号");
			destination = new JLabel("汽车目的地");
			farePrice = new JLabel("汽车租金（/个）");
			OKButton = new MyLabel("ok");

			OKButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					try {
						controller.getTruckManagerBL().addTruck(
								ID_input.getText(),
								destination_input.getSelectedItem().toString());
						messageTable.addRow(new String[] { ID_input.getText(),
								controller.getIntermediateCentre().name,
								destination_input.getSelectedItem().toString(),
								farePrice_input.getText(), "工作中" });
					} catch (Exception e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					frame.toMainPanel();
				}
			});
			break;
		}

		intermediateCenterID = controller.getIntermediateCentre().organizationID;
		farePrice_input = new MyTextField();
		farePrice_input.setText("0.2");

		try {
			cityDistanceData = DataFactory.getCityDistanceData();
			citys = cityDistanceData.getAllCitys();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		if (Num < 10)
			id = intermediateCenterID + "00" + Num;
		else if (Num < 100 && Num >= 10)
			id = intermediateCenterID + "0" + Num;
		else
			id = intermediateCenterID + Num;

		ID_input = new MyTextField();
		ID_input.setText(id);

		destination_input = new JComboBox();
		for (String city : citys)
			destination_input.addItem(city);

		setCmpLocation();
	}

	private void setCmpLocation() {
		// TODO 自动生成的方法存根
		setLayout(null);

		add(OKButton);
		add(function);
		add(ID);
		add(ID_input);
		add(destination);
		add(destination_input);
		add(farePrice);
		add(farePrice_input);

		OKButton.setBounds((int) (PANEL_WIDTH * 21.37784090909091 / 25),
				(int) (PANEL_HEIGHT * 1.8140589569160999 / 20),
				(int) (PANEL_WIDTH * 1.7400568181818181 / 25),
				(int) (PANEL_HEIGHT * 1.4965986394557824 / 20));
		function.setBounds(PANEL_WIDTH / 36, PANEL_HEIGHT / 24,
				PANEL_WIDTH * 4 / 18, PANEL_HEIGHT / 12);
		ID.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 5.26077097505669 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		destination.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 8.299319727891156 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		farePrice.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 11.29251700680272 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		ID_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 5.215419501133787 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
		destination_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 8.299319727891156 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
		farePrice_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 11.29251700680272 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
	}
}
