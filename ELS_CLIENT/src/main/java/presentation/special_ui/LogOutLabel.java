package presentation.special_ui;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import common.ImageGetter;
import init.Client;
import presentation.commonui.MyLabel;

public class LogOutLabel extends MyLabel {
	private JFrame frame;

	public LogOutLabel(JFrame f) {
		this("", f);
	}

	public LogOutLabel(String str, JFrame f) {
		setText(str);
		frame = f;

		Image normal = ImageGetter.getImage("logout_0.png").getImage();
		Image hover = ImageGetter.getImage("logout_1.png").getImage();
		Image press = ImageGetter.getImage("logout_2.png").getImage();

		setImages(normal, hover, press);

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				new Client();
			}
		});
	}
}
