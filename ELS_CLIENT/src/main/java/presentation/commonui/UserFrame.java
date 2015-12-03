package presentation.commonui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 所有Frame继承这个，调用 来添加一个功能及其对应的面板
 */
public class UserFrame extends JFrame {
	// private JLabel panel;
	private static final int DEFAULT_WIDTH = 960;
	private static final int DEFAULT_HEIGHT = 640;
	private JLabel imageLabel;
	private MessagePanel messagePanel;
	private FunctionPanel functionPanel;
	private JPanel operationPanel;
	private HeaderPanel headerPanel;
	private ArrayList<JPanel> operationPanels;

	private File propertiesFile;
	private Properties settings;

	public UserFrame() {

		// panel = new JLabel();
		operationPanels = new ArrayList<JPanel>();
		// panel.setBackground(Color.gray);
		// add(panel);

		imageLabel = new JLabel();
		messagePanel = new MessagePanel();
		functionPanel = new FunctionPanel();
		operationPanel = new JPanel();

		headerPanel = new HeaderPanel(this);

		// 颜色看效果
		headerPanel.setBackground(Color.BLACK);
		imageLabel.setOpaque(true);
		imageLabel.setBackground(Color.BLUE);

		imageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		messagePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		functionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		operationPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		setCmpLocation();

		setLayout(null);

		add(imageLabel);
		add(messagePanel);
		add(functionPanel);
		add(operationPanel);
		add(headerPanel);

		// imageLabel.setBackground(Color.red);
		// messagePanel.setBackground(Color.yellow);
		// functionPanel.setBackground(Color.blue);
		// operationPanel.setBackground(Color.green);

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				setCmpLocation();
			}
		});

		setTitle("ELS");
		setProperties();

		setMinimumSize(new Dimension(700, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
//		setResizable(false);
		setFocusable(false);

	}

	public void showFrame() {

		setVisible(true);
	}

	public void setCmpLocation() {

		int width = getWidth();
		int height = getHeight();

		imageLabel.setBounds(height / 25, height * 3 / 50, height * 4 / 25, height * 4 / 25);
		messagePanel.setBounds(height * 6 / 25, height * 8 / 75, width - height * 7 / 25, height / 15);
		functionPanel.setBounds(height / 25, height * 6 / 25, height * 4 / 25, height * 7 / 10);
		operationPanel.setBounds(height * 6 / 25, height * 6 / 25, width - height * 7 / 25, height * 7 / 10);
		headerPanel.setBounds(0, 0, width, 25);
		
		
	}

	public void setProperties() {
		String userDir = System.getProperty("user.home");

		File propertiesDir = new File(userDir, ".pro");
		if (!propertiesDir.exists())
			propertiesDir.mkdir();
		propertiesFile = new File(propertiesDir, "program.properties");

		Properties defaultSettings = new Properties();
		defaultSettings.put("left", "0");
		defaultSettings.put("top", "0");
		defaultSettings.put("width", "" + DEFAULT_WIDTH);
		defaultSettings.put("height", "" + DEFAULT_HEIGHT);

		settings = new Properties(defaultSettings);

		if (propertiesFile.exists())
			try {
				FileInputStream in = new FileInputStream(propertiesFile);
				settings.load(in);
				in.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		// else
		// try {
		// propertiesFile.createNewFile();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		int left = Integer.parseInt(settings.getProperty("left"));
		int top = Integer.parseInt(settings.getProperty("top"));
		int width = Integer.parseInt(settings.getProperty("width"));
		int height = Integer.parseInt(settings.getProperty("height"));

		setSize(width, height);

		if (left == 0 && top == 0)
			setLocationRelativeTo(null);
		else
			setLocation(left, top);

	}

	public void exitSystem() {
		settings.put("left", "" + getX());
		settings.put("top", "" + getY());
		settings.put("width", "" + getWidth());
		settings.put("height", "" + getHeight());

		try {
			FileOutputStream out = new FileOutputStream(propertiesFile);
			settings.store(out, "Program Properties");
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		System.exit(0);
	}

	// 设置人员信息
	public void setMessage(String name, String ID) {
		messagePanel.setMessage(name, ID);
	}

	public void addFuncLabel(JPanel panel) {
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addFuncLabel(null, null, null, panel);
	}

	// 调用此函数来添加一个功能
	public void addFuncLabel(Image image, Image pressImage, Image rolloverImage, JPanel newPanel) {
		int height = getHeight();
		int width = getWidth();

		newPanel.setBounds(height * 6 / 25, height / 5, width - height * 7 / 25, height * 19 / 25);
		operationPanels.add(newPanel);

		FuncLabel funcLabel = new FuncLabel();
		funcLabel.setPanel(newPanel);

		if (image != null)
			funcLabel.setImage(image);
		if (image != null)
			funcLabel.setPressImage(pressImage);
		if (image != null)
			funcLabel.setRolloverImage(rolloverImage);
		functionPanel.addFuncLabel(funcLabel);
		if (operationPanels.size() == 1) {
			remove(operationPanel);
			operationPanel = operationPanels.get(0);
			add(operationPanel);
			repaint();
		}
		funcLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				remove(operationPanel);
				operationPanel = ((FuncLabel) (e.getSource())).getPanel();
				int height = getHeight();
				int width = getWidth();
				operationPanel.setBounds(height * 6 / 25, height * 6 / 25, width - height * 7 / 25, height * 7 / 10);
	
				add(operationPanel);
				repaint();

			}
		});
	}
}
