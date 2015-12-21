package presentation.special_ui;

import java.awt.Image;

import common.ImageGetter;
import presentation.commonui.MyLabel;

public class ModifyLabel extends MyLabel {
	public ModifyLabel() {
		this("");
	}

	public ModifyLabel(String str) {
		setText(str);
		Image normal = ImageGetter.getImage("modify_0.png").getImage();
		Image hover = ImageGetter.getImage("modify_1.png").getImage();
		Image press = ImageGetter.getImage("modify_2.png").getImage();

		setImages(normal, hover, press);
		
		addMoveListener();
	}

}
