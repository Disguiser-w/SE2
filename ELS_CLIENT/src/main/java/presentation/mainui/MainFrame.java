package presentation.mainui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.financebl.controller.FinanceMainController;
import businesslogic.managebl.controller.ManageMainController;
import businesslogic.repertorybl.controller.RepertoryMainController;
import businesslogic.userbl.UserBL;
import businesslogic.userbl.controller.UserMainController;
import init.UserNameController;
import presentation.commonui.LocationHelper;
import vo.LogVO;

/**
 * 打开客户端的第一个界面
 */
public class MainFrame extends JFrame {

	// 宽度和长度
	public static final int MAIN_WIDTH = 400;
	public static final int MAIN_HEIGHT = 300;
	private JFrame frame = this;
	private JPanel mainPanel;
	private JPanel queryPanel;
	private JPanel signInPanel;
	private UserBL userbl;

	public MainFrame() {

		mainPanel = new MainPanel();
		add(mainPanel);
		userbl = new UserBL();

		setTitle("                       ELS");
		setSize(MAIN_WIDTH, MAIN_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
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
		// private LocationHelper helper;

		public MainPanel() {
			queryButton = new JButton("物流查询");
			signInButton = new JButton("登录");

			int width = MAIN_WIDTH;
			int height = MAIN_HEIGHT;

			queryButton.setBounds((int) (width * 15.1875 / 25), (int) (height * 12.8 / 20), (int) (width * 4.5625 / 25),
					(int) (height * 2.533333333333333 / 20));
			signInButton.setBounds((int) (width * 5.0625 / 25), (int) (height * 12.8 / 20), (int) (width * 4.5625 / 25),
					(int) (height * 2.533333333333333 / 20));
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

			// helper = new LocationHelper(this);

		}
	}

	// 物流查询界面
	class QueryPanel extends JPanel {

		private JLabel inputLabel;
		private JTextField orderNumField;
		private JButton queryButton;
		private JButton cancelButton;

		private LocationHelper helper;

		public QueryPanel() {
			inputLabel = new JLabel("   请输入订单号:");
			orderNumField = new JTextField();
			queryButton = new JButton("查询");
			cancelButton = new JButton("取消");
			int width = MAIN_WIDTH;
			int height = MAIN_HEIGHT;

			inputLabel.setBounds((int) (width * 9.0 / 25), (int) (height * 4.0 / 20), (int) (width * 6.375 / 25),
					(int) (height * 1.8666666666666667 / 20));
			orderNumField.setBounds((int) (width * 4.6875 / 25), (int) (height * 7.933333333333334 / 20),
					(int) (width * 15.6875 / 25), (int) (height * 1.8666666666666667 / 20));
			queryButton.setBounds((int) (width * 6.375 / 25), (int) (height * 15.2 / 20), (int) (width * 4.3125 / 25),
					(int) (height * 1.9333333333333333 / 20));
			cancelButton.setBounds((int) (width * 14.25 / 25), (int) (height * 15.2 / 20), (int) (width * 4.3125 / 25),
					(int) (height * 1.9333333333333333 / 20));

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
		// private UserNameController nameController;
		private JLabel userNameLabel;
		// private JTextField userNameField;
		private JLabel passwordLabel;
		private JPasswordField passwordField;
		private JButton signInButton;
		private JButton cancelButton;
		private JLabel imageLabel;
		private JComboBox names;
		// private JComboBox names;

		private UserNameController nameController;
		// private LocationHelper helper;

		public SignInPanel() {
			userNameLabel = new JLabel("用户名:");
			// userNameField = new JTextField();
			passwordLabel = new JLabel("   密码:");
			passwordField = new JPasswordField();
			signInButton = new JButton("登录");
			cancelButton = new JButton("取消");

			imageLabel = new JLabel();
			names = new JComboBox();

			imageLabel.setBorder(BorderFactory.createLineBorder(Color.black));

			nameController = new UserNameController();

			signInButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					signIn((String) names.getSelectedItem(), new String(passwordField.getPassword()));
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancel();
				}
			});

			// MAIN_WIDTH MAIN_HEIGHT
			int width = MAIN_WIDTH;
			int height = MAIN_HEIGHT;

			setLayout(null);
			add(names);
			add(userNameLabel);
			// add(userNameField);
			add(passwordLabel);
			add(passwordField);
			add(signInButton);
			add(cancelButton);
			add(imageLabel);

			// add(names);
			setInfos();
			updateNames();

			// helper = new LocationHelper(this);

			userNameLabel.setBounds((int) (width * 4.6875 / 25), (int) (height * 9.266666666666667 / 20),
					(int) (width * 3.875 / 25), (int) (height * 1.7333333333333334 / 20));
			names.setBounds((int) (width * 8.9375 / 25), (int) (height * 9.266666666666667 / 20),
					(int) (width * 11.4375 / 25), (int) (height * 1.8 / 20));
			passwordLabel.setBounds((int) (width * 4.6875 / 25), (int) (height * 11.933333333333334 / 20),
					(int) (width * 3.875 / 25), (int) (height * 1.7333333333333334 / 20));
			passwordField.setBounds((int) (width * 8.9375 / 25), (int) (height * 11.933333333333334 / 20),
					(int) (width * 11.5 / 25), (int) (height * 1.8 / 20));
			signInButton.setBounds((int) (width * 6.625 / 25), (int) (height * 15.733333333333333 / 20),
					(int) (width * 3.875 / 25), (int) (height * 1.7333333333333334 / 20));
			cancelButton.setBounds((int) (width * 14.1875 / 25), (int) (height * 15.733333333333333 / 20),
					(int) (width * 3.875 / 25), (int) (height * 1.7333333333333334 / 20));
			imageLabel.setBounds((int) (width * 9.125 / 25), (int) (height * 2.2666666666666666 / 20),
					(int) (width * 6.6875 / 25), (int) (height * 5.6 / 20));

		}

		public void cancel() {
			frame.remove(signInPanel);
			frame.add(mainPanel);
			frame.repaint();

		}

		private void setInfos() {

			passwordField.setEchoChar('*');

			names.setEditable(true);// 将JComboBox设成是可编辑的.
			ComboBoxEditor editor = names.getEditor();
			names.configureEditor(editor, "");
			names.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (names.getItemCount() == 0)
						return;
					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
						nameController.deleteName((String) names.getSelectedItem());
						updateNames();
						repaint();
					}
				}
			});

		}

		private void updateNames() {

			ArrayList<String> name = nameController.getUserName();

			if (name != null) {
				names.removeAllItems();
				for (String i : name) {
					names.addItem(i);

				}
			}

			names.configureEditor(names.getEditor(), "");
		}

		// 调用bl层的方法登录
		public void signIn(String userID, String password) {
			// try{
			// UserPO userpo = udService.findUser(userID);
			// if(userpo.equals(null)) //找不到该用户，返回2
			// return new LogVO("nouser", null);
			// else if(!(userpo.getPassword().equals(password))) //用户密码错误，返回1
			// return new LogVO("falsepassword", null);
			// else{
			// return new LogVO("success", poToVO(userpo)); //登录成功，返回0
			// }
			// }catch(RemoteException exception){
			// exception.printStackTrace();
			// return new LogVO("The server failed", null);
			// }

			LogVO logvo = userbl.login(userID, password);

			if (logvo.logReply.equals("nouser")) {

			} else if (logvo.logReply.equals("falsepassword")) {

			} else {
				// 成功登录，生成界面
				nameController.addNewName(userID);
				updateNames();

				String type = userID.split("-")[0];
				// 不同人员的方式自己来补完
				
				switch (type) {
				case "KD":
					// 快递员登录
					ExpressMainController expressMainController = new ExpressMainController(userID);
					break;
				case "YYT":
					// 营业厅业务员登录
					BusinessMainController businessMainController = new BusinessMainController(userID);
					break;
				// 来这里加吧，上面的 uservo通过 logvo.uservo得到
				case "CW":
					try {
						FinanceMainController financeMainController = new FinanceMainController(userID);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "GLY":
					try {
						UserMainController userMainController = new UserMainController(userID);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "JL":
					try {
						ManageMainController manageMainController = new ManageMainController(userID);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "CK":
					try {
						RepertoryMainController repertoryMainController = new RepertoryMainController(userID);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("该类人员不存在，请联系管理员");
				}

				frame.setVisible(false);
			}
		}
	}
}
