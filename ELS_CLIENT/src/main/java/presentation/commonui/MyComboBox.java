package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class MyComboBox<T> extends JComboBox<T> {
	public MyComboBox() {
		setMaximumRowCount(100);
		setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {

		if (UserFrame.type == UserFrame.TYPE_0) {
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);

		} else {
			setBackground(new Color(0, 0, 0, 0));
			setForeground(Color.WHITE);
			setBorder(BorderFactory.createLineBorder(Color.WHITE));
		}
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		super.paintComponent(g);
	}

}
