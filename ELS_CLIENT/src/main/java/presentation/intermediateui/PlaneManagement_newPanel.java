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

import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import dataservice.managedataservice.CityDistanceDataService;

public class PlaneManagement_newPanel extends JPanel {
	private IntermediateFrame frame;

	private JButton OKButton;

	private JLabel function;

	private JLabel plane_ID;
	private JLabel plane_destination;
	private JLabel plane_farePrice;

	private JTextField plane_ID_input;
	private JComboBox plane_destination_input;
	private JTextField plane_farePrice_input;

	private IntermediateMainController controller;
	private CityDistanceDataService cityDistanceData;
	private ArrayList<String> citys = new ArrayList<String>();

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private int planeNum;
	private String intermediateCenterID;
	private String planeID;

	public PlaneManagement_newPanel(IntermediateMainController c,
			IntermediateFrame f) {
		this.controller = c;
		this.frame = f;

		try {
			cityDistanceData = DataFactory.getCityDistanceData();
			citys = cityDistanceData.getAllCitys();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		planeNum = Integer.parseInt(controller.getPlaneList().get(
				controller.getPlaneList().size() - 1).ID.substring(
				controller.getPlaneList().get(
						controller.getPlaneList().size() - 1).ID.length() - 3,
				controller.getPlaneList().get(
						controller.getPlaneList().size() - 1).ID.length())) + 1;
		intermediateCenterID = controller.getIntermediateCentre().organizationID;

		if (planeNum < 10)
			planeID = intermediateCenterID + "00" + planeNum;
		else if (planeNum < 100 && planeNum >= 10)
			planeID = intermediateCenterID + "0" + planeNum;
		else
			planeID = intermediateCenterID + planeNum;

		OKButton = new JButton("ok");

		function = new JLabel("新增飞机");

		plane_ID = new JLabel("飞机编号");
		plane_destination = new JLabel("飞机目的地");
		plane_farePrice = new JLabel("飞机租金（/个）");

		plane_ID_input = new JTextField(planeID);

		plane_destination_input = new JComboBox();
		for (String city : citys)
			plane_destination_input.addItem(city);

		plane_farePrice_input = new JTextField("0.2");

		OKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getPlaneManagerBL().addPlane(
							plane_ID_input.getText(),
							plane_destination_input.getSelectedItem()
									.toString());
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				frame.toMainPanel();
			}
		});

		setLayout(null);

		add(OKButton);
		add(function);
		add(plane_ID);
		add(plane_destination);
		add(plane_farePrice);
		add(plane_ID_input);
		add(plane_destination_input);
		add(plane_farePrice_input);

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
		plane_ID.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 5.26077097505669 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		plane_destination.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 8.299319727891156 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		plane_farePrice.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 11.29251700680272 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		plane_ID_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 5.215419501133787 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
		plane_destination_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 8.299319727891156 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
		plane_farePrice_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 11.29251700680272 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
	}
}
