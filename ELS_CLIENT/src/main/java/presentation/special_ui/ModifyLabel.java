package presentation.special_ui;

import java.awt.Graphics;
import java.awt.Image;

import common.ImageGetter;
import presentation.commonui.MyLabel;
import presentation.commonui.UserFrame;

public class ModifyLabel extends MyLabel {
	private Image modify_1;
	private Image modify_0;
	private Image modify_4;
	private Image modify_3;

	public ModifyLabel() {
		this("");
	}

	public ModifyLabel(String str) {
		setText(str);

		modify_1 = ImageGetter.getImage("modify_1.png").getImage();
		modify_0 = ImageGetter.getImage("modify_0.png").getImage();
		modify_4 = ImageGetter.getImage("modify_4.png").getImage();
		modify_3 = ImageGetter.getImage("modify_3.png").getImage();
//		setImages(modify_1, modify_0, modify_0);
	}

	public void paintComponent(Graphics g) {
		if (UserFrame.type == UserFrame.TYPE_0) {

			setImages(modify_4, modify_3, modify_3);
		} else {
			setImages(modify_1, modify_0, modify_0);

		}
		super.paintComponent(g);
	}
}
