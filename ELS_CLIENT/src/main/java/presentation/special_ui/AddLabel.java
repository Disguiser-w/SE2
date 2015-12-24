package presentation.special_ui;

import java.awt.Graphics;
import java.awt.Image;

import common.ImageGetter;
import presentation.commonui.MyLabel;
import presentation.commonui.UserFrame;

public class AddLabel extends MyLabel {
	private Image new_1;
	private Image new_0;
	private Image new_3;
	private Image new_2;

	public AddLabel() {
		this("");
	}

	public AddLabel(String str) {
		setText(str);

		new_1 = ImageGetter.getImage("new_1.png").getImage();
		new_0 = ImageGetter.getImage("new_0.png").getImage();
		new_3 = ImageGetter.getImage("new_3.png").getImage();
		new_2 = ImageGetter.getImage("new_2.png").getImage();
	}

	public void paintComponent(Graphics g) {
		if (UserFrame.type == UserFrame.TYPE_0) {

			setImages(new_3, new_2, new_2);
		} else {
			setImages(new_1, new_0, new_0);

		}
		super.paintComponent(g);
	}
}
