package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MyTextField extends JTextField {
	// private Image image;
	protected JLabel label;
	protected MyObservable observable;

	public MyTextField(String str) {
		setText(str);
		// super(str);
		observable = new MyObservable();
		label = new BackLabel();
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

	}

	public MyTextField() {
		this("");
	}

	public void setText(String str) {
		super.setText(str);

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

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (!getText().equals("") && !isEditable())
			setToolTipText(getText());
		int width = getWidth();
		int height = getHeight();
		if (UserFrame.type == UserFrame.TYPE_0) {
			setForeground(Color.BLACK);
			setBackground(Color.WHITE);
			setCaretColor(Color.BLACK);
		} else {
			setForeground(Color.WHITE);
			setBackground(Color.BLACK);
			setCaretColor(Color.WHITE);

		}

		super.paintComponent(g2d);
	}

	class BackLabel extends JLabel {
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;

			int width = getWidth();
			int height = getHeight();

			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			if (UserFrame.type == UserFrame.TYPE_0) {
				g2d.setColor(Color.WHITE);
				g2d.fillRoundRect(1, 1, width - 3, height - 3, height - 2, height - 2);
				g2d.setColor(new Color(100, 100, 100));
				g2d.drawRoundRect(0, 0, width - 1, height - 1, height, height);
				g2d.drawRoundRect(1, 1, width - 3, height - 3, height - 2, height - 2);

			} else if (UserFrame.type == UserFrame.TYPE_1) {
				g2d.setColor(Color.BLACK);
				g2d.fillRoundRect(1, 1, width - 3, height - 3, height - 2, height - 2);
				g2d.setColor(new Color(200, 200, 200));
				g2d.drawRoundRect(0, 0, width - 1, height - 1, height, height);
				g2d.drawRoundRect(1, 1, width - 3, height - 3, height - 2, height - 2);
			}

		}
	}

	public JLabel getLabel() {
		return label;
	}

	class MyObservable extends Observable {
		public void setData() {
			setChanged();
			notifyObservers(new int[] { label.getX(), label.getY(), label.getWidth(), label.getHeight() });
		}
	}

	public void addObservable(Object o) {
		observable.addObserver((Observer) o);
	}
}
