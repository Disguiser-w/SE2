package presentation.special_ui;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import common.ImageGetter;
import presentation.commonui.MyTextField;

public class MySearchField extends MyTextField {
	private JLabel imageLabel;
	private ImageIcon normal;
	private ImageIcon hover;
	private ImageIcon press;

	private boolean isPressed;

	public MySearchField() {
		this("");
	}

	public MySearchField(String str) {
		super(str);

		imageLabel = new JLabel();
		getLabel().add(imageLabel);
		normal = ImageGetter.getImage("mirror_0.png");
		hover = ImageGetter.getImage("mirror_1.png");
		press = ImageGetter.getImage("mirror_2.png");

		imageLabel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (!isPressed) {
					imageLabel.setIcon(hover);
				}

			}

			public void mouseExited(MouseEvent e) {
				imageLabel.setIcon(normal);
				imageLabel.setLocation(imageLabel.getX(), getY() - 1);
			}

			public void mousePressed(MouseEvent e) {
				isPressed = true;
				imageLabel.setIcon(press);
				imageLabel.setLocation(imageLabel.getX(), getY() + 1);
			}

			public void mouseReleased(MouseEvent e) {
				isPressed = false;
				imageLabel.setIcon(hover);
				imageLabel.setLocation(imageLabel.getX(), getY() - 1);
			}
		});

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width + height, height);

		imageLabel.setBounds(1 + width - height / 4, 2, height - 4, height - 4);

		normal.setImage(normal.getImage().getScaledInstance(height - 4, height - 4, Image.SCALE_DEFAULT));
		hover.setImage(hover.getImage().getScaledInstance(height - 4, height - 4, Image.SCALE_DEFAULT));
		press.setImage(press.getImage().getScaledInstance(height - 4, height - 4, Image.SCALE_DEFAULT));

		imageLabel.setIcon(normal);

		super.reSetBounds(width - height + 2, height - 4);
	}

	public void addMouseListener(MouseAdapter m) {
		imageLabel.addMouseListener(m);
	}

	public void setImage(ImageIcon image1, ImageIcon image2, ImageIcon image3) {
		normal = image1;
		hover = image2;
		press = image3;
	}

	public JLabel getImageLabel() {
		return imageLabel;
	}
}
