
package presentation.special_ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import common.ImageGetter;
import init.Client;
import presentation.commonui.MyLabel;
import presentation.commonui.UserFrame;

public class LogOutLabel extends MyLabel {

	private Image exit_1_in;
	private Image exit_0_in;
	private Image exit_3_in;
	private Image exit_2_in;

	private JFrame frame;

	public LogOutLabel(JFrame f) {
		this("", f);
	}

	public LogOutLabel(String str, JFrame f) {
		setText(str);
		frame = f;

		exit_1_in = ImageGetter.getImage("exit_3_in.png").getImage();
		exit_0_in = ImageGetter.getImage("exit_2_in.png").getImage();
		exit_3_in = ImageGetter.getImage("exit_3_in.png").getImage();
		exit_2_in = ImageGetter.getImage("exit_2_in.png").getImage();

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				new Client();
			}
		});
	}

	public void paintComponent(Graphics g) {
		if (UserFrame.type == UserFrame.TYPE_0) {

			setImages(exit_3_in, exit_2_in, exit_2_in);
		} else {
			setImages(exit_1_in, exit_0_in, exit_0_in);

		}
		super.paintComponent(g);
	}

}
