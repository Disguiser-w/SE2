package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 所有Frame继承这个，调用 来添加一个功能及其对应的面板
 */
public class UserFrame extends JFrame {
	private JPanel panel;

	private JLabel imageLabel;
	private MessagePanel messagePanel;
	private FunctionPanel functionPanel;
	private JPanel operationPanel;

	private ArrayList<JPanel> operationPanels;

	public UserFrame() {
		panel = new JPanel();
		operationPanels = new ArrayList<JPanel>();
		// panel.setBackground(Color.gray);
		add(panel);

		imageLabel = new JLabel();
		messagePanel = new MessagePanel();
		functionPanel = new FunctionPanel();
		operationPanel = new JPanel();

		setCmpLocation();

		panel.setLayout(null);

		panel.add(imageLabel);
		panel.add(messagePanel);
		panel.add(functionPanel);
		panel.add(operationPanel);

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
		setSize(960, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

	}

	public void showFrame() {

		setVisible(true);
	}

	public void setCmpLocation() {
		int width = getWidth();
		int height = getHeight();

		imageLabel.setBounds(0, 0, height / 5, height / 5);
		messagePanel.setBounds(height / 5, height / 15, width - height * 6 / 25, height / 15);
		functionPanel.setBounds(height / 25, height / 5, height * 4 / 25, height * 19 / 25);
		operationPanel.setBounds(height * 6 / 25, height / 5, width - height * 7 / 25, height * 19 / 25);

	}

	// test
	public static void main(String[] args) {
		new UserFrame();
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
			panel.remove(operationPanel);
			operationPanel = operationPanels.get(0);
			panel.add(operationPanel);
			panel.repaint();
		}
		funcLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panel.remove(operationPanel);
				operationPanel = ((FuncLabel) (e.getSource())).getPanel();
				panel.add(operationPanel);
				panel.repaint();
			}
		});
	}
}

class MessagePanel extends JPanel {
	private String name;
	private String ID;
	private JButton changePasswordButton;

	public MessagePanel() {
		name = "";
		ID = "";
		changePasswordButton = new JButton("修改密码");
		setLayout(null);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		changePasswordButton.setBounds(width / 3, height / 5, height * 6 / 5, height * 3 / 5);
	}

	public void setMessage(String name, String ID) {
		this.name = name;
		this.ID = ID;
	}

	public void paintComponent(Graphics g) {
		g.setFont(new Font("", Font.BOLD, 15));
		FontMetrics fm = g.getFontMetrics();

		int strHeight = fm.getHeight();
		g.drawString("姓名:" + name, getWidth() / 10, (getHeight() + strHeight) / 2);
		g.drawString("编号:" + name, getWidth() * 2 / 5, (getHeight() + strHeight) / 2);
	}

}
