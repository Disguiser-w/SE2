package presentation.commonui;

import java.awt.Color;

import javax.swing.JLabel;

public class MyTextLabel extends JLabel {
	public MyTextLabel(String text) {
		super(text);
		if (UserFrame.type == UserFrame.TYPE_1) {
			setForeground(Color.WHITE);
		}
	}

	public MyTextLabel() {
		this("");
	}

}
