package presentation.businessui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;
import vo.DriverVO;

public class DriverModifyDialog extends JDialog{

	private DriverVO oldVO;
	private boolean isModified;
	private CPanel contentPanel;

	//
	private DriverManagerPanel panel;

	public DriverModifyDialog(DriverVO oldVO, DriverManagerPanel panel) {
		this.oldVO = oldVO;
		this.panel = panel;
		contentPanel = new CPanel();

		add(contentPanel);

		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(false);

	}

	public static void main(String[] args) {
		new DriverModifyDialog(null, null);
	}
}

class CPanel extends JPanel {
	private JLabel idLabel;
	private JTextField idField;

	private JLabel nameLabel;
	private JTextField nameField;

	private JLabel birthLabel;
	private JTextField birthField;

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
	private JLabel sexBox;

	private LocationHelper helper;

	public CPanel() {

		idLabel = new JLabel("ID");
		idField = new JTextField();

		nameLabel = new JLabel("姓名");
		nameField = new JTextField();

		birthLabel = new JLabel("出生日期");
		birthField = new JTextField();
		idCardNumLabel = new JLabel("身份证号码");
		idCardNumField = new JTextField();

		phoneNumberLabel = new JLabel("手机号");
		phoneNumberField = new JTextField();

		registrationDeadlineLabel = new JLabel("行驶证期限");
		registrationDeadlineField = new JTextField();

		timeLabel = new JLabel("本月次数");
		timeField = new JTextField();

		sexLabel = new JLabel("性别");
		sexBox = new JLabel();

		confirmButton = new JButton("");
		cancelButton = new JButton("");

		add(idLabel);
		add(idField);
		add(nameLabel);
		add(nameField);
		add(birthLabel);
		add(birthField);
		add(idCardNumLabel);
		add(idCardNumField);
		add(phoneNumberLabel);
		add(phoneNumberField);
		add(registrationDeadlineLabel);
		add(registrationDeadlineField);
		add(timeLabel);
		add(timeField);
		add(sexLabel);
		add(sexBox);
		add(confirmButton);
		add(cancelButton);

		setLayout(null);

		helper = new LocationHelper(this);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		idLabel.setBounds((int) (width * 5.897583429228999 / 25), (int) (height * 1.6875 / 20),
				(int) (width * 3.481012658227848 / 25), (int) (height * 0.84375 / 20));
		idField.setBounds((int) (width * 9.723820483314155 / 25), (int) (height * 1.6875 / 20),
				(int) (width * 3.2220943613348676 / 25), (int) (height * 0.84375 / 20));
		nameLabel.setBounds((int) (width * 5.897583429228999 / 25), (int) (height * 3.15625 / 20),
				(int) (width * 3.3659378596087457 / 25), (int) (height * 0.84375 / 20));
		nameField.setBounds((int) (width * 9.95397008055236 / 25), (int) (height * 3.1875 / 20),
				(int) (width * 2.5316455696202533 / 25), (int) (height * 0.71875 / 20));
		birthLabel.setBounds((int) (width * 6.012658227848101 / 25), (int) (height * 4.5625 / 20),
				(int) (width * 2.848101265822785 / 25), (int) (height * 0.84375 / 20));
		birthField.setBounds((int) (width * 9.551208285385501 / 25), (int) (height * 4.53125 / 20),
				(int) (width * 2.905638665132336 / 25), (int) (height * 0.75 / 20));
		idCardNumLabel.setBounds((int) (width * 5.811277330264672 / 25), (int) (height * 6.15625 / 20),
				(int) (width * 3.423475258918297 / 25), (int) (height * 1.09375 / 20));
		idCardNumField.setBounds((int) (width * 9.723820483314155 / 25), (int) (height * 6.1875 / 20),
				(int) (width * 3.538550057537399 / 25), (int) (height * 1.09375 / 20));
		phoneNumberLabel.setBounds((int) (width * 5.926352128883774 / 25), (int) (height * 7.71875 / 20),
				(int) (width * 3.1070195627157653 / 25), (int) (height * 1.0 / 20));
		phoneNumberField.setBounds((int) (width * 9.896432681242807 / 25), (int) (height * 7.8125 / 20),
				(int) (width * 2.819332566168009 / 25), (int) (height * 1.09375 / 20));
		registrationDeadlineLabel.setBounds((int) (width * 5.926352128883774 / 25), (int) (height * 9.25 / 20),
				(int) (width * 2.905638665132336 / 25), (int) (height * 1.28125 / 20));
		registrationDeadlineField.setBounds((int) (width * 9.637514384349828 / 25), (int) (height * 9.375 / 20),
				(int) (width * 3.1933256616800922 / 25), (int) (height * 1.15625 / 20));
		timeLabel.setBounds((int) (width * 5.897583429228999 / 25), (int) (height * 11.0 / 20),
				(int) (width * 3.2220943613348676 / 25), (int) (height * 1.125 / 20));
		timeField.setBounds((int) (width * 9.838895281933256 / 25), (int) (height * 11.03125 / 20),
				(int) (width * 2.5028768699654775 / 25), (int) (height * 1.125 / 20));
		confirmButton.setBounds((int) (width * 5.840046029919447 / 25), (int) (height * 12.40625 / 20),
				(int) (width * 3.998849252013809 / 25), (int) (height * 1.5625 / 20));
		cancelButton.setBounds((int) (width * 10.47180667433832 / 25), (int) (height * 12.8125 / 20),
				(int) (width * 0.8630609896432682 / 25), (int) (height * 0.625 / 20));
		sexLabel.setBounds((int) (width * 5.293440736478711 / 25), (int) (height * 14.90625 / 20),
				(int) (width * 2.6467203682393556 / 25), (int) (height * 1.1875 / 20));
		sexBox.setBounds((int) (width * 16.48446490218642 / 25), (int) (height * 13.96875 / 20),
				(int) (width * 2.0713463751438437 / 25), (int) (height * 1.03125 / 20));
	}
}
