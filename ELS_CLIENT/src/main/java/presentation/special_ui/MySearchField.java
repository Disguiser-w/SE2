package presentation.special_ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import common.ImageGetter;
import presentation.commonui.MyTextField;
import presentation.commonui.UserFrame;

public class MySearchField extends MyTextField {
	private JLabel imageLabel;
	private ImageIcon normal;
	private ImageIcon hover;

	private ImageIcon normal_night;
	private ImageIcon hover_night;

	private boolean isMouseOn;

	public MySearchField() {
		this("");
	}

	public MySearchField(String str) {
		super(str);

		imageLabel = new JLabel();
		getLabel().add(imageLabel);

		normal = ImageGetter.getImage("find_3.png");
		hover = ImageGetter.getImage("find_2.png");

		normal_night = ImageGetter.getImage("find_1.png");
		hover_night = ImageGetter.getImage("find_0.png");

		imageLabel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				isMouseOn = true;
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				isMouseOn = false;
				repaint();

			}

		});

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width + height, height);

		imageLabel.setBounds(1 + width - height / 4, 2, height - 4, height - 4);
		normal.setImage(normal.getImage().getScaledInstance(height, height, Image.SCALE_DEFAULT));
		hover.setImage(hover.getImage().getScaledInstance(height, height, Image.SCALE_DEFAULT));

		normal_night.setImage(normal_night.getImage().getScaledInstance(height, height, Image.SCALE_DEFAULT));
		hover_night.setImage(hover_night.getImage().getScaledInstance(height, height, Image.SCALE_DEFAULT));

		super.reSetBounds(width - height + 2, height - 4);
	}

	public void addMouseListener(MouseAdapter m) {
		imageLabel.addMouseListener(m);
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}

	public void paintComponent(Graphics g) {
		if (isMouseOn)
			if (UserFrame.type == UserFrame.TYPE_0)
				imageLabel.setIcon(hover);
			else {
				imageLabel.setIcon(hover_night);
			}
		else if (UserFrame.type == UserFrame.TYPE_0)
			imageLabel.setIcon(normal);
		else {
			imageLabel.setIcon(normal_night);
		}

	}
}
