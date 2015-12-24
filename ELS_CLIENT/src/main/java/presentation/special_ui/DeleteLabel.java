package presentation.special_ui;

import java.awt.Graphics;
import java.awt.Image;

import common.ImageGetter;
import presentation.commonui.MyLabel;
import presentation.commonui.UserFrame;

public class DeleteLabel extends MyLabel {
	private Image delete_1;
	private Image delete_0;
	private Image delete_3;
	private Image delete_2;

	public DeleteLabel() {
		this("");
	}

	public DeleteLabel(String str) {
		setText(str);

		delete_1 = ImageGetter.getImage("delete_1.png").getImage();
		delete_0 = ImageGetter.getImage("delete_0.png").getImage();
		delete_3 = ImageGetter.getImage("delete_3.png").getImage();
		delete_2 = ImageGetter.getImage("delete_2.png").getImage();
//		setImages(delete_1, delete_0, delete_0);
	}

	public void paintComponent(Graphics g) {
		if (UserFrame.type == UserFrame.TYPE_0) {

			setImages(delete_3, delete_2, delete_2);
		} else {
			setImages(delete_1, delete_0, delete_0);

		}
		super.paintComponent(g);
	}

}
