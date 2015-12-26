package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class MyLabel extends JLabel {
	protected boolean isPressed;
	protected boolean isMouseOn;
	protected String text;

	protected Image normal;
	protected Image hover;
	protected Image press;

	protected boolean pressLeave;

	public MyLabel(String str) {
		text = str;
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
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
	
	public void setEnable(boolean b){
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();

		int radio = 20;

		if (isPressed) {
			if (press != null)
				g2d.drawImage(press, 0, 0, width, height, this);
			else {
				g2d.setColor(new Color(0, 82, 130));
				g2d.fillRoundRect(0, 0, width, height, radio, radio);
			}
		} else if (isMouseOn) {
			if (hover != null)
				g2d.drawImage(hover, 0, 0, width, height, this);
			else {
				g2d.setColor(new Color(0, 82, 130));
				g2d.fillRoundRect(0, height / 2 - 8, width, height / 2 + 9, radio, radio);
				g2d.setColor(new Color(0, 151, 255));
				g2d.fillRoundRect(0, 0, width, height - 3, radio, radio);
			}

		}

		else {
			if (normal != null)
				g2d.drawImage(normal, 0, 0, width, height, this);
			else {
				g2d.setColor(new Color(0, 82, 130));
				g2d.fillRoundRect(0, height / 2 - 8, width, 9 + height / 2, radio, radio);
				g2d.setColor(new Color(0, 111, 255));
				g2d.fillRoundRect(0, 0, width, height - 3, radio, radio);
			}
		}
		
	}

	public void setImages(Image image1, Image image2, Image image3) {
		normal = image1;
		hover = image2;
		press = image3;
	}

	public void addMoveListener() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressLeave = true;
				setLocation(getX(), getY() + 4);
			}

			public void mouseReleased(MouseEvent e) {
				pressLeave = false;
				if (isMouseOn)
					setLocation(getX(), getY() - 4);
			}

			public void mouseExited(MouseEvent e) {
				// if (!isMouseOn&&isPressed)
				if (pressLeave)
					setLocation(getX(), getY() - 4);

			}

		});
	}
}
