package presentation.intermediateui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;
import dataservice.managedataservice.CityDistanceDataService;
import businesslogic.datafactory.DataFactory;
import businesslogic.intermediatebl.controller.IntermediateMainController;

public class PlaneManagement_newPanel extends JPanel {
	private JButton OKButton;

	private JLabel function;

	private JLabel plane_ID;
	private JLabel plane_destination;
	private JLabel plane_farePrice;

	private JTextField plane_ID_input;
	private JLabel plane_destination_input;
	private JTextField plane_farePrice_input;

	private IntermediateMainController controller;
	private CityDistanceDataService cityDistanceData;
	private ArrayList<String> citys = new ArrayList<String>();

	private int PANEL_WIDTH = 720;
	private int PANEL_HEIGHT = 480;

	private int planeNum;
	private String intermediateCenterID;
	private String planeID;
	
	private LocationHelper helper;

	public PlaneManagement_newPanel(IntermediateMainController controller) {
		this.controller = controller;

		try {
			cityDistanceData = DataFactory.getCityDistanceData();
			citys = cityDistanceData.getAllCitys();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

//		planeNum = controller.getPlaneList().size() + 1;
//		intermediateCenterID = controller.getIntermediateCentre().organizationID;

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

		plane_destination_input = new JLabel("city");
//		for (String city : citys)
//			plane_destination_input.addItem(city);

		plane_farePrice_input = new JTextField("0.2");
		
		setCmpLocation();
		
		OKButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				
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
		
		helper = new LocationHelper(this);
	}

	private void setCmpLocation() {
		// TODO 自动生成的方法存根
		
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setSize(720, 480);
		frame.add(new PlaneManagement_newPanel(null));
		frame.setVisible(true);
	}
}
