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

		if (isPressed) {
			g.setColor(new Color(0, 82, 130));
			g.fillRoundRect(0, 0, width, height, 8, 8);
		} else if (isMouseOn) {
			g.setColor(new Color(0, 82, 130));
			g.fillRoundRect(0, height - 8, width, 8, 8, 8);
			g.setColor(new Color(0, 151, 255));
			g.fillRoundRect(0, 0, width, height - 3, 8, 8);

		}

		else {

			g.setColor(new Color(0, 82, 130));
			g.fillRoundRect(0, height - 8, width, 8, 8, 8);
			g.setColor(new Color(0, 111, 255));
			g.fillRoundRect(0, 0, width, height - 3, 8, 8);
		}

	}
}
