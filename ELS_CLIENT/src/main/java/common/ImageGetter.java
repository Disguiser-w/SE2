package common;

import javax.swing.ImageIcon;

public class ImageGetter {
	public static ImageIcon getImage(String path) {

		return new ImageIcon(ImageGetter.class.getResource("image/" + path));
	}
}
