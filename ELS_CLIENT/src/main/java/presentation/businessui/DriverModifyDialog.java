package presentation.businessui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;
import vo.DriverVO;

public class DriverModifyDialog extends JDialog {

	private DriverVO oldVO;
	private boolean isModified;
	private ModifyPanel contentPanel;

	//
	private DriverManagerPanel panel;

	public DriverModifyDialog(DriverVO oldVO, DriverManagerPanel panel) {
		this.oldVO = oldVO;
		this.panel = panel;
		contentPanel = new ModifyPanel();

		add(contentPanel);

		setSize(781, 448);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(false);

	}

	public static void main(String[] args) {
		new DriverModifyDialog(null, null);
	}
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

//	private LocationHelper helper;
//	private LocationHelper 

	public ModifyPanel() {

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
//		idField
//		idField.setEditable(false);
		
		
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
		
		
	}

	private void addListener() {
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
