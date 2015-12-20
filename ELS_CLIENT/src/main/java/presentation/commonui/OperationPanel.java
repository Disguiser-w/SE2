package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import presentation.image.ImageGetter;

public class OperationPanel extends JPanel {
	private Image background;

	protected OperationPanel() {
		background = ImageGetter.getImage("background4.png").getImage();
	}

	public void add(MyTable table) {
		add(table.getScrollPanel());
	}

	public void remove(MyTable table) {
		remove(table.getScrollPanel());
	}

	public void add(MyTextField field) {
		add(field.getLabel());
	}

	public void remove(MyTextField field) {
		remove(field.getLabel());
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// g2d.setColor(Color.BLACK);
		// g2d.fillRoundRect(0, -5, getWidth(), getHeight() + 5, 10, 10);
		// g2d.setColor(Color.WHITE);
		// g2d.fillRoundRect(2, -3, getWidth() - 4, getHeight() + 1, 10, 10);
		// g2d.setColor(Color.BLACK);
		// g2d.fillRect(0, 0, getWidth(), 2);

		g2d.setColor(new Color(245, 245, 245));
		// 圆角方法
		if (UserFrame.type == UserFrame.TYPE_0)
			g2d.fillRoundRect(0, -7, getWidth(), getHeight() + 7, 14, 14);
		else if (UserFrame.type == UserFrame.TYPE_1)
			g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}

}
