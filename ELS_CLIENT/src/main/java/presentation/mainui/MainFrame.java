package presentation.mainui;

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

import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
import presentation.commonui.MyComboBox;
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
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainPanel = new MainPanel();
		add(mainPanel);

		// setTitle(" ELS");
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

	public void min() {
		this.setExtendedState(JFrame.ICONIFIED);
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
		private ImageIcon exit_normal;
		private ImageIcon exit_hover;
		private ImageIcon exit_press;
		private ImageIcon min_normal;
		private ImageIcon min_hover;
		private ImageIcon min_press;

		private JLabel queryButton;
		private JLabel signInButton;
		private JLabel exit;
		private JLabel min;

		private boolean isPressed;

		public MainPanel() {
			image = ImageGetter.getImage("loginPanel.png").getImage();

			query_normal = ImageGetter.getImage("query_0.png");
			query_hover = ImageGetter.getImage("query_1.png");
			query_press = ImageGetter.getImage("query_2.png");
			signIn_normal = ImageGetter.getImage("signIn_0.png");
			signIn_hover = ImageGetter.getImage("signIn_1.png");
			signIn_press = ImageGetter.getImage("signIn_2.png");
			exit_normal = ImageGetter.getImage("exit_0.png");
			exit_hover = ImageGetter.getImage("exit_1.png");
			exit_press = ImageGetter.getImage("exit_2.png");
			min_normal = ImageGetter.getImage("min_0.png");
			min_hover = ImageGetter.getImage("min_1.png");
			min_press = ImageGetter.getImage("min_2.png");

			queryButton = new JLabel();
			signInButton = new JLabel();
			exit = new JLabel();
			min = new JLabel();

			queryButton.setIcon(query_normal);
			signInButton.setIcon(signIn_normal);
			exit.setIcon(exit_normal);
			min.setIcon(min_normal);

			queryButton.setBounds(MAIN_WIDTH * 38 / 320, MAIN_HEIGHT * 297 / 600, MAIN_WIDTH * 97 / 320,
					MAIN_HEIGHT * 36 / 600);
			signInButton.setBounds(MAIN_WIDTH * 186 / 320, MAIN_HEIGHT * 297 / 600, MAIN_WIDTH * 97 / 320,
					MAIN_HEIGHT * 36 / 600);
			exit.setBounds(295, 8, 12, 12);
			min.setBounds(269, 17, 14, 2);

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

					try {
						userbl = new UserBL();
					} catch (Exception ex) {
						warnning("网络连接错误或服务器未打开，请检查IP地址和服务器开启状态。");
						return;
					}
					isPressed = false;
					queryButton.setIcon(query_hover);
					toQueryPanel();
					queryButton.setIcon(query_normal);

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

					try {
						userbl = new UserBL();
					} catch (Exception ex) {
						warnning("网络连接错误或服务器未打开，请检查IP地址和服务器开启状态。");
						return;
					}

					isPressed = false;
					signInButton.setIcon(signIn_hover);
					toSignInPanel();
					signInButton.setIcon(signIn_normal);
				}
			});

			exit.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						exit.setIcon(exit_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					exit.setIcon(exit_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					exit.setIcon(exit_press);
				}

				public void mouseReleased(MouseEvent e) {

					isPressed = false;
					exit.setIcon(exit_hover);
					System.exit(0);
				}
			});

			min.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						min.setIcon(min_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					min.setIcon(min_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					min.setIcon(min_press);
				}

				public void mouseReleased(MouseEvent e) {
					isPressed = false;
					min.setIcon(min_hover);
					min();
				}
			});
			setLayout(null);
			add(queryButton);
			add(signInButton);
			add(exit);
			add(min);
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

		private Image image;
		private ImageIcon query_normal;
		private ImageIcon query_hover;
		private ImageIcon query_press;
		private ImageIcon cancel_normal;
		private ImageIcon cancel_hover;
		private ImageIcon cancel_press;
		private ImageIcon exit_normal;
		private ImageIcon exit_hover;
		private ImageIcon exit_press;
		private ImageIcon min_normal;
		private ImageIcon min_hover;
		private ImageIcon min_press;

		private JLabel queryButton;
		private JTextField ID_input;
		private JLabel cancel;
		private JLabel exit;
		private JLabel min;

		private boolean isPressed;

		public QueryPanel() {
			image = ImageGetter.getImage("background_login.png").getImage();
			query_normal = ImageGetter.getImage("query_3.png");
			query_hover = ImageGetter.getImage("query_4.png");
			query_press = ImageGetter.getImage("query_5.png");
			cancel_normal = ImageGetter.getImage("return_0.png");
			cancel_hover = ImageGetter.getImage("return_1.png");
			cancel_press = ImageGetter.getImage("return_2.png");
			exit_normal = ImageGetter.getImage("exit_0.png");
			exit_hover = ImageGetter.getImage("exit_1.png");
			exit_press = ImageGetter.getImage("exit_2.png");
			min_normal = ImageGetter.getImage("min_0.png");
			min_hover = ImageGetter.getImage("min_1.png");
			min_press = ImageGetter.getImage("min_2.png");

			query = new LogisticQuery();

			ID_input = new JTextField();

			queryButton = new JLabel();
			cancel = new JLabel();
			exit = new JLabel();
			min = new JLabel();

			queryButton.setIcon(query_normal);
			cancel.setIcon(cancel_normal);
			exit.setIcon(exit_normal);
			min.setIcon(min_normal);

			ID_input.setBounds(66, 214, 188, 39);
			queryButton.setBounds(66, 282, 82, 27);
			cancel.setBounds(173, 282, 82, 27);
			exit.setBounds(295, 8, 12, 12);
			min.setBounds(269, 17, 14, 2);

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
					// queryPanel
				}
			});

			cancel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						cancel.setIcon(cancel_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					cancel.setIcon(cancel_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					cancel.setIcon(cancel_press);
				}

				public void mouseReleased(MouseEvent e) {
					isPressed = false;
					cancel.setIcon(cancel_hover);
					cancel();
				}
			});

			exit.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						exit.setIcon(exit_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					exit.setIcon(exit_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					exit.setIcon(exit_press);
				}

				public void mouseReleased(MouseEvent e) {
					isPressed = false;
					exit.setIcon(exit_hover);
					System.exit(0);
				}
			});

			min.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						min.setIcon(min_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					min.setIcon(min_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					min.setIcon(min_press);
				}

				public void mouseReleased(MouseEvent e) {
					isPressed = false;
					min.setIcon(min_hover);
					min();
				}
			});

			setLayout(null);
			add(ID_input);
			add(queryButton);
			add(cancel);
			add(exit);
			add(min);

			repaint();

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
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
		private Image image;
		private ImageIcon signIn_normal;
		private ImageIcon signIn_hover;
		private ImageIcon signIn_press;
		private ImageIcon cancel_normal;
		private ImageIcon cancel_hover;
		private ImageIcon cancel_press;
		private ImageIcon exit_normal;
		private ImageIcon exit_hover;
		private ImageIcon exit_press;
		private ImageIcon min_normal;
		private ImageIcon min_hover;
		private ImageIcon min_press;

		private JLabel signInButton;
		private JLabel cancel;
		private JLabel exit;
		private JLabel min;

		private JPasswordField passwordField;
		private MyComboBox<String> names;

		private boolean isPressed;

		private UserNameController nameController;

		public SignInPanel() {
			image = ImageGetter.getImage("background_login.png").getImage();
			signIn_normal = ImageGetter.getImage("login_0.png");
			signIn_hover = ImageGetter.getImage("login_1.png");
			signIn_press = ImageGetter.getImage("login_2.png");
			cancel_normal = ImageGetter.getImage("return_0.png");
			cancel_hover = ImageGetter.getImage("return_1.png");
			cancel_press = ImageGetter.getImage("return_2.png");
			exit_normal = ImageGetter.getImage("exit_0.png");
			exit_hover = ImageGetter.getImage("exit_1.png");
			exit_press = ImageGetter.getImage("exit_2.png");
			min_normal = ImageGetter.getImage("min_0.png");
			min_hover = ImageGetter.getImage("min_1.png");
			min_press = ImageGetter.getImage("min_2.png");

			signInButton = new JLabel();
			cancel = new JLabel();
			exit = new JLabel();
			min = new JLabel();

			signInButton.setIcon(signIn_normal);
			cancel.setIcon(cancel_normal);
			exit.setIcon(exit_normal);
			min.setIcon(min_normal);

			passwordField = new JPasswordField();
			names = new MyComboBox<String>();

			nameController = new UserNameController();

			names.setBounds(66, 214, 188, 39);
			passwordField.setBounds(66, 269, 188, 39);
			signInButton.setBounds(66, 344, 82, 27);
			cancel.setBounds(173, 344, 82, 27);
			exit.setBounds(295, 8, 12, 12);
			min.setBounds(269, 17, 14, 2);
			passwordField.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						signIn((String) names.getSelectedItem(), new String(passwordField.getPassword()));
					}
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
					signIn((String) names.getSelectedItem(), new String(passwordField.getPassword()));
				}
			});

			cancel.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						cancel.setIcon(cancel_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					cancel.setIcon(cancel_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					cancel.setIcon(cancel_press);
				}

				public void mouseReleased(MouseEvent e) {
					isPressed = false;
					cancel.setIcon(cancel_hover);
					cancel();
				}
			});

			exit.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						exit.setIcon(exit_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					exit.setIcon(exit_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					exit.setIcon(exit_press);
				}

				public void mouseReleased(MouseEvent e) {
					isPressed = false;
					exit.setIcon(exit_hover);
					System.exit(0);
				}
			});

			min.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					if (!isPressed) {
						min.setIcon(min_hover);
					}

				}

				public void mouseExited(MouseEvent e) {
					min.setIcon(min_normal);
				}

				public void mousePressed(MouseEvent e) {
					isPressed = true;
					min.setIcon(min_press);
				}

				public void mouseReleased(MouseEvent e) {
					isPressed = false;
					min.setIcon(min_hover);
					min();
				}
			});

			setLayout(null);
			add(passwordField);
			add(names);
			add(signInButton);
			add(cancel);
			add(exit);
			add(min);

			setInfos();
			updateNames();

			String lastID = nameController.getLastID();
			for (int i = 0; i < names.getItemCount(); i++) {
				if (names.getItemAt(i).equals(lastID)) {
					names.setSelectedIndex(i);
					ComboBoxEditor editor = names.getEditor();
					names.configureEditor(editor, lastID);
				}
			}
			repaint();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
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

			final LogVO logvo = userbl.login(userID, password);

			if (logvo.logReply.equals("The user doesn't exist")) {
				JOptionPane.showMessageDialog(null, "不存在该编号用户", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (logvo.logReply.equals("The userID and the password don't match")) {
				JOptionPane.showMessageDialog(null, "用户密码错误，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			} else {

				(new Thread(new Runnable() {
					public void run() {

						frame.setVisible(false);
						// 成功登录，生成界面
						nameController.addNewName(userID);
						// updateNames();

						String type = logvo.uservo.userID.split("-")[0];
						// 不同人员的方式自己来补完

						switch (type) {
						case "KD":
							// 快递员登录
							ExpressMainController expressMainController = new ExpressMainController(
									logvo.uservo.userID);
							break;
						case "ZZZX":
							// 中转中心业务员登录
							try {
								IntermediateMainController intermediateMaincontroller = new IntermediateMainController(
										logvo.uservo.userID);
							} catch (Exception e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
							}
							break;
						case "YYT":
							// 营业厅业务员登录
							BusinessMainController businessMainController = new BusinessMainController(
									logvo.uservo.userID);
							break;
						// 来这里加吧，上面的 uservo通过 logvo.uservo得到
						case "CW":
							try {
								FinanceMainController financeMainController = new FinanceMainController(
										logvo.uservo.userID);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case "admin":
							try {
								UserMainController userMainController = new UserMainController(logvo.uservo.userID);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case "JL":
							try {
								ManageMainController manageMainController = new ManageMainController(
										logvo.uservo.userID);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case "CK":
							try {
								RepertoryMainController repertoryMainController = new RepertoryMainController(
										logvo.uservo.userID);
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

					gap.setBounds(200 - len2 / 2, 130 + 105 * (i - 1) + 25, len2, 25);
					add(gap);
				}
			}

			int l3 = getWidthByNum("返回");
			cancel.setBounds(200 - l3 / 2 - 15, 255 + 105 * (len - 1) - 50, l3 + 30, 30);
		}

		public int getWidthByNum(String str) {
			return 13 * str.length();
		}
	}

	private void warnning(String msg) {
		JOptionPane.showMessageDialog(null, msg, "订单信息错误", JOptionPane.INFORMATION_MESSAGE);
	}

}
