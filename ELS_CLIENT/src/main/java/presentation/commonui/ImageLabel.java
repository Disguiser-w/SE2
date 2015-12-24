package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import common.ImageGetter;
import presentation.special_ui.DayLabel;
import presentation.special_ui.NightLabel;

public class ImageLabel extends JLabel {

	private JLabel day;
	private JLabel night;

	private String[] nameAndId;
	private Image background;
	private Image image;
	private UserFrame frame;

	public ImageLabel(String[] str, UserFrame f) {
		frame = f;
		background = ImageGetter.getImage("background1.png").getImage();

		day = new DayLabel();
		night = new NightLabel();
		this.nameAndId = str;
		String head = str[1].split("-")[0];

		image = ImageGetter.getImage(head + ".png").getImage();

		MouseListener l = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (UserFrame.type == UserFrame.TYPE_0) {
					UserFrame.type = UserFrame.TYPE_1;
					remove(night);
					add(day);
					frame.review();
				} else {
					UserFrame.type = UserFrame.TYPE_0;
					remove(day);
					add(night);
					frame.review();
				}
			}
		};

		day.addMouseListener(l);
		night.addMouseListener(l);
		add(night);

		int width = getWidth();
		int height = getHeight();

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		day.setBounds((int) (width * 21.475770925110133 / 25),
				(int) (height * 2.4390243902439024 / 20),
				(int) (width * 2.4229074889867843 / 25),
				(int) (height * 5.121951219512195 / 20));

		night.setBounds((int) (width * 21.475770925110133 / 25),
				(int) (height * 2.4390243902439024 / 20),
				(int) (width * 2.4229074889867843 / 25),
				(int) (height * 5.121951219512195 / 20));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(50, 50, 50));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// 这句是圆角效果
		if (UserFrame.type == UserFrame.TYPE_0)
			g2d.fillRoundRect(0, 0, getWidth(), getHeight() + 7, 14, 14);
		else if (UserFrame.type == UserFrame.TYPE_1)
			g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		g2d.setColor(Color.WHITE);

		int width = getWidth();
		int height = getHeight();
		g2d.drawRect(height / 4, height / 4, height / 2, height / 2);
		if (image != null)
			g2d.drawImage(image, height / 4 + 1, height / 4 + 1,
					height / 2 - 1, height / 2 - 1, null);

		g2d.drawString("姓名: " + nameAndId[0], width * 2 / 7 + width / 10,
				height * 1 / 3 + 4);
		g2d.drawString("编号: " + nameAndId[1], width * 2 / 7 + width / 10,
				height * 2 / 3 + 4);

	}

}
