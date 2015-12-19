package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.image.ImageGetter;

class MessagePanel extends JPanel {
	// private JLabel changePasswordLabel;
	// private JLabel messageLabel;
	private JLabel exitLabel;
	private JLabel timeLabel;

	private String time;
	private LocationHelper helper;
	private JFrame frame;

	private int oldX;
	private int oldY;
	private boolean isMoving;
	private Image background;

	public MessagePanel(JFrame f) {
		this.frame = f;
		// changePasswordLabel = new JLabel("修改密码");
		// messageLabel = new JLabel("消息");
		exitLabel = new ExitLabel();
		timeLabel = new JLabel();
		oldX = 0;
		oldY = 0;
		isMoving = false;
		background = ImageGetter.getImage("background2.png").getImage();
		// add(changePasswordLabel);
		// add(messageLabel);
		add(exitLabel);
		add(timeLabel);

		// changePasswordLabel.setHorizontalAlignment(JLabel.CENTER);
		// changePasswordLabel.setForeground(Color.WHITE);
		// changePasswordLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN,
		// 15));
		// changePasswordLabel.setBorder(BorderFactory.createSoftBevelBorder(Color.WHITE));

		// messageLabel.setHorizontalAlignment(JLabel.CENTER);
		// messageLabel.setForeground(Color.WHITE);
		// messageLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));

		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));

		setLayout(null);
		(new Thread(new Runnable() {
			public void run() {
				while (true) {
					refreshTime();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		})).start();

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
				isMoving = true;
			}

			public void mouseReleased(MouseEvent e) {
				isMoving = false;
			}

		});

		addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {
				if (!isMoving)
					return;
				int newX = e.getX();
				int newY = e.getY();
				int dx = newX - oldX;
				int dy = newY - oldY;
				frame.setLocation(frame.getX() + dx, frame.getY() + dy);
			}

		});
	}

	public void refreshTime() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		time = formatter.format(new Date());
		timeLabel.setText("当前时间 : " + time);
		timeLabel.repaint();
	}

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		// changePasswordLabel.setBounds(width / 2, height / 5, width * 1 / 12,
		// height * 3 / 5);
		// messageLabel.setBounds(width * 3 / 5, height / 5, width / 15, height
		// * 3 / 5);
		// timeLabel.setBounds(width * 7 / 10, height / 5, width * 11 / 50,
		// height * 3 / 5);
		// changePasswordLabel.setBounds((int) (width * 2.2851296043656206 /
		// 25), (int) (height * 9.024390243902438 / 20),
		// (int) (width * 2.387448840381992 / 25), (int) (height *
		// 8.536585365853659 / 20));
		// messageLabel.setBounds((int) (width * 7.5375170532060025 / 25), (int)
		// (height * 9.024390243902438 / 20),
		// (int) (width * 2.387448840381992 / 25), (int) (height *
		// 8.536585365853659 / 20));
		exitLabel.setBounds((int) (width * 22.78308321964529 / 25), (int) (height * 5.121951219512195 / 20),
				(int) (width * 1.398362892223738 / 25), (int) (height * 9.512195121951219 / 20));
		timeLabel.setBounds((int) (width * 1.5688949522510232 / 25), (int) (height * 5.121951219512195 / 20),
				(int) (width * 8.526603001364256 / 25), (int) (height * 9.75609756097561 / 20));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(102, 102, 102));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// 圆角
		g2d.fillRoundRect(0, 0, getWidth(), getHeight() + 7, 14, 14);
		// g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}
}