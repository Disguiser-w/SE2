package presentation.special_ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import common.ImageGetter;

public class KeyWordField extends JPasswordField {
	private JLabel label;

	public KeyWordField() {
		label = new Label1();
		label.add(this);
		setBackground(Color.BLACK);
	}

	public JLabel getLabel() {
		return label;
	}

}

class Label1 extends JLabel {
	private Image image;

	public Label1() {
		image = ImageGetter.getImage("keywords.png").getImage();
		setBackground(Color.BLACK);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}
