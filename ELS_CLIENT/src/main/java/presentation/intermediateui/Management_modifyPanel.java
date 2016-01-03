package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;
import vo.PlaneVO;
import vo.TrainVO;
import vo.TruckVO;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import dataservice.managedataservice.CityDistanceDataService;

@SuppressWarnings("serial")
public class Management_modifyPanel extends OperationPanel {
	public UserFrame frame;

	protected MyLabel OKButton;

	protected MyTable messageTable;

	protected JLabel function;

	protected JLabel ID;
	protected JLabel destination;
	protected JLabel farePrice;

	protected MyTextField ID_input;
	@SuppressWarnings("rawtypes")
	protected MyComboBox destination_input;
	protected MyTextField farePrice_input;

	protected IntermediateMainController controller;
	protected CityDistanceDataService cityDistanceData;
	protected ArrayList<String> citys = new ArrayList<String>();

	protected PlaneVO plane;
	protected TrainVO train;
	protected TruckVO truck;

	protected int PANEL_WIDTH = 720;
	protected int PANEL_HEIGHT = 480;

	protected int selectedNum;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Management_modifyPanel(IntermediateMainController c, UserFrame f,
			MyTable m, PlaneVO p, int n) {
		this.controller = c;
		this.frame = f;
		this.messageTable = m;
		this.plane = p;
		this.selectedNum = n;

		try {
			cityDistanceData = DataFactory.getCityDistanceData();
			citys = cityDistanceData.getAllCitys();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		OKButton = new MyLabel("ok");

		function = new JLabel("修改飞机");

		ID = new JLabel("飞机编号");
		destination = new JLabel("飞机目的地");
		farePrice = new JLabel("飞机租金（/个）");

		ID_input = new MyTextField();
		ID_input.setText(plane.ID);
		ID_input.setEditable(false);

		destination_input = new MyComboBox();
		for (String city : citys)
			destination_input.addItem(city);
		destination_input.setSelectedItem(plane.destination);

		farePrice_input = new MyTextField();
		farePrice_input.setText("0.2");

		OKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getPlaneManagerBL().modifyPlane(
							new PlaneVO(ID_input.getText(), destination_input
									.getSelectedItem().toString()));
					messageTable.setRowValueAt(
							new String[] {
									ID_input.getText(),
									controller.getIntermediateCentre().name,
									destination_input.getSelectedItem()
											.toString(),
									farePrice_input.getText(), "工作中" },
							selectedNum);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				frame.toMainPanel();
			}
		});

		setCmpLocation();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Management_modifyPanel(IntermediateMainController c, UserFrame f,
			MyTable m, TrainVO t, int n) {
		this.controller = c;
		this.frame = f;
		this.messageTable = m;
		this.train = t;
		this.selectedNum = n;

		try {
			cityDistanceData = DataFactory.getCityDistanceData();
			citys = cityDistanceData.getAllCitys();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		OKButton = new MyLabel("ok");

		function = new JLabel("修改火车");

		ID = new JLabel("火车编号");
		destination = new JLabel("火车目的地");
		farePrice = new JLabel("火车租金（/个）");

		ID_input = new MyTextField();
		ID_input.setText(train.ID);
		ID_input.setEditable(false);

		destination_input = new MyComboBox();
		for (String city : citys)
			destination_input.addItem(city);
		destination_input.setSelectedItem(train.destination);

		farePrice_input = new MyTextField();
		farePrice_input.setText("0.002");

		OKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getTrainManagerBL().modifyTrain(
							new TrainVO(ID_input.getText(),
									(String) destination_input
											.getSelectedItem()));
					System.out.println(selectedNum);
					messageTable.setRowValueAt(
							new String[] {
									ID_input.getText(),
									controller.getIntermediateCentre().name,
									destination_input.getSelectedItem()
											.toString(),
									farePrice_input.getText(), "工作中" },
							selectedNum);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				frame.toMainPanel();
			}
		});

		setCmpLocation();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Management_modifyPanel(IntermediateMainController c, UserFrame f,
			MyTable m, TruckVO p, int n) {
		this.controller = c;
		this.messageTable = m;
		this.frame = f;
		this.truck = p;
		this.selectedNum = n;

		try {
			cityDistanceData = DataFactory.getCityDistanceData();
			citys = cityDistanceData.getAllCitys();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		OKButton = new MyLabel("ok");

		function = new JLabel("修改汽车");

		ID = new JLabel("汽车编号");
		destination = new JLabel("汽车目的地");
		farePrice = new JLabel("汽车租金（/个）");

		ID_input = new MyTextField();
		ID_input.setText(truck.ID);
		ID_input.setEditable(false);

		destination_input = new MyComboBox();
		for (String city : citys)
			destination_input.addItem(city);
		destination_input.setSelectedItem(truck.destination);

		farePrice_input = new MyTextField();
		farePrice_input.setText("0.02");

		OKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					controller.getTruckManagerBL().modifyTruck(
							new TruckVO(ID_input.getText(),
									(String) destination_input
											.getSelectedItem()));
					messageTable.setRowValueAt(
							new String[] {
									ID_input.getText(),
									controller.getIntermediateCentre().name,
									destination_input.getSelectedItem()
											.toString(),
									farePrice_input.getText(), "工作中" },
							selectedNum);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				frame.toMainPanel();
			}
		});

		setCmpLocation();
	}

	protected void setCmpLocation() {
		// TODO 自动生成的方法存根
		setLayout(null);

		add(OKButton);
		add(function);
		add(ID);
		add(destination);
		add(farePrice);
		add(ID_input);
		add(destination_input);
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
