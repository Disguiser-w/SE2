package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MessagePanel extends JPanel {
	private String name;
	private String ID;
	private JLabel changePasswordLabel;
	private JLabel messageLabel;
	private JLabel timeLabel;
	private String time;

	public MessagePanel() {
		name = "盗格森二世";
		ID = "gs-007";
		changePasswordLabel = new JLabel("修改密码");
		messageLabel = new JLabel("消息");
		timeLabel = new JLabel();

		add(changePasswordLabel);
		add(messageLabel);
		add(timeLabel);
		//
		changePasswordLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		changePasswordLabel.setHorizontalAlignment(JLabel.CENTER);
		messageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		timeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		timeLabel.setHorizontalAlignment(JLabel.CENTER);

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
	}

	public void refreshTime() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		time = formatter.format(new Date());
		timeLabel.setText(time);
		timeLabel.repaint();
	}

	public void setBounds(int x, int y, int width, int height) {

		super.setBounds(x, y, width, height);
		changePasswordLabel.setBounds(width / 2, height / 5, width * 1 / 12, height * 3 / 5);
		messageLabel.setBounds(width * 3 / 5, height / 5, width / 15, height * 3 / 5);
		timeLabel.setBounds(width * 4 / 5, height / 5, width * 9 / 50, height * 3 / 5);
	}

	public void setMessage(String name, String ID) {
		this.name = name;
		this.ID = ID;
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.setFont(new Font("WenQuanYi Micro Hei", Font.PLAIN, 15));
		FontMetrics fm = g.getFontMetrics();

		int strHeight = fm.getAscent();
		g.drawString("姓名 : " + name, getWidth() / 20, (getHeight() + strHeight) / 2);
		g.drawString("编号 : " + ID, getWidth() * 6 / 25, (getHeight() + strHeight) / 2);
	}
}