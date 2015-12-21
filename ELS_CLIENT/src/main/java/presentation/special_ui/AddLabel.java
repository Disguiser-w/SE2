package presentation.special_ui;

import java.awt.Image;

import common.ImageGetter;
import presentation.commonui.MyLabel;

public class AddLabel extends MyLabel {
	public AddLabel() {
		this("");
	}

	public AddLabel(String str) {
		setText(str);
		Image normal = ImageGetter.getImage("add_0.png").getImage();
		Image hover = ImageGetter.getImage("add_1.png").getImage();
		Image press = ImageGetter.getImage("add_2.png").getImage();

		setImages(normal, hover, press);
		
		addMoveListener();
	}

}
