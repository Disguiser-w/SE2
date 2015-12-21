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
import javax.swing.JPanel;

public class FuncLabel extends JLabel {
	private JPanel panel;
	private Image image;
	private String name;

	private Image pressImage;

	private boolean isChoosed;
	private boolean isMouseOn;

	public FuncLabel(String str) {
		name = str;

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isChoosed = true;
				repaint();
			}

			public void mouseEntered(MouseEvent e) {
				if (!isChoosed) {
					isMouseOn = true;
					repaint();
				}
			}

			public void mouseExited(MouseEvent e) {
				if (!isChoosed) {
					isMouseOn = false;
					repaint();
				}
			}

		});

	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void paintComponent(Graphics g) {
		int height = getHeight();
		int width = getWidth();
		// 0,82,130
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (UserFrame.type == UserFrame.TYPE_0)
			if (isChoosed) {
				g2d.setColor(new Color(0, 82, 130));
			} else if (isMouseOn) {
				g2d.setColor(new Color(0, 111, 192));
			} else {
				g2d.setColor(new Color(0, 121, 255));
			}
		else if (UserFrame.type == UserFrame.TYPE_1)

			if (isChoosed) {
				g2d.setColor(new Color(0, 0, 0, 150));
			} else if (isMouseOn) {
				g2d.setColor(new Color(0, 0, 0, 100));
			} else {
				g2d.setColor(new Color(0, 0, 0, 0));
			}

		g2d.fillRect(0, 0, width, height);

		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		g2d.drawString(name, width * 2 / 5, height * 2 / 3 - 4);

		//
		g2d.drawRoundRect(height / 2, height / 4, height / 2, height / 2, 6, 6);

	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setChoosed(boolean c) {
		isChoosed = c;
		isMouseOn = false;
		repaint();
	}

	public void setChoosed() {
		isChoosed = true;
		repaint();
	}

}
