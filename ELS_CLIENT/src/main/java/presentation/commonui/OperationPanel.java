package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class OperationPanel extends JPanel {
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// g2d.setColor(Color.BLACK);
		// g2d.fillRoundRect(0, -5, getWidth(), getHeight() + 5, 10, 10);
		// g2d.setColor(Color.WHITE);
		// g2d.fillRoundRect(2, -3, getWidth() - 4, getHeight() + 1, 10, 10);
		// g2d.setColor(Color.BLACK);
		// g2d.fillRect(0, 0, getWidth(), 2);
		
		
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(0, -7, getWidth(), getHeight() + 7, 14, 14);

	}

}
