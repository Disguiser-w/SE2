package presentation.commonui;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Images {
	private static String path = "../image/";

	public static Image getImageByName(String name) {
		Image image = (new ImageIcon(Images.class.getResource(path + name))).getImage();
		return image;
	}

}
