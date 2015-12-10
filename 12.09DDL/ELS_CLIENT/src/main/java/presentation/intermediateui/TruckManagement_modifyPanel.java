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

import vo.TruckVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import dataservice.managedataservice.CityDistanceDataService;

public class TruckManagement_modifyPanel extends JPanel {
	private IntermediateFrame frame;

	private JButton OKButton;

	private JLabel function;

	private JLabel truck_ID;
	private JLabel truck_destination;
	private JLabel truck_farePrice;

	private JTextField truck_ID_input;
	private JComboBox truck_destination_input;
	private JTextField truck_farePrice_input;

	private IntermediateMainController controller;
	private CityDistanceDataService cityDistanceData;
	private ArrayList<String> citys = new ArrayList<String>();

	private TruckVO truck;

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	public TruckManagement_modifyPanel(IntermediateMainController c,
			IntermediateFrame f, TruckVO p) {
		this.controller = c;
		this.frame = f;
		this.truck = p;

		try {
			cityDistanceData = DataFactory.getCityDistanceData();
			citys = cityDistanceData.getAllCitys();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		OKButton = new JButton("ok");

		function = new JLabel("修改汽车");

		truck_ID = new JLabel("汽车编号");
		truck_destination = new JLabel("汽车目的地");
		truck_farePrice = new JLabel("汽车租金（/个）");

		truck_ID_input = new JTextField(truck.ID);
		truck_ID_input.setEditable(false);

		truck_destination_input = new JComboBox();
		for (String city : citys)
			truck_destination_input.addItem(city);
		truck_destination_input.setSelectedItem(truck.destination);

		truck_farePrice_input = new JTextField("0.2");

		OKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getTruckManagerBL().modifyTruck(
							new TruckVO(truck_ID_input.getText(),
									(String) truck_destination_input
											.getSelectedItem()));
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				frame.toMainPanel();
			}
		});

		setLayout(null);

		add(OKButton);
		add(function);
		add(truck_ID);
		add(truck_destination);
		add(truck_farePrice);
		add(truck_ID_input);
		add(truck_destination_input);
		add(truck_farePrice_input);

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
		truck_ID.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 5.26077097505669 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		truck_destination.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 8.299319727891156 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		truck_farePrice.setBounds((int) (PANEL_WIDTH * 3.90625 / 25),
				(int) (PANEL_HEIGHT * 11.29251700680272 / 20),
				(int) (PANEL_WIDTH * 4.261363636363637 / 25),
				(int) (PANEL_HEIGHT * 1.9047619047619047 / 20));
		truck_ID_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 5.215419501133787 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
		truck_destination_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 8.299319727891156 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
		truck_farePrice_input.setBounds((int) (PANEL_WIDTH * 10.15625 / 25),
				(int) (PANEL_HEIGHT * 11.29251700680272 / 20),
				(int) (PANEL_WIDTH * 9.907670454545455 / 25),
				(int) (PANEL_HEIGHT * 1.8594104308390023 / 20));
	}
}
