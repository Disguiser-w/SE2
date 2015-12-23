package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class MyTextLabel extends JLabel {
	public MyTextLabel(String text) {
		super(text);
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
	}

	public MyTextLabel() {
		this("");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (UserFrame.type == UserFrame.TYPE_0) {
			setForeground(Color.BLACK);
		} else
			setForeground(Color.WHITE);
		

	}

}
