package presentation.businessui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import businesslogic.businessbl.controller.DriverManagerController;
import presentation.commonui.LocationHelper;
import presentation.commonui.UserFrame;
import type.Sexuality;
import vo.DriverVO;

public class DriverManagerPanel extends JPanel {
	private JLabel addLabel;
	private JLabel delLabel;
	private JLabel modifyLabel;

	private JLabel searchLabel;
	private JTextField inputField;
	private JButton confirmButton;

	private JTable messageTable;
	private JLabel previousPageLabel;
	private JLabel nextPageLabel;
	private ArrayList<JCheckBox> selectDriver;
	private ArrayList<JLabel> totalInfo;
	private DriverManagerController controller;
	private LocationHelper helper;
	// 修改后返回的modifierVO 存在这里面
	private DriverVO modifierVO;

	private UserFrame mainFrame;

	public DriverManagerPanel(DriverManagerController controller, UserFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		addLabel = new JLabel("增");
		delLabel = new JLabel("删");
		modifyLabel = new JLabel("改");

		searchLabel = new JLabel();
		inputField = new JTextField();
		confirmButton = new JButton();

		messageTable = new JTable();
		previousPageLabel = new JLabel();
		nextPageLabel = new JLabel();
		selectDriver = new ArrayList<JCheckBox>();
		totalInfo = new ArrayList<JLabel>();

		add(addLabel);
		add(delLabel);
		add(modifyLabel);
		add(searchLabel);
		add(inputField);
		add(confirmButton);
		add(messageTable);
		add(previousPageLabel);
		add(nextPageLabel);

		helper = new LocationHelper(this);
		setLayout(null);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

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
		messageTable.setBounds((int) (width * 1.0243277848911652 / 25), (int) (height * 3.9732142857142856 / 20),
				(int) (width * 22.98335467349552 / 25), (int) (height * 11.964285714285714 / 20));
		previousPageLabel.setBounds((int) (width * 11.331626120358514 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));
		nextPageLabel.setBounds((int) (width * 13.380281690140846 / 25), (int) (height * 17.321428571428573 / 20),
				(int) (width * 1.0243277848911652 / 25), (int) (height * 1.4732142857142858 / 20));

	}

	class ModifyPanel extends JPanel {
		private JLabel idLabel;
		private JTextField idField;

		private JLabel nameLabel;
		private JTextField nameField;

		private JLabel birthLabel;
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

		private JLabel idCardNumLabel;
		private JTextField idCardNumField;

		private JLabel phoneNumberLabel;
		private JTextField phoneNumberField;

		private JLabel registrationDeadlineLabel;
		private JTextField registrationDeadlineField;

		private JLabel timeLabel;
		private JTextField timeField;

		private JButton confirmButton;
		private JButton cancelButton;

		private JLabel sexLabel;
		// private JComboBox sexBox;
		private JRadioButton male;
		private JRadioButton female;

		// 是否改变
		private boolean hasChange;
		private DriverVO oldVO;

		public ModifyPanel(DriverVO vo) {
			this.oldVO = vo;
			idLabel = new JLabel("ID");
			idField = new JTextField();

			nameLabel = new JLabel("姓名");
			nameField = new JTextField();

			birthLabel = new JLabel("出生日期");

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

			idCardNumLabel = new JLabel("身份证号码");
			idCardNumField = new JTextField();

			phoneNumberLabel = new JLabel("手机号");
			phoneNumberField = new JTextField();

			registrationDeadlineLabel = new JLabel("行驶证期限");
			registrationDeadlineField = new JTextField();

			timeLabel = new JLabel("本月次数");
			timeField = new JTextField();

			sexLabel = new JLabel("性别");
			male = new JRadioButton("男");
			female = new JRadioButton("女");
			ButtonGroup group = new ButtonGroup();
			group.add(male);
			group.add(female);
			confirmButton = new JButton("确认");
			cancelButton = new JButton("取消");

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

			setInfo();
			addListener();

			// helper = new LocationHelper(this);

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
			male.setBounds((int) (width * 13.284250960307299 / 25), (int) (height * 13.035714285714286 / 20),
					(int) (width * 1.3764404609475032 / 25), (int) (height * 1.3392857142857142 / 20));
			female.setBounds((int) (width * 15.685019206145967 / 25), (int) (height * 13.035714285714286 / 20),
					(int) (width * 2.848911651728553 / 25), (int) (height * 1.3392857142857142 / 20));
		}

		private int getDayOfMonth(int year, int month) {

			Calendar c = Calendar.getInstance();
			c.set(year, month, 1);
			c.add(Calendar.DAY_OF_YEAR, -1);
			return c.get(Calendar.DAY_OF_MONTH);
		}

		private void setInfo() {
			// private JLabel idCardNumLabel;
			// private JTextField idCardNumField;
			//
			// private JLabel phoneNumberLabel;
			// private JTextField phoneNumberField;
			//
			// private JLabel registrationDeadlineLabel;
			// private JTextField registrationDeadlineField;
			//
			// private JLabel timeLabel;
			// private JTextField timeField;
			//
			// private JButton confirmButton;
			// private JButton cancelButton;
			//
			// private JLabel sexLabel;
			// // private JComboBox sexBox;
			// private JRadioButton male;
			// private JRadioButton female;
			//

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

		private void addListener() {

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
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
						ye = Integer.parseInt(registrationDeadlineField.getText());
					} catch (Exception ex) {
						warnning("本月次数为应为整数");
						return;
					}

					Sexuality s = null;
					if (male.isSelected())
						s = Sexuality.MALE;
					else
						s = Sexuality.FEMALE;

					modifierVO = new DriverVO(id, newName, date, idNum, mobilePhone, oldVO.vehicleOrganization, s,
							ye + "", times);

					controller.modifyDriver(modifierVO);
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

	public void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "订单信息错误", JOptionPane.ERROR_MESSAGE);
	}

}
