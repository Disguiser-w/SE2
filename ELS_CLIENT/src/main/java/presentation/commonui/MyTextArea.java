package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextArea;

public class MyTextArea extends JTextArea {

	public MyTextArea() {
//		setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190, 125)));
		setBackground(new Color(150, 150, 150, 75));
	}

	public void paintComponent(Graphics g) {
		if (UserFrame.type == UserFrame.TYPE_0) {
			setForeground(Color.BLACK);
		} else if (UserFrame.type == UserFrame.TYPE_1) {
			setForeground(Color.WHITE);
		}
		super.paintComponent(g);
	}
}
