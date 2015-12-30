package presentation.mainui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import businesslogic.businessbl.controller.BusinessMainController;
import businesslogic.expressbl.LogisticQuery;
import businesslogic.expressbl.controller.ExpressMainController;
import businesslogic.financebl.controller.FinanceMainController;
import businesslogic.intermediatebl.controller.IntermediateMainController;
import businesslogic.managebl.controller.ManageMainController;
import businesslogic.repertorybl.controller.RepertoryMainController;
import businesslogic.userbl.UserBL;
import businesslogic.userbl.controller.UserMainController;
import common.ImageGetter;
import init.UserNameController;
import presentation.commonui.LocationHelper;
import presentation.commonui.MyTextField;
import vo.LogVO;
import vo.OrderVO;

/**
 * 打开客户端的第一个界面
 */
public class MainFrame extends JFrame {

	// 宽度和长度
	public static final int MAIN_WIDTH = 320;
	public static final int MAIN_HEIGHT = 600;
	private JFrame frame = this;
	private JPanel mainPanel;
	private JPanel queryPanel;
	private JPanel signInPanel;
	private JScrollPane resultPanel;
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

	}

	public void showFrame() {

		setVisible(true);
		((MainPanel) mainPanel).showPanel();
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
		private Image image;
		private ImageIcon query_normal;
		private ImageIcon query_hover;
		private ImageIcon query_press;
		private ImageIcon signIn_normal;
		private ImageIcon signIn_hover;
		private ImageIcon signIn_press;

		private float alpha;
		// 查询按钮
		private JLabel queryButton;
		// 登录按钮
		private JLabel signInButton;

		private boolean isPressed;

		// private LocationHelper helper;

		public MainPanel() {
			alpha = 0;

			image = ImageGetter.getImage("loginPanel.png").getImage();

			query_normal = ImageGetter.getImage("query_0.png");
			query_hover = ImageGetter.getImage("query_1.png");
			query_press = ImageGetter.getImage("query_2.png");
			signIn_normal = ImageGetter.getImage("signIn_0.png");
			signIn_hover = ImageGetter.getImage("signIn_1.png");
			signIn_press = ImageGetter.getImage("signIn_2.png");

			queryButton = new JLabel();
			signInButton = new JLabel();

			int width = MAIN_WIDTH;
			int height = MAIN_HEIGHT;

			queryButton.setIcon(query_normal);
			signInButton.setIcon(signIn_normal);
			queryButton.setBounds((int) (width * 38 / 320),
					(int) (height * 297 / 600), (int) (width * 97 / 320),
					(int) (height * 36 / 600));
			signInButton.setBounds((int) (width * 186 / 320),
					(int) (height * 297 / 600), (int) (width * 97 / 320),
					(int) (height * 36 / 600));

			queryButton.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						queryButton.setIcon(query_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					queryButton.setIcon(query_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					queryButton.setIcon(query_press);
				}

				public void mouseReleased(MouseEvent e) {
					isPressed = false;
					queryButton.setIcon(query_hover);
					toQueryPanel();
				}
			});

			signInButton.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						signInButton.setIcon(signIn_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					signInButton.setIcon(signIn_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					signInButton.setIcon(signIn_press);
				}

				public void mouseReleased(MouseEvent e) {
					isPressed = false;
					signInButton.setIcon(signIn_hover);
					toSignInPanel();
				}
			});

			setLayout(null);
			add(queryButton);
			add(signInButton);

			// helper = new LocationHelper(this);

		}

		public void showPanel() {
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int width = getWidth();
			int height = getHeight();
			g.drawImage(image, 0, 0, width, height, this);
		}
	}

	// 物流查询界面
	class QueryPanel extends JPanel {

		private LogisticQuery query;
		private JTextField orderNumField;
		private JLabel queryButton;

		private LocationHelper helper;
		private Image queryPanelImage;

		public QueryPanel() {
			query = new LogisticQuery();
			orderNumField = new MyTextField();
			queryButton = new JLabel("查询");

			queryPanelImage = ImageGetter.getImage("queryPanel.png").getImage();
			int width = MAIN_WIDTH;
			int height = MAIN_HEIGHT;

			orderNumField.setBounds((int) (width * 4.6875 / 25),
					(int) (height * 7.933333333333334 / 20),
					(int) (width * 15.6875 / 25),
					(int) (height * 1.8666666666666667 / 20));
			queryButton.setBounds((int) (width * 6.375 / 25),
					(int) (height * 15.2 / 20), (int) (width * 4.3125 / 25),
					(int) (height * 1.9333333333333333 / 20));

			queryButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					query(orderNumField.getText());
				}

				public void mousePressed(MouseEvent e) {
					// /
				}

				public void mouseReleased(MouseEvent e) {
					// /
				}
			});

			setLayout(null);
			add(orderNumField);
			add(queryButton);

			repaint();

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(queryPanelImage, 0, 0, getWidth(), getHeight(), this);
		}

		public void cancel() {
			frame.remove(queryPanel);
			frame.add(mainPanel);
			frame.repaint();

		}

		// 调用bl层的方法进行查询
		public void query(String orderID) {
			OrderVO vo = query.getOrderInfo(orderID);
			if (vo == null) {
				warnning("订单号不存在");
				return;
			}
			frame.remove(queryPanel);
			QueryResultPanel panel = new QueryResultPanel(vo);
			resultPanel = new JScrollPane(panel);
			resultPanel.setBounds(0, 0, 400, 300);

			frame.add(resultPanel);
			frame.setVisible(true);

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
		private JComboBox<String> names;
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
			names = new JComboBox<String>();

			imageLabel.setBorder(BorderFactory.createLineBorder(Color.black));

			nameController = new UserNameController();

			signInButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					signIn((String) names.getSelectedItem(), new String(
							passwordField.getPassword()));
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

			userNameLabel.setBounds((int) (width * 4.6875 / 25),
					(int) (height * 9.266666666666667 / 20),
					(int) (width * 3.875 / 25),
					(int) (height * 1.7333333333333334 / 20));
			names.setBounds((int) (width * 8.9375 / 25),
					(int) (height * 9.266666666666667 / 20),
					(int) (width * 11.4375 / 25), (int) (height * 1.8 / 20));
			passwordLabel.setBounds((int) (width * 4.6875 / 25),
					(int) (height * 11.933333333333334 / 20),
					(int) (width * 3.875 / 25),
					(int) (height * 1.7333333333333334 / 20));
			passwordField.setBounds((int) (width * 8.9375 / 25),
					(int) (height * 11.933333333333334 / 20),
					(int) (width * 11.5 / 25), (int) (height * 1.8 / 20));
			signInButton.setBounds((int) (width * 6.625 / 25),
					(int) (height * 15.733333333333333 / 20),
					(int) (width * 3.875 / 25),
					(int) (height * 1.7333333333333334 / 20));
			cancelButton.setBounds((int) (width * 14.1875 / 25),
					(int) (height * 15.733333333333333 / 20),
					(int) (width * 3.875 / 25),
					(int) (height * 1.7333333333333334 / 20));
			imageLabel.setBounds((int) (width * 9.125 / 25),
					(int) (height * 2.2666666666666666 / 20),
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
			names.getEditor().getEditorComponent()
					.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent e) {
							if (names.getItemCount() == 0)
								return;
							if (e.getKeyCode() == KeyEvent.VK_DELETE) {
								nameController.deleteName((String) names
										.getSelectedItem());
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
		public void signIn(final String userID, String password) {
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

			if (logvo.logReply.equals("The user doesn't exist")) {
				JOptionPane.showMessageDialog(null, "不存在该编号用户","错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (logvo.logReply.equals("The userID and the password don't match")) {
				JOptionPane.showMessageDialog(null, "用户密码错误，请重新输入","错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else {

				(new Thread(new Runnable() {
					public void run() {

						frame.setVisible(false);
						// 成功登录，生成界面
						nameController.addNewName(userID);
//						updateNames();

						String type = userID.split("-")[0];
						// 不同人员的方式自己来补完

						switch (type) {
						case "KD":
							// 快递员登录
							ExpressMainController expressMainController = new ExpressMainController(
									userID);
							break;
						case "ZZZX":
							// 中转中心业务员登录
							try {
								IntermediateMainController intermediateMaincontroller = new IntermediateMainController(
										userID);
							} catch (Exception e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
							}
							break;
						case "YYT":
							// 营业厅业务员登录
							BusinessMainController businessMainController = new BusinessMainController(
									userID);
							break;
						// 来这里加吧，上面的 uservo通过 logvo.uservo得到
						case "CW":
							try {
								FinanceMainController financeMainController = new FinanceMainController(
										userID);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case "admin":
							try {
								UserMainController userMainController = new UserMainController(
										userID);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case "JL":
							try {
								ManageMainController manageMainController = new ManageMainController(
										userID);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case "CK":
							try {
								RepertoryMainController repertoryMainController = new RepertoryMainController(
										userID);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						default:
							System.out.println("该类人员不存在，请联系管理员");
						}

					}
				})).start();

			}
		}
	}

	class QueryResultPanel extends JScrollPane {
		private JLabel stateLabel;
		private JLabel historyLabel;
		private JButton cancel;

		private OrderVO vo;
		private int dx1;
		private int dx2;
		private LocationHelper helper;

		public QueryResultPanel(OrderVO vo) {
			stateLabel = new JLabel();
			historyLabel = new JLabel("历史转运流程:");
			cancel = new JButton("返回");
			this.vo = vo;
			setResult();
			setLayout(null);

			add(stateLabel);
			add(historyLabel);
			add(cancel);

			// addMouseListener(new MouseAdapter() {
			// public void mouseClicked(MouseEvent e) {
			// System.out.println(e.getX() + " " + e.getY());
			// }
			// });
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.remove(resultPanel);
					((MainFrame) frame).toQueryPanel();

				}
			});

			// helper = new LocationHelper(this);
		}

		public void setResult() {
			String state = null;
			switch (vo.order_state) {

			case WAITING_ENVEHICLE:
				state = "等待装车";
				break;
			// 装运中
			case TRANSFERING:
				state = "转运中";
				break;
			// 被营业厅接收后的状态
			case WAITING_DISTRIBUTE:
				state = "等待派件";
				break;
			// 正在派送
			case DISTRIBUEING:
				state = "派件中";
				break;
			// 完成时的状态
			case FINISHED:
				state = "已完成";
				break;
			default:
				break;
			}

			int center = 200;
			String stateStr = "货物状态" + state;
			stateLabel.setText(stateStr);
			int l = getWidthByNum(stateStr);
			stateLabel.setBounds(center - l / 2, 30, l, 25);

			String history = "历史转运流程:";
			int l2 = getWidthByNum(history);
			historyLabel.setBounds(center - l2 / 2, 105, l2, 20);

			int len = vo.history.size();

			setPreferredSize(new Dimension(280, 255 + 105 * (len - 1)));

			int i = 0;
			for (String record : vo.history) {
				JLabel label = new JLabel(record);
				add(label);
				int len1 = getWidthByNum(record);
				label.setBounds(200 - len1 / 2, 130 + 105 * i, len1, 25);

				i++;
				if (i != len) {
					JLabel gap = new JLabel("|");
					int len2 = getWidthByNum("|");

					gap.setBounds(200 - len2 / 2, 130 + 105 * (i - 1) + 25,
							len2, 25);
					add(gap);
				}
			}

			int l3 = getWidthByNum("返回");
			cancel.setBounds(200 - l3 / 2 - 15, 255 + 105 * (len - 1) - 50,
					l3 + 30, 30);
		}

		public int getWidthByNum(String str) {
			return 13 * str.length();
		}
	}

	private void warnning(String msg) {
		JOptionPane.showMessageDialog(null, msg, "订单信息错误",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
