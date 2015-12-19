package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JLabel;

import presentation.image.ImageGetter;

public class ImageLabel extends JLabel {

	private String[] nameAndId;
	private Image background;
	private Image image;

	public ImageLabel(String[] str) {
		background = ImageGetter.getImage("background1.png").getImage();
		this.nameAndId = str;
	}

	public void setImage(Image image) {
		this.image = image;
		repaint();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(50, 50, 50));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// 这句是圆角效果
		g2d.fillRoundRect(0, 0, getWidth(), getHeight() + 7, 14, 14);
		// g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		g2d.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		g2d.setColor(Color.WHITE);

		int width = getWidth();
		int height = getHeight();
		g2d.drawRoundRect(height / 4, height / 4, height / 2, height / 2, 6, 6);
		g2d.drawString("姓名: " + nameAndId[0], width * 2 / 7 + width / 10, height * 1 / 3 + 4);
		g2d.drawString("编号: " + nameAndId[1], width * 2 / 7 + width / 10, height * 2 / 3 + 4);

	}

}
