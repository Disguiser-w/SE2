//package presentation.businessui;
//
//import java.util.ArrayList;
//
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//
//import businesslogic.businessbl.controller.VehicleManagerController;
//
//public class VehicleManagerPanel extends JPanel {
//	private JLabel addLabel;
//	private JLabel delLabel;
//	private JLabel searchLabel;
//	private JTextField inputField;
//	private JButton confirmButton;
//
//	private JTable messageTable;
//	private JLabel previousPageLabel;
//	private JLabel nextPageLabel;
//	private ArrayList<JCheckBox> selectVehicle;
//	private ArrayList<JLabel> totalInfo;
//
//	private VehicleManagerController controller;
//	// private LocationHelper helper;
//
//	public VehicleManagerPanel(VehicleManagerController controller) {
//		this.controller = controller;
//		addLabel = new JLabel();
//		delLabel = new JLabel();
//		searchLabel = new JLabel();
//		inputField = new JTextField();
//		confirmButton = new JButton();
//
//		messageTable = new JTable();
//		previousPageLabel = new JLabel();
//		nextPageLabel = new JLabel();
//		selectVehicle = new ArrayList<JCheckBox>();
//		totalInfo = new ArrayList<JLabel>();
//
//		add(addLabel);
//		add(delLabel);
//		add(searchLabel);
//		add(inputField);
//		add(confirmButton);
//		add(messageTable);
//		add(previousPageLabel);
//		add(nextPageLabel);
//
//		// helper = new LocationHelper(this);
//		setLayout(null);
//	}
//
//	public void setBounds(int x, int y, int width, int height) {
//		super.setBounds(x, y, width, height);
//
//		addLabel.setBounds((int) (width * 2.624839948783611 / 25), (int) (height * 1.1607142857142858 / 20),
//				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
//		delLabel.setBounds((int) (width * 6.530089628681178 / 25), (int) (height * 1.1607142857142858 / 20),
//				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
//		searchLabel.setBounds((int) (width * 15.749039692701665 / 25), (int) (height * 1.3392857142857142 / 20),
//				(int) (width * 0.9282970550576184 / 25), (int) (height * 1.2946428571428572 / 20));
//		inputField.setBounds((int) (width * 16.677336747759284 / 25), (int) (height * 1.3392857142857142 / 20),
//				(int) (width * 4.321382842509603 / 25), (int) (height * 1.3392857142857142 / 20));
//		confirmButton.setBounds((int) (width * 22.247119078104994 / 25), (int) (height * 1.3392857142857142 / 20),
//				(int) (width * 1.7285531370038412 / 25), (int) (height * 1.2946428571428572 / 20));
//		messageTable.setBounds((int) (width * 0.9923175416133163 / 25), (int) (height * 3.9732142857142856 / 20),
//				(int) (width * 22.98335467349552 / 25), (int) (height * 11.964285714285714 / 20));
//		previousPageLabel.setBounds((int) (width * 11.555697823303458 / 25), (int) (height * 17.321428571428573 / 20),
//				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
//		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
//				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
//
//	}
//}

package presentation.businessui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.VehicleManagerController;
import presentation.commonui.LocationHelper;
import presentation.commonui.UserFrame;
import vo.DriverVO;
import vo.OrganizationVO;
import vo.VehicleVO;

public class VehicleManagerPanel extends JPanel {

	private JLabel addLabel;
	private JLabel delLabel;
	private JLabel modifyLabel;

	private JLabel searchLabel;
	private JTextField inputField;
	private JButton confirmButton;

	private JTable messageTable;
	private JLabel previousPageLabel;
	private JLabel nextPageLabel;
	private ArrayList<JCheckBox> selectVehicle;
	private JLabel numOfPage;

	private VehicleManagerController controller;

	private UserFrame mainFrame;
	private ArrayList<VehicleVO> vehicles;

	private ArrayList<DriverVO> driverVOs;
	private ArrayList<OrganizationVO> organizationVOs;

	private boolean isModify;
	private boolean isDel;

	private int numOfChoose;
	private int num;

	private LocationHelper helper;

	public VehicleManagerPanel(VehicleManagerController controller, UserFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		addLabel = new JLabel("增");
		delLabel = new JLabel("删");
		modifyLabel = new JLabel("改");
		addLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		delLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		modifyLabel.setBorder(BorderFactory.createLineBorder(Color.black));

		searchLabel = new JLabel();
		inputField = new JTextField();
		confirmButton = new JButton();
		MessgeTableModel model = new MessgeTableModel();
		messageTable = new JTable(model);
		previousPageLabel = new JLabel(" < ");
		nextPageLabel = new JLabel(" > ");
		selectVehicle = new ArrayList<JCheckBox>();
		for (int i = 0; i < 8; i++) {
			selectVehicle.add(new JCheckBox());
		}
		numOfPage = new JLabel();

		add(addLabel);
		add(delLabel);
		add(modifyLabel);
		add(searchLabel);
		add(inputField);
		add(confirmButton);
		add(messageTable);
		add(previousPageLabel);
		add(nextPageLabel);
		add(messageTable.getTableHeader());
		add(numOfPage);
		messageTable.setBackground(getBackground());

		num = 0;
		numOfChoose = 0;
		isModify = false;

		vehicles = controller.getVehicleInfo();

		setLayout(null);
		addListener();

		// helper = new LocationHelper(this);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		numOfPage.setBounds((int) (width * 12.32394366197183 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.088348271446863 / 25), (int) (height * 1.4732142857142858 / 20));
		selectVehicle.get(0).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 5.625 / 20),
				(int) (width * 0.6722151088348272 / 25), (int) (height * 0.8035714285714286 / 20));
		selectVehicle.get(1).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 6.830357142857143 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectVehicle.get(2).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 8.125 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectVehicle.get(3).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 9.419642857142858 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectVehicle.get(4).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 10.758928571428571 / 20), (int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		selectVehicle.get(5).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 12.098214285714286 / 20), (int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		selectVehicle.get(6).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 13.348214285714286 / 20), (int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		selectVehicle.get(7).setBounds((int) (width * 0.2560819462227913 / 25),
				(int) (height * 14.642857142857142 / 20), (int) (width * 0.6402048655569782 / 25),
				(int) (height * 0.8928571428571429 / 20));
		addLabel.setBounds((int) (width * 2.624839948783611 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		delLabel.setBounds((int) (width * 6.594110115236876 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		modifyLabel.setBounds((int) (width * 10.56338028169014 / 25), (int) (height * 1.1607142857142858 / 20),
				(int) (width * 1.3124199743918055 / 25), (int) (height * 1.8303571428571428 / 20));
		searchLabel.setBounds((int) (width * 15.781049935979514 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 0.9282970550576184 / 25), (int) (height * 1.2946428571428572 / 20));
		inputField.setBounds((int) (width * 16.677336747759284 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 4.321382842509603 / 25), (int) (height * 1.3392857142857142 / 20));
		confirmButton.setBounds((int) (width * 22.247119078104994 / 25), (int) (height * 1.3392857142857142 / 20),
				(int) (width * 1.7285531370038412 / 25), (int) (height * 1.2946428571428572 / 20));
		messageTable.setBounds((int) (width * 1.0243277848911652 / 25), (int) (height * 5.401785714285714 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 10.535714285714286 / 20));
		messageTable.getTableHeader().setBounds((int) (width * 1.0243277848911652 / 25),
				(int) (height * 5.401785714285714 / 20) - (int) (height * 1.435714285714286 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 1.435714285714286 / 20));
		previousPageLabel.setBounds((int) (width * 11.331626120358514 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));

	}

	private void addListener() {
		previousPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (num == 0)
					return;
				else {
					num--;
					setInfos();

				}
			}
		});

		nextPageLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (num >= (vehicles.size() - 1) / 8)
					return;
				else {
					num++;
					setInfos();
				}
			}
		});

		addLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AddPanel addPanel = new AddPanel();
				mainFrame.changePanel(addPanel);
			}
		});
		ItemListener listener = new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				JCheckBox button = ((JCheckBox) (e.getSource()));
				if (button.isSelected()) {
					numOfChoose++;
				} else {
					numOfChoose--;
				}

				if (numOfChoose == 1) {
					isModify = true;
				} else
					isModify = false;

				if (numOfChoose >= 1)
					isDel = true;

			}

		};

		for (JCheckBox i : selectVehicle) {
			i.addItemListener(listener);
		}

		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isModify) {
					int n = 0;

					for (JCheckBox i : selectVehicle) {

						if (i.isSelected()) {
							break;
						}
						n++;

					}
					VehicleVO vo = vehicles.get(num * 8 + n);
					ModifyPanel panel = new ModifyPanel(vo);
					mainFrame.changePanel(panel);
				}
			}
		});

		delLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isDel) {
					if (JOptionPane.showConfirmDialog(null, "确认要删除该车辆信息吗", "",
							JOptionPane.DEFAULT_OPTION) == JOptionPane.NO_OPTION) {
						return;
					}
					int n = 0;
					int m = 0;
					for (JCheckBox i : selectVehicle) {
						if (i.isSelected()) {
							i.setSelected(false);
							VehicleVO vo = vehicles.get(num * 8 + n);
							controller.deleteVehicle(vo);
							m++;
						}
						n++;
						isDel = false;
					}

					if ((vehicles.size() - m - 1) / 8 + 1 < num + 1) {
						num--;
					}
					repaint();
				}

			}
		});
	}

	// 设置table的基本内容，图片，什么的
	private void setBaseInfo() {

		// 设置成不可编辑不可改变位置，大小
		messageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		messageTable.getTableHeader().setReorderingAllowed(false);
		messageTable.getTableHeader().setResizingAllowed(false);

		TableColumn column1 = messageTable.getColumnModel().getColumn(0);
		TableColumn column2 = messageTable.getColumnModel().getColumn(1);
		TableColumn column3 = messageTable.getColumnModel().getColumn(2);
		TableColumn column4 = messageTable.getColumnModel().getColumn(3);

		// 设置宽度
		int tWidth = messageTable.getWidth();
		column1.setPreferredWidth(tWidth * 5 / 18);
		column2.setPreferredWidth(tWidth * 5 / 18);
		column3.setPreferredWidth(tWidth * 5 / 18);
		column4.setPreferredWidth(tWidth / 6);

		messageTable.setRowHeight(messageTable.getHeight() / 8);

		//
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.cyan);
				else
					setBackground(Color.white);

				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};

		tcr.setHorizontalAlignment(JLabel.CENTER);
		column1.setCellRenderer(tcr);
		column2.setCellRenderer(tcr);
		column3.setCellRenderer(tcr);
		column4.setCellRenderer(tcr);

	}

	// 设置载入动态的内容
	private void setInfos() {
		for (JCheckBox i : selectVehicle) {
			remove(i);
		}
		vehicles = controller.getVehicleInfo();
		numOfPage.setText(num + 1 + "/" + ((vehicles.size() - 1) / 8 + 1));

		messageTable.setModel(new MessgeTableModel());
		setBaseInfo();
	}

	private class MessgeTableModel extends AbstractTableModel {

		@Override
		public int getRowCount() {
			return 8;
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			int index = num * 8 + rowIndex;

			if (index > vehicles.size() - 1)
				return null;

			VehicleVO vo = vehicles.get(index);

			switch (columnIndex) {
			case 0:
				add(selectVehicle.get(rowIndex));
				return vo.ID;
			case 1:
				return vo.local.name;
			case 2:
				return vo.destination.name;
			case 3:
				return vo.driver.name;
			default:
				return null;
			}
		}

		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "车辆编号";
			case 1:
				return "出发地";
			case 2:
				return "目的地";
			case 3:
				return "司机";

			default:
				return null;
			}
		}

	}

	public void paintComponent(Graphics g) {

		setInfos();
		super.paintComponent(g);

	}

	class ModifyPanel extends JPanel {

		private JLabel idLabel;
		private JTextField idField;

		private JLabel engineNumberLabel;
		private JTextField engineNumberField;

		private JLabel licensePlateNumberLabel;
		private JTextField liscenPlateNumberField;

		private JLabel lowNumberPlateLabel;
		private JTextField lowNumberPlateField;

		private JLabel buyTimeLabel;
		private JComboBox<Integer> year;
		private JLabel gap1;
		private JComboBox<Integer> month;
		private JLabel gap2;
		private JComboBox<Integer> day;

		// private JLabel year;
		// private JLabel gap1;
		// private JLabel month;
		// private JLabel gap2;
		// private JLabel day;

		// 从购买到现在
		private JLabel serviceTimeLabel;
		private JTextField serviceTimeField;

		// 所有机构
		private JLabel destinationLabel;
		private JComboBox<String> destinationBox;
		// private JLabel destinationBox;

		// local和destinationcity自己设置

		// 选一个司机,如果没司机就不行
		private JLabel driversLabel;
		private JComboBox<String> driversBox;
		// private JLabel driversBox;

		private JButton confirmButton;
		private JButton cancelButton;

		private VehicleVO oldVO;

		private LocationHelper helper;

		public ModifyPanel(VehicleVO vo) {
			this.oldVO = vo;
			idLabel = new JLabel("ID");
			idField = new JTextField();
			engineNumberLabel = new JLabel("发动机号");
			engineNumberField = new JTextField();

			licensePlateNumberLabel = new JLabel("车牌号");
			liscenPlateNumberField = new JTextField();

			lowNumberPlateLabel = new JLabel("底盘号");
			lowNumberPlateField = new JTextField();

			buyTimeLabel = new JLabel("购买时间");
			year = new JComboBox<Integer>();
			gap1 = new JLabel("-");
			month = new JComboBox<Integer>();
			gap2 = new JLabel("-");
			day = new JComboBox<Integer>();
			// year = new JLabel();
			// gap1 = new JLabel("-");
			// month = new JLabel();
			// gap2 = new JLabel("-");
			// day = new JLabel();
			serviceTimeLabel = new JLabel("服役时间");
			serviceTimeField = new JTextField();

			destinationLabel = new JLabel("目的地");
			destinationBox = new JComboBox<String>();
			// destinationBox = new JLabel();

			driversLabel = new JLabel("司机");
			driversBox = new JComboBox<String>();
			// driversBox = new JLabel();

			confirmButton = new JButton("确认");
			cancelButton = new JButton("取消");

			add(idLabel);
			add(idField);
			add(engineNumberLabel);
			add(engineNumberField);
			add(licensePlateNumberLabel);
			add(liscenPlateNumberField);
			add(lowNumberPlateLabel);
			add(lowNumberPlateField);
			add(buyTimeLabel);
			add(year);
			add(gap1);
			add(month);
			add(gap2);
			add(day);
			add(serviceTimeLabel);
			add(serviceTimeField);
			add(destinationLabel);
			add(destinationBox);
			add(driversLabel);
			add(driversBox);
			add(confirmButton);
			add(cancelButton);

			organizationVOs = controller.getOrganizationInfos();
			for (OrganizationVO i : organizationVOs)
				destinationBox.addItem(i.name);

			setLayout(null);
			setInfo1();
			addListener1();
			// helper = new LocationHelper(this);

		}

		public void setBounds(int x, int y, int width, int height) {
			super.setBounds(x, y, width, height);

			idLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 1.7857142857142858 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			idField.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 1.7857142857142858 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.2053571428571428 / 20));
			engineNumberLabel.setBounds((int) (width * 5.729833546734955 / 25),
					(int) (height * 3.3482142857142856 / 20), (int) (width * 4.0332906530089625 / 25),
					(int) (height * 1.1607142857142858 / 20));
			engineNumberField.setBounds((int) (width * 13.636363636363637 / 25),
					(int) (height * 3.3482142857142856 / 20), (int) (width * 5.409731113956466 / 25),
					(int) (height * 1.2053571428571428 / 20));
			licensePlateNumberLabel.setBounds((int) (width * 5.729833546734955 / 25),
					(int) (height * 4.910714285714286 / 20), (int) (width * 4.0332906530089625 / 25),
					(int) (height * 1.1607142857142858 / 20));
			liscenPlateNumberField.setBounds((int) (width * 13.636363636363637 / 25),
					(int) (height * 4.910714285714286 / 20), (int) (width * 5.409731113956466 / 25),
					(int) (height * 1.2053571428571428 / 20));
			lowNumberPlateLabel.setBounds((int) (width * 5.729833546734955 / 25),
					(int) (height * 6.473214285714286 / 20), (int) (width * 4.0332906530089625 / 25),
					(int) (height * 1.1607142857142858 / 20));
			lowNumberPlateField.setBounds((int) (width * 13.636363636363637 / 25),
					(int) (height * 6.473214285714286 / 20), (int) (width * 5.409731113956466 / 25),
					(int) (height * 1.2053571428571428 / 20));
			buyTimeLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			year.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 1.9526248399487836 / 25), (int) (height * 1.1607142857142858 / 20));
			gap1.setBounds((int) (width * 15.58898847631242 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 0.3201024327784891 / 25), (int) (height * 1.1607142857142858 / 20));
			month.setBounds((int) (width * 15.909090909090908 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 1.408450704225352 / 25), (int) (height * 1.1607142857142858 / 20));
			gap2.setBounds((int) (width * 17.317541613316262 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 0.3201024327784891 / 25), (int) (height * 1.1607142857142858 / 20));
			day.setBounds((int) (width * 17.63764404609475 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 1.3764404609475032 / 25), (int) (height * 1.1607142857142858 / 20));
			serviceTimeLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 9.598214285714286 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			serviceTimeField.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 9.598214285714286 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.2053571428571428 / 20));
			destinationLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 11.160714285714286 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			destinationBox.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 11.160714285714286 / 20),
					(int) (width * 5.377720870678617 / 25), (int) (height * 1.1607142857142858 / 20));
			driversLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 12.723214285714286 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			driversBox.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 12.723214285714286 / 20),
					(int) (width * 5.377720870678617 / 25), (int) (height * 1.1607142857142858 / 20));
			confirmButton.setBounds((int) (width * 7.010243277848912 / 25), (int) (height * 16.517857142857142 / 20),
					(int) (width * 2.464788732394366 / 25), (int) (height * 1.3839285714285714 / 20));
			cancelButton.setBounds((int) (width * 14.660691421254802 / 25), (int) (height * 16.517857142857142 / 20),
					(int) (width * 2.464788732394366 / 25), (int) (height * 1.3839285714285714 / 20));
		}

		private void setInfo1() {
			Calendar c = new GregorianCalendar();// 新建日期对象
			int y = c.get(Calendar.YEAR);

			for (int i = 1960; i <= y; i++) {
				year.addItem(i);
			}

			for (int i = 1; i <= 12; i++) {
				month.addItem(i);
			}
			int nowYear = (int) year.getSelectedItem();
			int nowMonth = (int) month.getSelectedItem();

			int d = getDayOfMonth(nowYear, nowMonth);

			for (int i = 1; i <= d; i++) {
				day.addItem(i);
			}

			idField.setText(oldVO.ID);
			idField.setEditable(false);
			engineNumberField.setText(oldVO.engineNumber);
			liscenPlateNumberField.setText(oldVO.licensePlateNumber);
			lowNumberPlateField.setText(oldVO.lowNumberPlate);

			String[] dates = oldVO.buyTime.split("-");
			year.setSelectedItem(Integer.parseInt(dates[0]));
			month.setSelectedItem(Integer.parseInt(dates[1]));
			day.setSelectedItem(Integer.parseInt(dates[2]));

			serviceTimeField.setText(oldVO.serviceTime);

			driverVOs = new ArrayList<DriverVO>();
			driverVOs.add(oldVO.driver);

			ArrayList<DriverVO> allDriverVOs = controller.getDriverInfos();
			for (DriverVO vo : allDriverVOs) {
				if (!vo.isUsing) {
					driverVOs.add(vo);
				}
			}

			driversBox.removeAllItems();
			for (DriverVO i : driverVOs) {
				driversBox.addItem(i.name);
			}

		}

		private void addListener1() {

			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					boolean hasChange = false;

					String id = idField.getText();

					if (!id.equals(oldVO.ID))
						hasChange = true;

					String engineNumber = engineNumberField.getText();
					if (engineNumber.equals("")) {
						warnning("发动机号不能为空");
						return;
					}
					if (!engineNumber.equals(oldVO.engineNumber))
						hasChange = true;

					String licensePlateNumber = liscenPlateNumberField.getText();
					if (licensePlateNumber.equals("")) {
						warnning("车牌号不能为空");
						return;
					}
					if (!licensePlateNumber.equals(oldVO.licensePlateNumber))
						hasChange = true;

					String lowNumberPlate = lowNumberPlateField.getText();
					if (lowNumberPlate.equals("")) {
						warnning("底盘号不可为空");
						return;
					}
					if (!lowNumberPlate.equals(oldVO.lowNumberPlate))
						hasChange = true;

					String buyTime = year.getSelectedItem() + "-" + month.getSelectedItem() + "-"
							+ day.getSelectedItem();

					if (!buyTime.equals(oldVO.buyTime))
						hasChange = true;

					String serviceTime = serviceTimeField.getText();
					if (serviceTime.equals("")) {
						warnning("请输入服役时间(例如:2年)");
						return;
					}

					if (!serviceTime.equals(oldVO.serviceTime))
						hasChange = true;

					OrganizationVO destination = organizationVOs.get(destinationBox.getSelectedIndex());
					String name = destination.getName();
					String destinationCity = destination.getName().substring(0, name.length() - 3);

					if (!destinationCity.equals(oldVO.destinationCity))
						hasChange = true;

					OrganizationVO local = BusinessMainController.businessVO.organizationVO;

					if (!local.name.equals(oldVO.local.name))
						hasChange = true;

					DriverVO vo = driverVOs.get(driversBox.getSelectedIndex());
					vo.isUsing = true;

					if (!vo.ID.equals(oldVO.driver.ID))
						hasChange = true;

					VehicleVO newVO = new VehicleVO(id, engineNumber, licensePlateNumber, lowNumberPlate, buyTime,
							serviceTime, destination, destinationCity, local, vo);

					if (!hasChange) {
						warnning("没有对车辆信息做出修改");
						return;
					}

					if (controller.modifyVehicle(newVO)) {
						successing("成功修改车辆信息");
						setInfos();

					} else {
						warnning("操作失败，请检查网络连接");
						return;
					}

				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.toMainPanel();
				}
			});

			class DateListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					int y = (int) year.getSelectedItem();
					int m = (int) month.getSelectedItem();

					int d = getDayOfMonth(y, m);
					day.removeAllItems();

					for (int i = 1; i <= d; i++) {
						day.addItem(i);
					}
				}
			}
			DateListener listener = new DateListener();
			year.addActionListener(listener);
			month.addActionListener(listener);
		}
	}

	class AddPanel extends JPanel {

		private JLabel idLabel;
		private JTextField idField;

		private JLabel engineNumberLabel;
		private JTextField engineNumberField;

		private JLabel licensePlateNumberLabel;
		private JTextField liscenPlateNumberField;

		private JLabel lowNumberPlateLabel;
		private JTextField lowNumberPlateField;

		private JLabel buyTimeLabel;
		private JComboBox<Integer> year;
		private JLabel gap1;
		private JComboBox<Integer> month;
		private JLabel gap2;
		private JComboBox<Integer> day;

		// private JLabel year;
		// private JLabel gap1;
		// private JLabel month;
		// private JLabel gap2;
		// private JLabel day;

		// 从购买到现在
		private JLabel serviceTimeLabel;
		private JTextField serviceTimeField;

		// 所有机构
		private JLabel destinationLabel;
		private JComboBox<String> destinationBox;
		// private JLabel destinationBox;

		// local和destinationcity自己设置

		// 选一个司机,如果没司机就不行
		private JLabel driversLabel;
		private JComboBox<String> driversBox;
		// private JLabel driversBox;

		private JButton confirmButton;
		private JButton cancelButton;

		private LocationHelper helper;

		public AddPanel() {
			idLabel = new JLabel("ID");
			idField = new JTextField();
			engineNumberLabel = new JLabel("发动机号");
			engineNumberField = new JTextField();

			licensePlateNumberLabel = new JLabel("车牌号");
			liscenPlateNumberField = new JTextField();

			lowNumberPlateLabel = new JLabel("底盘号");
			lowNumberPlateField = new JTextField();

			buyTimeLabel = new JLabel("购买时间");
			year = new JComboBox<Integer>();
			gap1 = new JLabel("-");
			month = new JComboBox<Integer>();
			gap2 = new JLabel("-");
			day = new JComboBox<Integer>();
			// year = new JLabel();
			// gap1 = new JLabel("-");
			// month = new JLabel();
			// gap2 = new JLabel("-");
			// day = new JLabel();
			serviceTimeLabel = new JLabel("服役时间");
			serviceTimeField = new JTextField();

			destinationLabel = new JLabel("目的地");
			destinationBox = new JComboBox<String>();
			// destinationBox = new JLabel();

			driversLabel = new JLabel("司机");
			driversBox = new JComboBox<String>();
			// driversBox = new JLabel();

			confirmButton = new JButton("确认");
			cancelButton = new JButton("取消");

			add(idLabel);
			add(idField);
			add(engineNumberLabel);
			add(engineNumberField);
			add(licensePlateNumberLabel);
			add(liscenPlateNumberField);
			add(lowNumberPlateLabel);
			add(lowNumberPlateField);
			add(buyTimeLabel);
			add(year);
			add(gap1);
			add(month);
			add(gap2);
			add(day);
			add(serviceTimeLabel);
			add(serviceTimeField);
			add(destinationLabel);
			add(destinationBox);
			add(driversLabel);
			add(driversBox);
			add(confirmButton);
			add(cancelButton);

			organizationVOs = controller.getOrganizationInfos();
			for (OrganizationVO i : organizationVOs)
				destinationBox.addItem(i.name);

			setLayout(null);
			setInfo2();
			addListener2();
			// helper = new LocationHelper(this);

		}

		public void setBounds(int x, int y, int width, int height) {
			super.setBounds(x, y, width, height);

			idLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 1.7857142857142858 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			idField.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 1.7857142857142858 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.2053571428571428 / 20));
			engineNumberLabel.setBounds((int) (width * 5.729833546734955 / 25),
					(int) (height * 3.3482142857142856 / 20), (int) (width * 4.0332906530089625 / 25),
					(int) (height * 1.1607142857142858 / 20));
			engineNumberField.setBounds((int) (width * 13.636363636363637 / 25),
					(int) (height * 3.3482142857142856 / 20), (int) (width * 5.409731113956466 / 25),
					(int) (height * 1.2053571428571428 / 20));
			licensePlateNumberLabel.setBounds((int) (width * 5.729833546734955 / 25),
					(int) (height * 4.910714285714286 / 20), (int) (width * 4.0332906530089625 / 25),
					(int) (height * 1.1607142857142858 / 20));
			liscenPlateNumberField.setBounds((int) (width * 13.636363636363637 / 25),
					(int) (height * 4.910714285714286 / 20), (int) (width * 5.409731113956466 / 25),
					(int) (height * 1.2053571428571428 / 20));
			lowNumberPlateLabel.setBounds((int) (width * 5.729833546734955 / 25),
					(int) (height * 6.473214285714286 / 20), (int) (width * 4.0332906530089625 / 25),
					(int) (height * 1.1607142857142858 / 20));
			lowNumberPlateField.setBounds((int) (width * 13.636363636363637 / 25),
					(int) (height * 6.473214285714286 / 20), (int) (width * 5.409731113956466 / 25),
					(int) (height * 1.2053571428571428 / 20));
			buyTimeLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			year.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 1.9526248399487836 / 25), (int) (height * 1.1607142857142858 / 20));
			gap1.setBounds((int) (width * 15.58898847631242 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 0.3201024327784891 / 25), (int) (height * 1.1607142857142858 / 20));
			month.setBounds((int) (width * 15.909090909090908 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 1.408450704225352 / 25), (int) (height * 1.1607142857142858 / 20));
			gap2.setBounds((int) (width * 17.317541613316262 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 0.3201024327784891 / 25), (int) (height * 1.1607142857142858 / 20));
			day.setBounds((int) (width * 17.63764404609475 / 25), (int) (height * 8.035714285714286 / 20),
					(int) (width * 1.3764404609475032 / 25), (int) (height * 1.1607142857142858 / 20));
			serviceTimeLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 9.598214285714286 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			serviceTimeField.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 9.598214285714286 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.2053571428571428 / 20));
			destinationLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 11.160714285714286 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			destinationBox.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 11.160714285714286 / 20),
					(int) (width * 5.377720870678617 / 25), (int) (height * 1.1607142857142858 / 20));
			driversLabel.setBounds((int) (width * 5.729833546734955 / 25), (int) (height * 12.723214285714286 / 20),
					(int) (width * 4.0332906530089625 / 25), (int) (height * 1.1607142857142858 / 20));
			driversBox.setBounds((int) (width * 13.636363636363637 / 25), (int) (height * 12.723214285714286 / 20),
					(int) (width * 5.377720870678617 / 25), (int) (height * 1.1607142857142858 / 20));
			confirmButton.setBounds((int) (width * 7.010243277848912 / 25), (int) (height * 16.517857142857142 / 20),
					(int) (width * 2.464788732394366 / 25), (int) (height * 1.3839285714285714 / 20));
			cancelButton.setBounds((int) (width * 14.660691421254802 / 25), (int) (height * 16.517857142857142 / 20),
					(int) (width * 2.464788732394366 / 25), (int) (height * 1.3839285714285714 / 20));
		}

		private void setInfo2() {
			Calendar c = new GregorianCalendar();// 新建日期对象
			int y = c.get(Calendar.YEAR);

			for (int i = 1960; i <= y; i++) {
				year.addItem(i);
			}

			for (int i = 1; i <= 12; i++) {
				month.addItem(i);
			}
			int nowYear = (int) year.getSelectedItem();
			int nowMonth = (int) month.getSelectedItem();

			int d = getDayOfMonth(nowYear, nowMonth);

			for (int i = 1; i <= d; i++) {
				day.addItem(i);
			}

			int[] date = getDate();
			year.setSelectedItem(date[0]);
			month.setSelectedItem(date[1]);
			day.setSelectedItem(date[2]);

			idField.setText(getVehicleID());
			idField.setEditable(false);

			driverVOs = new ArrayList<DriverVO>();
			ArrayList<DriverVO> allDriverVOs = controller.getDriverInfos();

			for (DriverVO vo : allDriverVOs) {
				if (!vo.isUsing) {
					driverVOs.add(vo);
				}
			}

			driversBox.removeAllItems();
			for (DriverVO i : driverVOs) {
				driversBox.addItem(i.name);
			}

		}

		private void addListener2() {

			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String id = idField.getText();
					String engineNumber = engineNumberField.getText();
					if (engineNumber.equals("")) {
						warnning("发动机号不能为空");
						return;
					}
					String licensePlateNumber = liscenPlateNumberField.getText();
					if (licensePlateNumber.equals("")) {
						warnning("车牌号不能为空");
						return;
					}
					String lowNumberPlate = lowNumberPlateField.getText();
					if (lowNumberPlate.equals("")) {
						warnning("底盘号不可为空");
						return;
					}
					String buyTime = year.getSelectedItem() + "-" + month.getSelectedItem() + "-"
							+ day.getSelectedItem();

					String serviceTime = serviceTimeField.getText();
					if (serviceTime.equals("")) {
						warnning("请输入服役时间(例如:2年)");
						return;
					}

					OrganizationVO destination = organizationVOs.get(destinationBox.getSelectedIndex());
					String name = destination.getName();

					String destinationCity = destination.getName().substring(0, name.length() - 3);
					OrganizationVO local = BusinessMainController.businessVO.organizationVO;

					DriverVO vo = driverVOs.get(driversBox.getSelectedIndex());
					vo.isUsing = true;

					VehicleVO newVO = new VehicleVO(id, engineNumber, licensePlateNumber, lowNumberPlate, buyTime,
							serviceTime, destination, destinationCity, local, vo);

					if (controller.addVehicle(newVO)) {
						successing("成功增加车辆信息");
						setInfo2();
						clear();

					} else {
						warnning("操作失败，请检查网络连接");
						return;
					}
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainFrame.toMainPanel();
				}
			});

			class DateListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					int y = (int) year.getSelectedItem();
					int m = (int) month.getSelectedItem();

					int d = getDayOfMonth(y, m);
					day.removeAllItems();

					for (int i = 1; i <= d; i++) {
						day.addItem(i);
					}
				}
			}
			DateListener listener = new DateListener();
			year.addActionListener(listener);
			month.addActionListener(listener);
		}

		private void clear() {

			idField.setText(getVehicleID());
			engineNumberField.setText("");
			liscenPlateNumberField.setText("");
			lowNumberPlateField.setText("");

			int[] date = getDate();

			year.setSelectedItem(date[0]);
			month.setSelectedItem(date[1]);
			day.setSelectedItem(date[2]);
			serviceTimeField.setText("");
			destinationBox.setSelectedIndex(0);
			if (driversBox.getItemCount() != 0)
				driversBox.setSelectedIndex(0);

		}
	}

	public void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "车辆信息错误", JOptionPane.ERROR_MESSAGE);
	}

	public void successing(String message) {
		JOptionPane.showMessageDialog(null, message, "提交成功", JOptionPane.DEFAULT_OPTION);
	}

	private int getDayOfMonth(int year, int month) {

		Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	private int[] getDate() {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String date = f.format(d);

		int[] dateInt = new int[3];
		String[] dateStr = date.split("-");

		for (int i = 0; i < 3; i++) {
			dateInt[i] = Integer.parseInt(dateStr[i]);
		}
		return dateInt;
	}

	private String getVehicleID() {
		String num = "" + controller.getNumOfVehicle();
		int len = num.length();
		for (int i = 0; i < 5 - len; i++)
			num = 0 + num;

		String ID = "VEH-" + BusinessMainController.businessVO.organizationVO.organizationID + "-" + num;
		return ID;
	}

	// 调整位置

}
