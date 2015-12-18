package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class MyCheckBox extends JPanel {
	private boolean isSelected;
	private boolean isPressed;
	private boolean isMouseOn;

	public MyCheckBox() {
		isSelected = false;
		isPressed = false;
		isMouseOn = false;

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isPressed = true;
				repaint();
			}

			public void mouseReleased(MouseEvent e) {
				if (isMouseOn) {
					if (isSelected)
						isSelected = false;
					else
						isSelected = true;
					isPressed = false;
					repaint();
				}
			}

			public void mouseEntered(MouseEvent e) {
				isMouseOn = true;
			}

			public void mouseExited(MouseEvent e) {
				isMouseOn = false;
				isPressed = false;
				repaint();
			}
		});

	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		repaint();
	}

	public boolean getSelected() {
		return isSelected;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		g2d.setColor(new Color(0, 134, 255));
		g2d.fillOval(1, 1, width - 2, height - 2);
		g2d.setColor(Color.white);
		g2d.fillOval(2, 2, width - 4, height - 4);

		g2d.setColor(new Color(0, 134, 255));
		g2d.fillOval(width / 4, height / 4, width / 2, height / 2);
		g2d.setColor(Color.WHITE);
		if (isPressed)
			g2d.fillOval(width / 4 + 2, height / 4 + 2, width / 2 - 4, height / 2 - 4);
		else if (!isSelected)
			g2d.fillOval(width / 4, height / 4, width / 2, height / 2);

		// if (isSelected) {
		// if (isPressed)
		// ;//
		// else if (isMouseOn)
		// ;//
		// else
		// ;//
		// } else if (isPressed)
		// ;//
		// else if (isMouseOn)
		// ;//
		// else
		// ;//

	}

}