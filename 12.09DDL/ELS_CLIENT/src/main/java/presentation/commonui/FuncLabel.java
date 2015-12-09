package presentation.commonui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FuncLabel extends JLabel {
	private Image image;
	private Image pressImage;
	private Image rolloverImage;

	private boolean isPressed;
	private boolean isMouseOn;
	private JPanel panel;

	public FuncLabel() {

		image = pressImage = rolloverImage = Images.getImageByName("func.png");

		addMouseListener(new MouseAdapter() {
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
			}

			public void mouseExited(MouseEvent e) {
				isMouseOn = false;
			}
		});

	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setPressImage(Image pressImage) {
		this.pressImage = pressImage;
	}

	public void setRolloverImage(Image rolloverImage) {
		this.rolloverImage = rolloverImage;
	}

	public void paintComponent(Graphics g) {
		int height = getHeight();
		int width = getWidth();

		if (isPressed)
			g.drawImage(pressImage, 0, 0, width, height, null);
		else if (isMouseOn)
			g.drawImage(rolloverImage, 0, 0, width, height, null);
		else
			g.drawImage(image, 0, 0, width, height, null);

	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JPanel getPanel() {
		return panel;
	}

}
