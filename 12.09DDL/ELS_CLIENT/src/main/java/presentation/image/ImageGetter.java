package presentation.image;

import javax.swing.ImageIcon;

public class ImageGetter {

	public static ImageIcon getQueryImage() {
		return new ImageIcon(ImageGetter.class.getResource("query.png"));
	}
}
