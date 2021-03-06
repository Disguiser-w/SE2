package presentation.commonui;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import common.FileGetter;
import testConnection.TestConnection;

/**
 * 所有Frame继承这个，调用 来添加一个功能及其对应的面板
 */
public class UserFrame extends JFrame {
	public static final int TYPE_0 = 0;
	public static final int TYPE_1 = 1;
	public static int type = TYPE_0;
	// private JLabel panel;
	private boolean isMoving;

	private ImageLabel imageLabel;
	private MessagePanel messagePanel;
	private FunctionPanel functionPanel;
	private JPanel operationPanel;
	private ArrayList<JPanel> operationPanels;
	private int num;

	public static final int DEFAULT_WIDTH = 960;
	public static final int DEFAULT_HEIGHT = 640;

	public UserFrame(String name, String ID) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isMoving = false;
		num = 0;
		operationPanels = new ArrayList<JPanel>();
		imageLabel = new ImageLabel(new String[] { name, ID }, this);
		messagePanel = new MessagePanel(this);
		functionPanel = new FunctionPanel();
		operationPanel = new JPanel();

		setCmpLocation();
		// initGlobalFontSetting();
		setLayout(null);

		add(imageLabel);
		add(messagePanel);
		add(functionPanel);
		add(operationPanel);

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				setCmpLocation();
			}
		});

		setSize(960, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setFocusable(false);
		setResizable(false);
	}

	public void showFrame() {
		add(operationPanels.get(0));
		functionPanel.setNowLabel(0);
		setVisible(true);
	}

	public void setCmpLocation() {

		int width = getWidth();
		int height = getHeight();

		imageLabel.setBounds((int) (width * 0.0 / 25), (int) (height * 0.0 / 20),
				(int) (width * 5.911458333333333 / 25), (int) (height * 2.5625 / 20));
		messagePanel.setBounds((int) (width * 5.911458333333333 / 25), (int) (height * 0.0 / 20),
				(int) (width * 19.088541666666668 / 25), (int) (height * 2.5625 / 20));
		functionPanel.setBounds((int) (width * 0.0 / 25), (int) (height * 2.5625 / 20),
				(int) (width * 5.911458333333333 / 25), (int) (height * 17.4375 / 20));
		operationPanel.setBounds((int) (width * 5.911458333333333 / 25), (int) (height * 2.5625 / 20),
				(int) (width * 19.088541666666668 / 25), (int) (height * 17.4375 / 20));

		// 227 82
		// 733 82
		// 227 558
		// 733 558
	}

	// 设置人员信息

	public void addFuncLabel(JPanel panel, String str) {
		addFuncLabel(panel, str, null);
	}

	// 调用此函数来添加一个功能
	public void addFuncLabel(JPanel newPanel, String str, Image image) {
		int height = getHeight();
		int width = getWidth();

		newPanel.setBounds((int) (width * 5.911458333333333 / 25), (int) (height * 2.5625 / 20),
				(int) (width * 19.088541666666668 / 25), (int) (height * 17.4375 / 20));
		operationPanels.add(newPanel);

		FuncLabel funcLabel = new FuncLabel(str, image);
		funcLabel.setPanel(newPanel);

		functionPanel.addFuncLabel(funcLabel);

		if (operationPanels.size() == 1) {
			remove(operationPanel);
			operationPanel = operationPanels.get(0);
			add(operationPanel);
			repaint();
		}

		operationPanel.setBounds((int) (width * 5.911458333333333 / 25), (int) (height * 2.5625 / 20),
				(int) (width * 19.088541666666668 / 25), (int) (height * 17.4375 / 20));
		funcLabel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				FuncLabel label = (FuncLabel) (e.getSource());
				if (operationPanel == label.getPanel())
					return;

				remove(operationPanel);
				operationPanel = label.getPanel();
				num = operationPanels.indexOf(operationPanel);
				functionPanel.setNowLabel(num);

				add(operationPanel);
				operationPanel.repaint();
				repaint();
				setVisible(true);
			}
		});
	}

	public void changePanel(JPanel newPanel) {
		remove(operationPanel);
		operationPanel = newPanel;
		int width = getWidth();
		int height = getHeight();
		operationPanel.setBounds((int) (width * 5.911458333333333 / 25), (int) (height * 2.5625 / 20),
				(int) (width * 19.088541666666668 / 25), (int) (height * 17.4375 / 20));
		add(operationPanel);
		setVisible(true);
		operationPanel.repaint();
		repaint();
		setVisible(true);
	}

	public void toMainPanel() {
		remove(operationPanel);
		operationPanel = operationPanels.get(num);
		add(operationPanel);
		repaint();
		setVisible(true);
	}

	public void review() {
		imageLabel.repaint();
		messagePanel.repaint();
		functionPanel.repaint();
		for (JPanel i : operationPanels)
			i.repaint();
		operationPanel.repaint();
		repaint();
		setVisible(true);

	}

}