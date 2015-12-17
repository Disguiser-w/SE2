package presentation.commonui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentation.image.ImageGetter;

public class MyTextField extends JTextField {
	private Image image;

	public MyTextField() {
		image = ImageGetter.getImage("textField.png").getImage();
		setOpaque(true);
		setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.drawImage(image, 0, 0, width, height, this);
	}

//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.
//	}
}
