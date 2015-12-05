package presentation.businessui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.commonui.LocationHelper;
import vo.DriverVO;

public class DriverModifyDialog extends JDialog {

	private DriverVO oldVO;
	private boolean isModified;
	private ContentPanel contentPanel;

	//
	private DriverManagerPanel panel;

	public DriverModifyDialog(DriverVO oldVO, DriverManagerPanel panel) {
		this.oldVO = oldVO;
		this.panel = panel;
		contentPanel = new ContentPanel();
		this.setLayout(new BorderLayout());
		add(contentPanel,BorderLayout.CENTER);
		setSize(800, 600);
		setLocationRelativeTo(null);

		setVisible(true);
		setFocusable(false);

	}

	class ContentPanel extends JPanel {
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

		public void ContentPanel() {
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

			
			helper = new LocationHelper(this);
			setLocation();
		}
		
		public void setLocation(){

		}
	}

	public static void main(String[] args) {
		new DriverModifyDialog(null, null);
	}
}
