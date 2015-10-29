package presentation.mainui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 打开客户端的第一个界面
 */
public class MainFrame extends JFrame {

	// 宽度和长度
	public static final int MAIN_WIDTH = 960;
	public static final int MAIN_HEIGHT = 640;
	private JFrame frame = this;
	private JPanel mainPanel;
	private JPanel queryPanel;
	private JPanel signInPanel;

	public MainFrame() {

		mainPanel = new MainPanel();
		add(mainPanel);

		setTitle("                       ELS");
		setSize(MAIN_WIDTH, MAIN_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void toQueryPanel() {
		queryPanel = new QueryPanel();
		remove(mainPanel);
		add(queryPanel);

		setVisible(true);
	}

	public void toSignInPanel() {
		signInPanel = new SignInPanel();
		remove(mainPanel);
		add(signInPanel);

		setVisible(true);
	}

	// 主界面
	class MainPanel extends JPanel {


		// 查询按钮
		private JButton queryButton;
		// 登录按钮
		private JButton signInButton;

		public MainPanel() {
			queryButton = new JButton("物流查询");
			signInButton = new JButton("登录");

			queryButton.setBounds(MAIN_WIDTH / 7, MAIN_HEIGHT / 3, MAIN_WIDTH * 2 / 7, MAIN_HEIGHT / 6);
			signInButton.setBounds(MAIN_WIDTH * 4 / 7, MAIN_HEIGHT / 3, MAIN_WIDTH * 2 / 7, MAIN_HEIGHT / 6);

			queryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					toQueryPanel();
				}
			});

			signInButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					toSignInPanel();
				}
			});

			setLayout(null);
			add(queryButton);
			add(signInButton);

		}
	}

	// 物流查询界面
	class QueryPanel extends JPanel {

		private JLabel inputLabel;
		private JTextField orderNumField;
		private JButton queryButton;
		private JButton cancelButton;

		public QueryPanel() {
			inputLabel = new JLabel("           请输入订单号:");
			orderNumField = new JTextField();
			queryButton = new JButton("查询");
			cancelButton = new JButton("取消");

			inputLabel.setBounds(0, MAIN_HEIGHT / 6, MAIN_WIDTH * 3 / 8, MAIN_HEIGHT / 6);
			orderNumField.setBounds(MAIN_WIDTH * 3 / 8, MAIN_HEIGHT * 2 / 9, MAIN_WIDTH / 2, MAIN_HEIGHT / 9);
			queryButton.setBounds(MAIN_WIDTH / 8, MAIN_HEIGHT * 7 / 12, MAIN_WIDTH / 4, MAIN_HEIGHT / 6);
			cancelButton.setBounds(MAIN_WIDTH * 5 / 8, MAIN_HEIGHT * 7 / 12, MAIN_WIDTH / 4, MAIN_HEIGHT / 6);

			queryButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					query(orderNumField.getText());
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancel();
				}
			});

			setLayout(null);
			add(inputLabel);
			add(orderNumField);
			add(queryButton);
			add(cancelButton);
		}

		public void cancel() {
			frame.remove(queryPanel);
			frame.add(mainPanel);
			frame.repaint();

		}

		// 调用bl层的方法进行查询
		public void query(String orderID) {

		}

	}

	// 登录界面
	class SignInPanel extends JPanel {

		private JLabel userNameLabel;
		private JTextField userNameField;
		private JLabel passwordLabel;
		private JPasswordField passwordField;
		private JButton signInButton;
		private JButton cancelButton;

		public SignInPanel() {
			userNameLabel = new JLabel("      用户名:");
			userNameField = new JTextField();
			passwordLabel = new JLabel("         密码:");
			passwordField = new JPasswordField();
			signInButton = new JButton("登录");
			cancelButton = new JButton("取消");

			signInButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					signIn(userNameField.getText(), new String(passwordField.getPassword()));
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancel();
				}
			});

			// MAIN_WIDTH MAIN_HEIGHT
			userNameLabel.setBounds(MAIN_WIDTH / 6, MAIN_HEIGHT / 7, MAIN_WIDTH / 4, MAIN_HEIGHT * 2 / 15);
			userNameField.setBounds(MAIN_WIDTH *3/ 8, MAIN_HEIGHT / 7, MAIN_WIDTH * 5 / 12, MAIN_HEIGHT * 2 / 15);
			passwordLabel.setBounds(MAIN_WIDTH / 6, MAIN_HEIGHT * 3 / 7, MAIN_WIDTH / 4, MAIN_HEIGHT * 2 / 15);
			passwordField.setBounds(MAIN_WIDTH *3/ 8, MAIN_HEIGHT * 3 / 7, MAIN_WIDTH * 5 / 12, MAIN_HEIGHT * 2 / 15);
			signInButton.setBounds(MAIN_WIDTH / 8, MAIN_HEIGHT * 11 / 15, MAIN_WIDTH / 4, MAIN_HEIGHT * 2 / 15);
			cancelButton.setBounds(MAIN_WIDTH * 5 / 8, MAIN_HEIGHT * 11 / 15, MAIN_WIDTH / 4, MAIN_HEIGHT * 2 / 15);

			setLayout(null);
			add(userNameLabel);
			add(userNameField);
			add(passwordLabel);
			add(passwordField);
			add(signInButton);
			add(cancelButton);
		}

		public void cancel() {
			frame.remove(signInPanel);
			frame.add(mainPanel);
			frame.repaint();

		}

		// 调用bl层的方法登录
		public void signIn(String userID, String password) {

		}
	}
}
