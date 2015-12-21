
package presentation.businessui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.VehicleManagerController;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;
import presentation.special_ui.AddLabel;
import presentation.special_ui.DeleteLabel;
import presentation.special_ui.ModifyLabel;
import presentation.special_ui.MySearchField;
import vo.DriverVO;
import vo.OrganizationVO;
import vo.VehicleVO;

public class VehicleManagerPanel extends OperationPanel {

	private MyLabel addLabel;
	private MyLabel delLabel;
	private MyLabel modifyLabel;
	private MyTable messageTable;

	private MySearchField inputField;

	private VehicleManagerController controller;

	private UserFrame mainFrame;
	private ArrayList<VehicleVO> vehicles;
	private ArrayList<DriverVO> driverVOs;

	private ArrayList<OrganizationVO> organizationVOs;

	private LocationHelper helper;
	private int selectedIndex;

	public VehicleManagerPanel(VehicleManagerController controller, UserFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		selectedIndex = -1;
		addLabel = new AddLabel();
		delLabel = new DeleteLabel();
		modifyLabel = new ModifyLabel();

		inputField = new MySearchField();

		add(addLabel);
		add(delLabel);
		add(modifyLabel);
		add(inputField);
		vehicles = controller.getVehicleInfo();

		setLayout(null);
		addListener();

		setBaseInfos();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		addLabel.setBounds((int) (width * 1.2278308321964528 / 25), (int) (height * 1.039426523297491 / 20),
				(int) (width * 1.4324693042291952 / 25), (int) (height * 1.3978494623655915 / 20));
		delLabel.setBounds((int) (width * 6.207366984993179 / 25), (int) (height * 1.039426523297491 / 20),
				(int) (width * 1.4324693042291952 / 25), (int) (height * 1.3978494623655915 / 20));
		modifyLabel.setBounds((int) (width * 11.084583901773533 / 25), (int) (height * 1.039426523297491 / 20),
				(int) (width * 1.4324693042291952 / 25), (int) (height * 1.3978494623655915 / 20));

		inputField.setBounds((int) (width * 17.154843110504775 / 25), (int) (height * 1.2186379928315412 / 20),
				(int) (width * 5.320600272851296 / 25), (int) (height * 1.075268817204301 / 20));

		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25),
				(int) (height * 3.369175627240143 / 20), (int) (width * 22.98335467349552 / 25),
				(int) (height * 15.412186379928315 / 20));

	}

	private void setBaseInfos() {
		String[] head = new String[] { "车辆编号", "出发地", "目的地", "司机" };

		int[] widths = { 173, 150, 150, 120 };

		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos() {
		ArrayList<String[]> infos = new ArrayList<String[]>();
		for (VehicleVO i : vehicles) {

			infos.add(new String[] { i.ID, i.destination.name, i.local.name, i.driver.name });
		}
		return infos;
	}

	private void addListener() {

		addLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AddPanel addPanel = new AddPanel();
				mainFrame.changePanel(addPanel);
			}
		});

		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
				int size = selectedIndexs.size();

				if (size != 1)
					return;

				selectedIndex = selectedIndexs.get(0);
				VehicleVO vo = vehicles.get(selectedIndex);
				messageTable.cancelSelected(selectedIndex);

				ModifyPanel panel = new ModifyPanel(vo);
				mainFrame.changePanel(panel);

			}
		});

		delLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				messageTable.setAllSelected(false);

				ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
				int size = selectedIndexs.size();
				if (size == 0)
					return;
				else {
					for (int i : selectedIndexs)
						controller.deleteVehicle(vehicles.get(i));

					vehicles = controller.getVehicleInfo();
					messageTable.setInfos(getInfos());

				}
			}
		});

		inputField.getImageLabel().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = inputField.getText();
				if (id.equals(""))
					warnning("请输入车辆ID");

				for (VehicleVO i : vehicles) {
					if (i.ID.equals(id)) {
						mainFrame.changePanel(new WatchPanel(i));
					}
				}
			}
		});
	}

	class ModifyPanel extends OperationPanel {

		protected JLabel idLabel;
		protected MyTextField idField;

		protected JLabel engineNumberLabel;
		protected MyTextField engineNumberField;

		protected JLabel licensePlateNumberLabel;
		protected MyTextField liscenPlateNumberField;

		protected JLabel lowNumberPlateLabel;
		protected MyTextField lowNumberPlateField;

		protected JLabel buyTimeLabel;
		protected MyComboBox<Integer> year;
		protected JLabel gap1;
		protected MyComboBox<Integer> month;
		protected JLabel gap2;
		protected MyComboBox<Integer> day;

		// protected JLabel year;
		// protected JLabel gap1;
		// protected JLabel month;
		// protected JLabel gap2;
		// protected JLabel day;

		// 从购买到现在
		protected JLabel serviceTimeLabel;
		protected MyTextField serviceTimeField;

		// 所有机构
		protected JLabel destinationLabel;
		protected MyComboBox<String> destinationBox;
		// protected JLabel destinationBox;

		// local和destinationcity自己设置

		// 选一个司机,如果没司机就不行
		protected JLabel driversLabel;
		protected MyComboBox<String> driversBox;
		// protected JLabel driversBox;

		protected JLabel confirmLabel;
		protected JLabel cancelLabel;

		protected VehicleVO oldVO;

		protected LocationHelper helper;

		public ModifyPanel(VehicleVO vo) {
			this.oldVO = vo;
			idLabel = new MyTextLabel("ID");
			idField = new MyTextField();
			engineNumberLabel = new MyTextLabel("发动机号");
			engineNumberField = new MyTextField();

			licensePlateNumberLabel = new MyTextLabel("车牌号");
			liscenPlateNumberField = new MyTextField();

			lowNumberPlateLabel = new MyTextLabel("底盘号");
			lowNumberPlateField = new MyTextField();

			buyTimeLabel = new MyTextLabel("购买时间");
			year = new MyComboBox<Integer>();
			gap1 = new MyTextLabel("-");
			month = new MyComboBox<Integer>();
			gap2 = new MyTextLabel("-");
			day = new MyComboBox<Integer>();
			// year = new MyTextLabel();
			// gap1 = new MyTextLabel("-");
			// month = new MyTextLabel();
			// gap2 = new MyTextLabel("-");
			// day = new MyTextLabel();
			serviceTimeLabel = new MyTextLabel("服役时间");
			serviceTimeField = new MyTextField();

			destinationLabel = new MyTextLabel("目的地");
			destinationBox = new MyComboBox<String>();
			// destinationBox = new MyTextLabel();

			driversLabel = new MyTextLabel("司机");
			driversBox = new MyComboBox<String>();
			// driversBox = new MyTextLabel();

			confirmLabel = new MyLabel("确认");
			cancelLabel = new MyLabel("取消");

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
			add(confirmLabel);
			add(cancelLabel);

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
			confirmLabel.setBounds((int) (width * 7.010243277848912 / 25), (int) (height * 16.517857142857142 / 20),
					(int) (width * 2.464788732394366 / 25), (int) (height * 1.3839285714285714 / 20));
			cancelLabel.setBounds((int) (width * 14.660691421254802 / 25), (int) (height * 16.517857142857142 / 20),
					(int) (width * 2.464788732394366 / 25), (int) (height * 1.3839285714285714 / 20));
		}

		protected void setInfo1() {
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

		protected void addListener1() {

			confirmLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

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
					String name = destination.name;
					String destinationCity = destination.name.substring(0, name.length() - 3);

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
						messageTable.setRowValueAt(new String[] { id, destination.name, local.name, vo.name },
								selectedIndex);
						vehicles = controller.getVehicleInfo();
						mainFrame.toMainPanel();

					} else {
						warnning("操作失败，请检查网络连接");
						return;
					}

				}
			});

			cancelLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
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

	class AddPanel extends OperationPanel {

		private JLabel idLabel;
		private MyTextField idField;

		private JLabel engineNumberLabel;
		private MyTextField engineNumberField;

		private JLabel licensePlateNumberLabel;
		private MyTextField liscenPlateNumberField;

		private JLabel lowNumberPlateLabel;
		private MyTextField lowNumberPlateField;

		private JLabel buyTimeLabel;
		private MyComboBox<Integer> year;
		private JLabel gap1;
		private MyComboBox<Integer> month;
		private JLabel gap2;
		private MyComboBox<Integer> day;

		// private JLabel year;
		// private JLabel gap1;
		// private JLabel month;
		// private JLabel gap2;
		// private JLabel day;

		// 从购买到现在
		private JLabel serviceTimeLabel;
		private MyTextField serviceTimeField;

		// 所有机构
		private JLabel destinationLabel;
		private MyComboBox<String> destinationBox;
		// private JLabel destinationBox;

		// local和destinationcity自己设置

		// 选一个司机,如果没司机就不行
		private JLabel driversLabel;
		private MyComboBox<String> driversBox;
		// private JLabel driversBox;

		private JLabel confirmLabel;
		private JLabel cancelLabel;

		private boolean hasAdd;
		private LocationHelper helper;

		public AddPanel() {
			idLabel = new MyTextLabel("ID");
			idField = new MyTextField();
			engineNumberLabel = new MyTextLabel("发动机号");
			engineNumberField = new MyTextField();

			licensePlateNumberLabel = new MyTextLabel("车牌号");
			liscenPlateNumberField = new MyTextField();

			lowNumberPlateLabel = new MyTextLabel("底盘号");
			lowNumberPlateField = new MyTextField();

			buyTimeLabel = new MyTextLabel("购买时间");
			year = new MyComboBox<Integer>();
			gap1 = new MyTextLabel("-");
			month = new MyComboBox<Integer>();
			gap2 = new MyTextLabel("-");
			day = new MyComboBox<Integer>();
			// year = new MyTextLabel();
			// gap1 = new MyTextLabel("-");
			// month = new MyTextLabel();
			// gap2 = new MyTextLabel("-");
			// day = new MyTextLabel();
			serviceTimeLabel = new MyTextLabel("服役时间");
			serviceTimeField = new MyTextField();

			destinationLabel = new MyTextLabel("目的地");
			destinationBox = new MyComboBox<String>();
			// destinationBox = new MyTextLabel();

			driversLabel = new MyTextLabel("司机");
			driversBox = new MyComboBox<String>();
			// driversBox = new MyTextLabel();

			confirmLabel = new MyLabel("确认");
			cancelLabel = new MyLabel("取消");

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
			add(confirmLabel);
			add(cancelLabel);

			organizationVOs = controller.getOrganizationInfos();
			for (OrganizationVO i : organizationVOs)
				destinationBox.addItem(i.name);

			hasAdd = false;

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
			confirmLabel.setBounds((int) (width * 7.010243277848912 / 25), (int) (height * 16.517857142857142 / 20),
					(int) (width * 2.464788732394366 / 25), (int) (height * 1.3839285714285714 / 20));
			cancelLabel.setBounds((int) (width * 14.660691421254802 / 25), (int) (height * 16.517857142857142 / 20),
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

			confirmLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

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
					String name = destination.name;

					String destinationCity = destination.name.substring(0, name.length() - 3);
					OrganizationVO local = BusinessMainController.businessVO.organizationVO;

					DriverVO vo = driverVOs.get(driversBox.getSelectedIndex());
					vo.isUsing = true;

					VehicleVO newVO = new VehicleVO(id, engineNumber, licensePlateNumber, lowNumberPlate, buyTime,
							serviceTime, destination, destinationCity, local, vo);

					if (controller.addVehicle(newVO)) {
						successing("成功增加车辆信息");
						setInfo2();
						clear();
						messageTable.addRow(new String[] { id, destination.name, local.name, vo.name });
						vehicles = controller.getVehicleInfo();
						mainFrame.toMainPanel();

					} else {
						warnning("操作失败，请检查网络连接");
						return;
					}
				}
			});

			cancelLabel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
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

	class WatchPanel extends ModifyPanel {

		public WatchPanel(VehicleVO vo) {
			super(vo);

			idField.setEditable(false);
			engineNumberField.setEditable(false);
			liscenPlateNumberField.setEditable(false);
			lowNumberPlateField.setEditable(false);
			serviceTimeField.setEditable(false);
			destinationBox.setEnabled(false);
			driversBox.setEnabled(false);

			int width = getWidth();
			int height = getHeight();

			remove(confirmLabel);
			cancelLabel.setBounds((int) (width * 11.308629961587707 / 25), (int) (height * 16.383928571428573 / 20),
					(int) (width * 2.4007682458386683 / 25), (int) (height * 1.25 / 20));

			year.setEnabled(false);
			month.setEnabled(false);
			day.setEnabled(false);

		}

	}

}
