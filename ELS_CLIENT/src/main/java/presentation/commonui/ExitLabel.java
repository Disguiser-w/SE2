package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class ExitLabel extends JLabel {
	public boolean isMouseOn;
	public Image mouseOn;
	public Image normal;

	public ExitLabel() {

		isMouseOn = false;
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				isMouseOn = true;
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				isMouseOn = false;
				repaint();
			}

			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (isMouseOn) {
			if (mouseOn != null)
				g2d.drawImage(mouseOn, 0, 0, null);
			else {
				g2d.setColor(Color.WHITE);
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
			}

		} else {
			if (normal != null)
				g2d.drawImage(normal, 0, 0, null);
			else {
				g2d.setColor(Color.WHITE);
				g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
				g2d.setColor(Color.BLACK);
				g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 5, 5);
			}
		}
	}

}
