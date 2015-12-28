package presentation.special_ui;

import java.awt.Image;

import common.ImageGetter;
import presentation.commonui.MyLabel;

public class NightLabel extends MyLabel {
	public NightLabel() {
		this("");
	}

	public NightLabel(String str) {
		setText(str);
		Image normal = ImageGetter.getImage("night_0.png").getImage();
		Image hover = ImageGetter.getImage("night_1.png").getImage();
		Image press = ImageGetter.getImage("night_2.png").getImage();

		setImages(normal, hover, press);

	}
}
