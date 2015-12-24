package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MyTextField extends JTextField {
	// private Image image;
	protected JLabel label;

	public MyTextField(String str) {
		setText(str);
		label = new BackLabel();
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

	}

	public MyTextField() {
		this("");
	}

	public void setBounds(int x, int y, int width, int height) {
		label.setBounds(x, y, width, height);
		label.setLayout(null);
		super.setBounds(height / 2 - 1, 2, width - height + 2, height - 4);
		label.add(this);
	}

	public void reSetBounds(int width, int height) {
		super.setBounds(getX(), getY(), width, height);
	}

	public void paintComponent(Graphics g) {
		if (!getText().equals("") && !isEditable())
			setToolTipText(getText());
		int width = getWidth();
		int height = getHeight();
		if (UserFrame.type == UserFrame.TYPE_1) {
			setForeground(Color.WHITE);
			setCaretColor(Color.WHITE);
		} else {
			setForeground(Color.BLACK);
			setCaretColor(Color.BLACK);
		}
		if (UserFrame.type == UserFrame.TYPE_0) {
			if (isEditable()) {
				g.setColor(Color.WHITE);
			} else
				g.setColor(new Color(238, 238, 238));

			g.fillRect(0, 0, getWidth(), getHeight());
		} else if (UserFrame.type == UserFrame.TYPE_1) {
			setBackground(new Color(0, 0, 0, 0));
			setForeground(Color.WHITE);
		}
		super.paintComponent(g);
	}

	class BackLabel extends JLabel {
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;

			int width = getWidth();
			int height = getHeight();

			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (UserFrame.type == UserFrame.TYPE_0) {
				g2d.setColor(new Color(100, 100, 100));
				g2d.drawRoundRect(0, 0, width - 1, height - 1, height, height);
				g2d.drawRoundRect(1, 1, width - 3, height - 3, height - 2, height - 2);
			} else if (UserFrame.type == UserFrame.TYPE_1) {
				g2d.setColor(new Color(150, 150, 150, 125));
				g2d.fillRoundRect(0, 0, width - 1, height - 1, height, height);
			}

			if (UserFrame.type == UserFrame.TYPE_0) {
				if (isEditable()) {
					g2d.setColor(Color.WHITE);
				} else
					g2d.setColor(new Color(238, 238, 238));
				g2d.fillRoundRect(2, 2, width - 5, height - 5, height - 4, height - 4);

			}
		}
	}

	public JLabel getLabel() {
		return label;
	}
}
