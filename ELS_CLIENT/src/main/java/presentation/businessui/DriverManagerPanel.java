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
import businesslogic.businessbl.controller.DriverManagerController;
import businesslogic.logdiarybl.controller.LogDiaryBLController;
import businesslogic.receiptbl.GetDate;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyCheckBox;
import presentation.commonui.MyComboBox;
import presentation.commonui.MyLabel;
import presentation.commonui.MyTable;
import presentation.commonui.MyTextField;
import presentation.commonui.MyTextLabel;
import presentation.commonui.OperationPanel;
import presentation.commonui.UserFrame;
import presentation.special_ui.AddLabel;
import presentation.special_ui.BoxGroup;
import presentation.special_ui.DeleteLabel;
import presentation.special_ui.ModifyLabel;
import presentation.special_ui.MySearchField;
import type.Sexuality;
import vo.DriverVO;
import vo.LogDiaryVO;

public class DriverManagerPanel extends OperationPanel {

	private MyLabel addLabel;
	private MyLabel delLabel;
	private MyLabel modifyLabel;

	private MySearchField inputField;

	private MyTable messageTable;

	private DriverManagerController controller;

	private UserFrame mainFrame;
	private ArrayList<DriverVO> drivers;

	private int selectedIndex;
	private LogDiaryBLController log;
	// private LocationHelper helper;

	public DriverManagerPanel(DriverManagerController controller, UserFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		log = new LogDiaryBLController();
		addLabel = new AddLabel();
		addLabel.setToolTipText("新增车辆");
		delLabel = new DeleteLabel();
		delLabel.setToolTipText("删除车辆");
		modifyLabel = new ModifyLabel();
		modifyLabel.setToolTipText(" 修改车辆信息");

		inputField = new MySearchField();

		selectedIndex = -1;

		add(addLabel);
		add(delLabel);
		add(modifyLabel);

		add(inputField);

		drivers = controller.getDriverInfo();

		setLayout(null);
		addListener();

		setBaseInfos();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		addLabel.setBounds(35, 34, 30, 30);
		delLabel.setBounds(160, 34, 30, 30);
		modifyLabel.setBounds(285, 34, 30, 30);

		inputField.setBounds((int) (width * 17.154843110504775 / 25), (int) (height * 1.2186379928315412 / 20),
				(int) (width * 5.320600272851296 / 25), (int) (height * 1.075268817204301 / 20));
		messageTable.setLocationAndSize((int) (width * 1.0243277848911652 / 25),
				(int) (height * 3.369175627240143 / 20), (int) (width * 22.98335467349552 / 25),
				(int) (height * 15.412186379928315 / 20));
	}

	private void setBaseInfos() {
		String[] head = new String[] { "司机编号", "姓名", "身份证号", "手机", "性别", "运货次数" };

		int[] widths = { 120, 60, 170, 120, 60, 63 };

		messageTable = new MyTable(head, getInfos(), widths, true);
		add(messageTable);
	}

	private ArrayList<String[]> getInfos() {
		ArrayList<String[]> infos = new ArrayList<String[]>();
		for (DriverVO i : drivers) {
			String sex = null;
			if (i.sexuality == Sexuality.MALE)
				sex = "男";
			else
				sex = "女";
			infos.add(new String[] { i.ID, i.name, i.IdCardNumber, i.phoneNumber, sex, i.time + "" });
		}
		return infos;
	}

	private void addListener() {

		addLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AddPanel addPanel = new AddPanel();
				mainFrame.changePanel(addPanel);
				addLabel.reSet();
			}
		});

		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				ArrayList<Integer> selectedIndexs = messageTable.getSelectedIndex();
				int size = selectedIndexs.size();

				if (size != 1)
					return;

				selectedIndex = selectedIndexs.get(0);
				DriverVO vo = drivers.get(selectedIndex);
				messageTable.cancelSelected(selectedIndex);

				ModifyPanel panel = new ModifyPanel(vo);
				mainFrame.changePanel(panel);
				modifyLabel.reSet();

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
						controller.deleteDriver(drivers.get(i));

					log.addLogDiary(new LogDiaryVO(GetDate.getTime(),BusinessMainController.businessVO,"删除了车辆信息"), GetDate.getTime())
					drivers = controller.getDriverInfo();
					messageTable.setInfos(getInfos());

				}
			}
		});

		inputField.getImageLabel().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = inputField.getText();
				if (id.equals(""))
					warnning("请输入司机ID");

				for (DriverVO i : drivers) {
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

		protected JLabel nameLabel;
		protected MyTextField nameField;

		protected JLabel birthLabel;
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

		protected JLabel idCardNumLabel;
		protected MyTextField idCardNumField;

		protected JLabel phoneNumberLabel;
		protected MyTextField phoneNumberField;

		protected JLabel registrationDeadlineLabel;
		protected MyTextField registrationDeadlineField;

		protected JLabel timeLabel;
		protected MyTextField timeField;

		protected MyLabel confirmButton;
		protected MyLabel cancelButton;

		protected JLabel sexLabel;
		// protected MyComboBox sexBox;
		protected MyTextLabel maleLabel;
		protected MyCheckBox male;
		protected MyTextLabel femaleLabel;
		protected MyCheckBox female;

		protected DriverVO oldVO;
		protected LocationHelper helper;
		protected BoxGroup bg;

		public ModifyPanel(DriverVO vo) {
			this.oldVO = vo;
			idLabel = new MyTextLabel("ID");
			idField = new MyTextField();

			nameLabel = new MyTextLabel("姓名");
			nameField = new MyTextField();

			birthLabel = new MyTextLabel("出生日期");

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

			idCardNumLabel = new MyTextLabel("身份证号码");
			idCardNumField = new MyTextField();

			phoneNumberLabel = new MyTextLabel("手机号");
			phoneNumberField = new MyTextField();

			registrationDeadlineLabel = new MyTextLabel("行驶证期限");
			registrationDeadlineField = new MyTextField();

			timeLabel = new MyTextLabel("本月次数");
			timeField = new MyTextField();

			sexLabel = new MyTextLabel("性别");
			maleLabel = new MyTextLabel("男");
			male = new MyCheckBox();
			femaleLabel = new MyTextLabel("女");
			female = new MyCheckBox();
			bg = new BoxGroup();
			bg.add(male);
			bg.add(female);

			confirmButton = new MyLabel("确认");
			cancelButton = new MyLabel("取消");

			add(idLabel);
			add(idField);
			add(nameLabel);
			add(nameField);
			add(birthLabel);
			add(year);
			add(gap1);
			add(month);
			add(gap2);
			add(day);
			add(idCardNumLabel);
			add(idCardNumField);
			add(phoneNumberLabel);
			add(phoneNumberField);
			add(registrationDeadlineLabel);
			add(registrationDeadlineField);
			add(timeLabel);
			add(timeField);

			add(confirmButton);
			add(cancelButton);

			add(sexLabel);
			add(male);
			add(female);
			setLayout(null);

			setInfo1();
			addListener1();

			helper = new LocationHelper(this);

		}

		public void setBounds(int x, int y, int width, int height) {
			super.setBounds(x, y, width, height);

			idLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 1.4285714285714286 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			idField.setBounds((int) (width * 12.580025608194623 / 25), (int) (height * 1.4285714285714286 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			nameLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 3.080357142857143 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			nameField.setBounds((int) (width * 12.580025608194623 / 25), (int) (height * 3.080357142857143 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			birthLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			month.setBounds((int) (width * 14.788732394366198 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 1.440460947503201 / 25), (int) (height * 1.2053571428571428 / 20));
			gap1.setBounds((int) (width * 16.229193341869397 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 0.2880921895006402 / 25), (int) (height * 1.2053571428571428 / 20));
			day.setBounds((int) (width * 16.51728553137004 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 1.440460947503201 / 25), (int) (height * 1.2053571428571428 / 20));
			gap2.setBounds((int) (width * 14.500640204865556 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 0.2880921895006402 / 25), (int) (height * 1.2053571428571428 / 20));
			year.setBounds((int) (width * 12.580025608194623 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 1.9206145966709347 / 25), (int) (height * 1.2053571428571428 / 20));
			idCardNumLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 6.428571428571429 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			idCardNumField.setBounds((int) (width * 12.580025608194623 / 25), (int) (height * 6.428571428571429 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			phoneNumberLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 8.125 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			phoneNumberField.setBounds((int) (width * 12.612035851472472 / 25), (int) (height * 8.125 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			registrationDeadlineLabel.setBounds((int) (width * 6.818181818181818 / 25),
					(int) (height * 9.776785714285714 / 20), (int) (width * 3.329065300896287 / 25),
					(int) (height * 1.2053571428571428 / 20));
			registrationDeadlineField.setBounds((int) (width * 12.612035851472472 / 25),
					(int) (height * 11.428571428571429 / 20), (int) (width * 5.409731113956466 / 25),
					(int) (height * 1.25 / 20));
			timeLabel.setBounds((int) (width * 6.850192061459667 / 25), (int) (height * 11.428571428571429 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			timeField.setBounds((int) (width * 12.612035851472472 / 25), (int) (height * 9.776785714285714 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			confirmButton.setBounds((int) (width * 7.490396927016645 / 25), (int) (height * 16.383928571428573 / 20),
					(int) (width * 2.4007682458386683 / 25), (int) (height * 1.25 / 20));
			cancelButton.setBounds((int) (width * 14.468629961587707 / 25), (int) (height * 16.383928571428573 / 20),
					(int) (width * 2.4007682458386683 / 25), (int) (height * 1.25 / 20));
			sexLabel.setBounds((int) (width * 6.850192061459667 / 25), (int) (height * 13.080357142857142 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			maleLabel.setBounds((int) (width * 12.789904502046385 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.8185538881309686 / 25), (int) (height * 0.967741935483871 / 20));
			male.setBounds((int) (width * 13.574351978171896 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.9549795361527967 / 25), (int) (height * 1.003584229390681 / 20));
			femaleLabel.setBounds((int) (width * 15.723055934515688 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.8185538881309686 / 25), (int) (height * 0.967741935483871 / 20));
			female.setBounds((int) (width * 16.473396998635742 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.9549795361527967 / 25), (int) (height * 1.003584229390681 / 20));
		}

		protected void setInfo1() {

			Calendar c = new GregorianCalendar();// 新建日期对象
			int y = c.get(Calendar.YEAR);

			for (int i = 1980; i <= y; i++) {
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

			nameField.setText(oldVO.name);

			String[] birth = oldVO.DateOfBirth.split("-");
			int y1 = Integer.parseInt(birth[0]);
			int m1 = Integer.parseInt(birth[1]);
			int d1 = Integer.parseInt(birth[2]);

			year.setSelectedItem(y1);
			month.setSelectedItem(m1);
			day.setSelectedItem(d1);

			idCardNumField.setText(oldVO.IdCardNumber);
			phoneNumberField.setText(oldVO.phoneNumber);
			registrationDeadlineField.setText(oldVO.registrationDeadline);
			registrationDeadlineField.setToolTipText("单位:年");
			timeField.setText(oldVO.time + "");

			if (oldVO.sexuality == Sexuality.MALE)
				male.setSelected(true);
			else
				female.setSelected(true);
		}

		protected void addListener1() {

			confirmButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					String id = idField.getText();

					// 检查格式
					String newName = nameField.getText();
					if (newName.equals("")) {
						warnning("请输入司机姓名");
						return;
					}

					int y = (int) year.getSelectedItem();
					int m = (int) month.getSelectedItem();
					int d = (int) day.getSelectedItem();
					String date = y + "-" + m + "-" + d;

					String idNum = idCardNumField.getText();
					if (idNum.equals("")) {
						warnning("身份证号不能为空");
						return;
					}

					if (idNum.length() != 18) {
						warnning("身份证号码格式不正确");
						return;
					}

					for (int i = 0; i < 18; i++) {
						if (idNum.charAt(i) > '9' || idNum.charAt(i) < '0') {
							warnning("身份证号码格式不正确");
							return;
						}
					}

					String mobilePhone = phoneNumberField.getText();
					if (mobilePhone.equals("")) {
						warnning("司机手机号码不可为空");
						return;

					}
					boolean result = true;

					if (mobilePhone.length() != 11)
						result = false;
					else
						for (int i = 0; i < 11; i++) {
							if ('0' > mobilePhone.charAt(i) || mobilePhone.charAt(i) > '9')
								result = false;
						}
					if (!result) {
						warnning("司机手机号码格式有误！");
						return;
					}

					int ye = 0;
					try {
						ye = Integer.parseInt(registrationDeadlineField.getText());
					} catch (Exception ex) {
						warnning("驾驶证期限为应为整数");
						return;
					}

					int times = 0;
					try {
						times = Integer.parseInt(registrationDeadlineField.getText());
					} catch (Exception ex) {
						warnning("本月次数为应为整数");
						return;
					}

					Sexuality s = null;
					if (male.getSelected())
						s = Sexuality.MALE;
					else
						s = Sexuality.FEMALE;

					DriverVO modifierVO = new DriverVO(id, newName, date, idNum, mobilePhone, oldVO.vehicleOrganization,
							s, ye + "", times);

					if (controller.modifyDriver(modifierVO)) {
						successing("成功修改司机信息");
						String sex = null;
						if (s == Sexuality.MALE)
							sex = "男";
						else
							sex = "女";
						messageTable.setRowValueAt(new String[] { id, newName, idNum, mobilePhone, sex, times + "" },
								selectedIndex);
						drivers = controller.getDriverInfo();
						mainFrame.toMainPanel();

						log.addLogDiary(new LogDiaryVO(GetDate.getTime(), BusinessMainController.businessVO, "修改了车辆信息"),
								GetDate.getTime());
					} else {
						warnning("操作失败，请检查网络连接");
						return;
					}
				}
			});

			cancelButton.addMouseListener(new MouseAdapter() {
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

	// 新增车辆JPanel,和Modify基本一样，但是不同也有点多所以
	class AddPanel extends OperationPanel {
		private JLabel idLabel;
		private MyTextField idField;

		private JLabel nameLabel;
		private MyTextField nameField;

		private JLabel birthLabel;
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

		private JLabel idCardNumLabel;
		private MyTextField idCardNumField;

		private JLabel phoneNumberLabel;
		private MyTextField phoneNumberField;

		private JLabel registrationDeadlineLabel;
		private MyTextField registrationDeadlineField;

		private JLabel timeLabel;
		private MyTextField timeField;

		private MyLabel confirmButton;
		private MyLabel cancelButton;

		private JLabel sexLabel;
		// private MyComboBox sexBox;
		private JLabel maleLabel;
		private MyCheckBox male;
		private JLabel femaleLabel;
		private MyCheckBox female;
		private BoxGroup bg;

		private LocationHelper helper;

		public AddPanel() {

			idLabel = new MyTextLabel("ID");
			idField = new MyTextField();

			nameLabel = new MyTextLabel("姓名");
			nameField = new MyTextField();

			birthLabel = new MyTextLabel("出生日期");

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

			idCardNumLabel = new MyTextLabel("身份证号码");
			idCardNumField = new MyTextField();

			phoneNumberLabel = new MyTextLabel("手机号");
			phoneNumberField = new MyTextField();

			registrationDeadlineLabel = new MyTextLabel("行驶证期限");
			registrationDeadlineField = new MyTextField();

			timeLabel = new MyTextLabel("本月次数");
			timeField = new MyTextField();

			sexLabel = new MyTextLabel("性别");
			maleLabel = new MyTextLabel("男");
			male = new MyCheckBox();
			femaleLabel = new MyTextLabel("女");
			female = new MyCheckBox();
			bg = new BoxGroup();
			bg.add(male);
			bg.add(female);

			confirmButton = new MyLabel("确认");
			cancelButton = new MyLabel("取消");

			add(idLabel);
			add(idField);
			add(nameLabel);
			add(nameField);
			add(birthLabel);
			add(year);
			add(gap1);
			add(month);
			add(gap2);
			add(day);
			add(idCardNumLabel);
			add(idCardNumField);
			add(phoneNumberLabel);
			add(phoneNumberField);
			add(registrationDeadlineLabel);
			add(registrationDeadlineField);
			add(timeLabel);
			add(timeField);

			add(confirmButton);
			add(cancelButton);

			add(sexLabel);
			add(maleLabel);
			add(male);
			add(femaleLabel);
			add(female);
			setLayout(null);

			setInfo1();
			addListener2();

			helper = new LocationHelper(this);

		}

		public void setBounds(int x, int y, int width, int height) {
			super.setBounds(x, y, width, height);

			idLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 1.4285714285714286 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			idField.setBounds((int) (width * 12.580025608194623 / 25), (int) (height * 1.4285714285714286 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			nameLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 3.080357142857143 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			nameField.setBounds((int) (width * 12.580025608194623 / 25), (int) (height * 3.080357142857143 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			birthLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			month.setBounds((int) (width * 14.788732394366198 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 1.440460947503201 / 25), (int) (height * 1.2053571428571428 / 20));
			gap1.setBounds((int) (width * 16.229193341869397 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 0.2880921895006402 / 25), (int) (height * 1.2053571428571428 / 20));
			day.setBounds((int) (width * 16.51728553137004 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 1.440460947503201 / 25), (int) (height * 1.2053571428571428 / 20));
			gap2.setBounds((int) (width * 14.500640204865556 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 0.2880921895006402 / 25), (int) (height * 1.2053571428571428 / 20));
			year.setBounds((int) (width * 12.580025608194623 / 25), (int) (height * 4.776785714285714 / 20),
					(int) (width * 1.9206145966709347 / 25), (int) (height * 1.2053571428571428 / 20));
			idCardNumLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 6.428571428571429 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			idCardNumField.setBounds((int) (width * 12.580025608194623 / 25), (int) (height * 6.428571428571429 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			phoneNumberLabel.setBounds((int) (width * 6.818181818181818 / 25), (int) (height * 8.125 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			phoneNumberField.setBounds((int) (width * 12.612035851472472 / 25), (int) (height * 8.125 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			registrationDeadlineLabel.setBounds((int) (width * 6.818181818181818 / 25),
					(int) (height * 9.776785714285714 / 20), (int) (width * 3.329065300896287 / 25),
					(int) (height * 1.2053571428571428 / 20));
			registrationDeadlineField.setBounds((int) (width * 12.612035851472472 / 25),
					(int) (height * 9.776785714285714 / 20), (int) (width * 5.409731113956466 / 25),
					(int) (height * 1.25 / 20));
			timeLabel.setBounds((int) (width * 6.850192061459667 / 25), (int) (height * 11.428571428571429 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			timeField.setBounds((int) (width * 12.612035851472472 / 25), (int) (height * 11.428571428571429 / 20),
					(int) (width * 5.409731113956466 / 25), (int) (height * 1.25 / 20));
			confirmButton.setBounds((int) (width * 7.490396927016645 / 25), (int) (height * 16.383928571428573 / 20),
					(int) (width * 2.4007682458386683 / 25), (int) (height * 1.25 / 20));
			cancelButton.setBounds((int) (width * 14.468629961587707 / 25), (int) (height * 16.383928571428573 / 20),
					(int) (width * 2.4007682458386683 / 25), (int) (height * 1.25 / 20));
			sexLabel.setBounds((int) (width * 6.850192061459667 / 25), (int) (height * 13.080357142857142 / 20),
					(int) (width * 3.329065300896287 / 25), (int) (height * 1.2053571428571428 / 20));
			maleLabel.setBounds((int) (width * 12.517053206002728 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.8185538881309686 / 25), (int) (height * 0.967741935483871 / 20));
			male.setBounds((int) (width * 13.335607094133698 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.9549795361527967 / 25), (int) (height * 1.003584229390681 / 20));
			maleLabel.setBounds((int) (width * 12.789904502046385 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.8185538881309686 / 25), (int) (height * 0.967741935483871 / 20));
			male.setBounds((int) (width * 13.574351978171896 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.9549795361527967 / 25), (int) (height * 1.003584229390681 / 20));
			femaleLabel.setBounds((int) (width * 15.723055934515688 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.8185538881309686 / 25), (int) (height * 0.967741935483871 / 20));
			female.setBounds((int) (width * 16.473396998635742 / 25), (int) (height * 13.082437275985663 / 20),
					(int) (width * 0.9549795361527967 / 25), (int) (height * 1.003584229390681 / 20));
		}

		private void setInfo1() {

			String ID = getNextID();

			idField.setText(ID);
			idField.setEditable(false);

			Calendar c = new GregorianCalendar();// 新建日期对象
			int y = c.get(Calendar.YEAR);

			for (int i = 1980; i <= y; i++) {
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

			male.setSelected(true);

			timeField.setText("0");
			timeField.setEditable(false);

		}

		private void addListener2() {

			confirmButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					String ID = idField.getText();

					// 检查格式
					String newName = nameField.getText();
					if (newName.equals("")) {
						warnning("请输入司机姓名");
						return;
					}

					int y = (int) year.getSelectedItem();
					int m = (int) month.getSelectedItem();
					int d = (int) day.getSelectedItem();
					String date = y + "-" + m + "-" + d;

					String idNum = idCardNumField.getText();
					if (idNum.equals("")) {
						warnning("身份证号不能为空");
						return;
					}

					if (idNum.length() != 18) {
						warnning("身份证号码格式不正确");
						return;
					}

					for (int i = 0; i < 18; i++) {
						if (idNum.charAt(i) > '9' || idNum.charAt(i) < '0') {
							warnning("身份证号码格式不正确");
							return;
						}
					}

					String mobilePhone = phoneNumberField.getText();
					if (mobilePhone.equals("")) {
						warnning("司机手机号码不可为空");
						return;

					}
					boolean result = true;

					if (mobilePhone.length() != 11)
						result = false;
					else
						for (int i = 0; i < 11; i++) {
							if ('0' > mobilePhone.charAt(i) || mobilePhone.charAt(i) > '9')
								result = false;
						}
					if (!result) {
						warnning("司机手机号码格式有误！");
						return;
					}

					int ye = 0;
					try {
						ye = Integer.parseInt(registrationDeadlineField.getText());
					} catch (Exception ex) {
						warnning("驾驶证期限为应为整数");
						return;
					}

					int times = 0;
					try {
						ye = Integer.parseInt(registrationDeadlineField.getText());
					} catch (Exception ex) {
						warnning("本月次数为应为整数");
						return;
					}

					Sexuality s = null;
					if (male.getSelected())
						s = Sexuality.MALE;
					else
						s = Sexuality.FEMALE;

					DriverVO vo = new DriverVO(ID, newName, date, idNum, mobilePhone,
							BusinessMainController.businessVO.organizationVO, s, ye + "", times);

					if (controller.addDriver(vo)) {
						successing("成功添加司机");
						String sex = null;

						if (s == Sexuality.MALE)
							sex = "男";
						else
							sex = "女";
						messageTable.addRow(new String[] { ID, newName, idNum, mobilePhone, sex, times + "" });
						drivers = controller.getDriverInfo();
						clear();
						mainFrame.toMainPanel();
						
						log.addLogDiary(new LogDiaryVO(GetDate.getTime(),BusinessMainController.businessVO,"增加了车辆信息"), GetDate.getTime())
					} else {
						warnning("提交失败，请检查网络连接");
					}
				}
			});

			cancelButton.addMouseListener(new MouseAdapter() {
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
			idField.setText(getNextID());
			nameField.setText("");

			year.setSelectedIndex(0);
			month.setSelectedIndex(0);
			day.setSelectedIndex(0);

			idCardNumField.setText("");
			phoneNumberField.setText("");
			registrationDeadlineField.setText("");
			male.setSelected(true);
		}

		private String getNextID() {
			int num = controller.getNumOfDriver();
			int numOfZero = 5 - (num + "").length();
			String ID = num + "";
			for (int i = 0; i < numOfZero; i++) {
				ID = 0 + ID;
			}
			return "SJ-" + BusinessMainController.businessVO.organizationVO.organizationID + "-" + ID;
		}
	}

	public void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "司机信息错误", JOptionPane.ERROR_MESSAGE);
	}

	public void successing(String message) {
		JOptionPane.showMessageDialog(null, message, "提交成功", JOptionPane.DEFAULT_OPTION);
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

	private int getDayOfMonth(int year, int month) {

		Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	class WatchPanel extends ModifyPanel {

		public WatchPanel(DriverVO vo) {
			super(vo);

			nameField.setEditable(false);
			idCardNumField.setEditable(false);
			phoneNumberField.setEditable(false);
			registrationDeadlineField.setEditable(false);
			timeField.setEditable(false);

			int width = getWidth();
			int height = getHeight();

			remove(confirmButton);
			cancelButton.setBounds((int) (width * 11.308629961587707 / 25), (int) (height * 16.383928571428573 / 20),
					(int) (width * 2.4007682458386683 / 25), (int) (height * 1.25 / 20));

			year.setEnabled(false);
			month.setEnabled(false);
			day.setEnabled(false);
			male.setEnabled(false);
			female.setEnabled(false);

		}

	}
}
