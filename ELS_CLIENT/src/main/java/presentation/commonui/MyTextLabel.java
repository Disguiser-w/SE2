package presentation.commonui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class MyTextLabel extends JLabel {
	public MyTextLabel(String text) {
		super(text);
		if (UserFrame.type == UserFrame.TYPE_1) {
			setForeground(Color.WHITE);
		}
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
	}

	public MyTextLabel() {
		this("");
	}

}
