package presentation.image;

import javax.swing.ImageIcon;

public class ImageGetter {

	public static ImageIcon getQueryImage() {
		return new ImageIcon(ImageGetter.class.getResource("query.png"));
	}

	public static ImageIcon getImage(String path) {
		return new ImageIcon(ImageGetter.class.getResource(path));
	}
}
