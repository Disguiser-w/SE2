package presentation.commonui;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Images {
	public static Image getFuncImage() {
		Image image = (new ImageIcon(Images.class.getResource("../image/func.png"))).getImage();
		return image;
	}
}
