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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.businessbl.controller.DriverManagerController;
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
	// private ArrayList<JCheckBox> selectDriver;
	private ArrayList<JCheckBox> selectDriver;

	private DriverManagerController controller;

	private UserFrame mainFrame;
	private ArrayList<DriverVO> drivers;

	private boolean isModify;
	private boolean isDel;

	private int numOfChoose;
	private int num;

	public DriverManagerPanel(DriverManagerController controller, UserFrame mainFrame) {
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
		selectDriver = new ArrayList<JCheckBox>();
		for (int i = 0; i < 8; i++) {
			selectDriver.add(new JCheckBox());
		}

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

		messageTable.setBackground(getBackground());

		num = 0;
		numOfChoose = 0;
		isModify = false;

		drivers = controller.getDriverInfo();

		setLayout(null);
		addListener();

		// helper = new LocationHelper(this);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		selectDriver.get(0).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 5.625 / 20),
				(int) (width * 0.6722151088348272 / 25), (int) (height * 0.8035714285714286 / 20));
		selectDriver.get(1).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 6.830357142857143 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectDriver.get(2).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 8.125 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectDriver.get(3).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 9.419642857142858 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectDriver.get(4).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 10.758928571428571 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectDriver.get(5).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 12.098214285714286 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectDriver.get(6).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 13.348214285714286 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
		selectDriver.get(7).setBounds((int) (width * 0.2560819462227913 / 25), (int) (height * 14.642857142857142 / 20),
				(int) (width * 0.6402048655569782 / 25), (int) (height * 0.8928571428571429 / 20));
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

				if (num >= (drivers.size() - 1) / 8)
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

		for (JCheckBox i : selectDriver) {
			i.addItemListener(listener);
		}

		modifyLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isModify) {
					int n = 0;

					for (JCheckBox i : selectDriver) {

						if (i.isSelected()) {
							break;
						}
						n++;

					}
					DriverVO vo = drivers.get(num * 8 + n);
					ModifyPanel panel = new ModifyPanel(vo);
					mainFrame.changePanel(panel);
				}
			}
		});

		delLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isDel) {
					if (JOptionPane.showConfirmDialog(null, "确认要删除该司机信息吗", "",
							JOptionPane.DEFAULT_OPTION) == JOptionPane.NO_OPTION) {
						return;
					}
					int n = 0;
					for (JCheckBox i : selectDriver) {
						if (i.isSelected()) {
							i.setSelected(false);
							DriverVO vo = drivers.get(num * 8 + n);
							controller.deleteDriver(vo);
						}
						n++;
						isDel = false;
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
		TableColumn column5 = messageTable.getColumnModel().getColumn(4);
		TableColumn column6 = messageTable.getColumnModel().getColumn(5);

		// 设置宽度
		int tWidth = messageTable.getWidth();
		column1.setPreferredWidth(tWidth * 5 / 32);
		column2.setPreferredWidth(tWidth / 8);
		column3.setPreferredWidth(tWidth * 9 / 32);
		column4.setPreferredWidth(tWidth / 4);
		column5.setPreferredWidth(tWidth * 3 / 32);
		column6.setPreferredWidth(tWidth * 3 / 32);

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
		column5.setCellRenderer(tcr);
		column6.setCellRenderer(tcr);

	}

	// 设置载入动态的内容
	private void setInfos() {
		for (JCheckBox i : selectDriver) {
			remove(i);
		}

		drivers = controller.getDriverInfo();
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
			return 6;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			int index = num * 8 + rowIndex;

			if (index > drivers.size() - 1)
				return null;

			DriverVO vo = drivers.get(index);

			switch (columnIndex) {
			case 0:
				add(selectDriver.get(rowIndex));
				return vo.ID;
			case 1:
				return vo.name;
			case 2:
				return vo.IdCardNumber;
			case 3:
				return vo.phoneNumber;
			case 4:
				if (vo.sexuality == Sexuality.FEMALE)
					return "女";
				else
					return "男";
			case 5:
				return vo.time;
			default:
				return null;
			}
		}

		public String getColumnName(int c) {
			switch (c) {
			case 0:
				return "司机编号";
			case 1:
				return "姓名";
			case 2:
				return "身份证号 ";
			case 3:
				return "手机";
			case 4:
				return "性别";
			case 5:
				return "运货次数";
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

					DriverVO modifierVO = new DriverVO(id, newName, date, idNum, mobilePhone, oldVO.vehicleOrganization,
							s, ye + "", times);

					if (controller.modifyDriver(modifierVO)) {
						successing("成功修改司机信息");
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

	// 新增车辆JPanel,和Modify基本一样，但是不同也有点多所以
	class AddPanel extends JPanel {
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

		public AddPanel() {

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
			male.setBounds((int) (width * 13.284250960307299 / 25), (int) (height * 13.035714285714286 / 20),
					(int) (width * 1.3764404609475032 / 25), (int) (height * 1.3392857142857142 / 20));
			female.setBounds((int) (width * 15.685019206145967 / 25), (int) (height * 13.035714285714286 / 20),
					(int) (width * 2.848911651728553 / 25), (int) (height * 1.3392857142857142 / 20));
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

			String ID = getNextID();

			idField.setText(ID);
			idField.setEditable(false);

			Calendar c = new GregorianCalendar();// 新建日期对象
			int y = c.get(Calendar.YEAR);

			for (int i = 1960; i <= y - 1; i++) {
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

		private void addListener() {

			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
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
					if (male.isSelected())
						s = Sexuality.MALE;
					else
						s = Sexuality.FEMALE;

					DriverVO vo = new DriverVO(ID, newName, date, idNum, mobilePhone,
							BusinessMainController.businessVO.organizationVO, s, ye + "", times);

					if (controller.addDriver(vo)) {
						successing("成功添加司机");
						setInfos();
						clear();
					} else {
						warnning("提交失败，请检查网络连接");
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
			int num = drivers.size();
			int numOfZero = 5 - (num + "").length();
			String ID = num + "";
			for (int i = 0; i < numOfZero; i++) {
				ID = 0 + ID;
			}
			return "JS-" + ID;
		}
	}

	public void warnning(String message) {
		// fix 放到底部信息栏
		JOptionPane.showMessageDialog(null, message, "司机信息错误", JOptionPane.ERROR_MESSAGE);
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
}
