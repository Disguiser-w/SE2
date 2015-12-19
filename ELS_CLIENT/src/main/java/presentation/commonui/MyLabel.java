package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class MyLabel extends JLabel {
	private boolean isPressed;
	private boolean isMouseOn;
	private String text;

	public MyLabel(String str) {
		text = str;

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				isPressed = true;
				repaint();
			}

			public void mouseReleased(MouseEvent e) {
				isPressed = false;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				isMouseOn = true;
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				isPressed = isMouseOn = false;
				repaint();
			}
		});
	}

	public MyLabel() {
		this("");
	}

	public void paintComponent(Graphics g) {

		int width = getWidth();
		int height = getHeight();

		g.setColor(Color.BLACK);
		if (isMouseOn)
			g.drawRoundRect(0, 0, width - 1, height - 1, 10, 10);

		g.fillRoundRect(5, 5, width - 11, height - 11, 10, 10);

		g.setColor(Color.WHITE);
		if (isPressed)

			g.fillRoundRect(10, 10, width - 21, height - 21, 10, 10);
	}
}
