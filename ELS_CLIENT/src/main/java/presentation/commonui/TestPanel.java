package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.image.ImageGetter;

class TestPanel extends JPanel {
	private ImageLabel imageLabel;
	private MessagePanel messagePanel;
	private FunctionPanel functionPanel;
	private JPanel operationPanel;
	private LocationHelper helper;

	public TestPanel() {
//		setCursor(
//				Toolkit.getDefaultToolkit().createCustomCursor(ImageGetter.getImage("").getImage(), new Point(), null));
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(960, 640);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		setBackground(new Color(0, 0, 0, 0));
		frame.setBackground(new Color(0, 0, 0, 0));

		imageLabel = new ImageLabel(new String[] { "狗剩", "KD123456" });

		imageLabel.setText("张家胜");
		imageLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		imageLabel.setForeground(Color.WHITE);

		imageLabel.setVerticalAlignment(JLabel.BOTTOM);
		messagePanel = new MessagePanel(frame);

		functionPanel = new FunctionPanel();
		operationPanel = new JPanel();
		operationPanel.setBackground(new Color(240, 240, 240));

		setLayout(null);
		add(imageLabel);
		add(messagePanel);
		add(functionPanel);
		add(operationPanel);

		int width = 960;
		int height = 640;
		// helper = new LocationHelper(this);

		imageLabel.setBounds((int) (width * 0.0 / 25), (int) (height * 0.0 / 20),
				(int) (width * 5.911458333333333 / 25), (int) (height * 2.5625 / 20));
		messagePanel.setBounds((int) (width * 5.911458333333333 / 25), (int) (height * 0.0 / 20),
				(int) (width * 19.088541666666668 / 25), (int) (height * 2.5625 / 20));
		functionPanel.setBounds((int) (width * 0.0 / 25), (int) (height * 2.5625 / 20),
				(int) (width * 5.911458333333333 / 25), (int) (height * 17.4375 / 20));
		operationPanel.setBounds((int) (width * 5.911458333333333 / 25), (int) (height * 2.5625 / 20),
				(int) (width * 19.088541666666668 / 25), (int) (height * 17.4375 / 20));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		TestPanel panel = new TestPanel();
	}
}