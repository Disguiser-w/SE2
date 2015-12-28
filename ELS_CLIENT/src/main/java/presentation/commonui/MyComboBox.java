package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComboBox;

public class MyComboBox<T> extends JComboBox<T> {
	public MyComboBox() {
		setMaximumRowCount(100);
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
	}

	public void paintComponent(Graphics g) {


		super.paintComponent(g);
		if (UserFrame.type == UserFrame.TYPE_0) {
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
			
		} else {
			setBackground(Color.BLACK);
			setForeground(Color.WHITE);
			
		}

	}
	


}
