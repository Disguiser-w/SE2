package presentation.special_ui;

import java.awt.Image;

import presentation.commonui.MyLabel;
import presentation.image.ImageGetter;

public class DeleteLabel extends MyLabel {
	public DeleteLabel() {
		this("");
	}

	public DeleteLabel(String str) {
		setText(str);
		Image normal = ImageGetter.getImage("delete_0.png").getImage();
		Image hover = ImageGetter.getImage("delete_1.png").getImage();
		Image press = ImageGetter.getImage("delete_2.png").getImage();

		setImages(normal, hover, press);

		addMoveListener();
	}

}
