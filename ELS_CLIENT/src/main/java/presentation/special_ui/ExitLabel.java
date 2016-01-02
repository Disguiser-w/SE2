
package presentation.special_ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import common.ImageGetter;
import presentation.commonui.MyLabel;
import presentation.commonui.UserFrame;

public class ExitLabel extends MyLabel {
	private Image close_1;
	private Image close_0;
	private Image close_3;
	private Image close_2;

	public ExitLabel() {
		this("");
	}

	public ExitLabel(String str) {
		setText(str);

		close_1 = ImageGetter.getImage("close_2.png").getImage();
		close_0 = ImageGetter.getImage("close_3.png").getImage();
		close_3 = ImageGetter.getImage("close_2.png").getImage();
		close_2 = ImageGetter.getImage("close_3.png").getImage();
		// setImages(close_1, close_0, close_0);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}

	public void paintComponent(Graphics g) {
		if (UserFrame.type == UserFrame.TYPE_0) {

			setImages(close_3, close_2, close_2);
		} else {
			setImages(close_1, close_0, close_0);

		}
		super.paintComponent(g);
	}

}
