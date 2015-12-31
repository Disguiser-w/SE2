package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import common.ImageGetter;

public class MyCheckBox extends JLabel {
	private boolean isSelected;
	private boolean isPressed;
	private boolean isMouseOn;

	private Image unselect_day;
	private Image select_day;

	private Image unselect_night;
	private Image select_night;

	public MyCheckBox() {
		isSelected = false;
		isPressed = false;
		isMouseOn = false;

		unselect_day = ImageGetter.getImage("unselected_day.png").getImage();
		select_day = ImageGetter.getImage("selected_day.png").getImage();
		unselect_night = ImageGetter.getImage("unselected_black.png")
				.getImage();
		select_night = ImageGetter.getImage("selected_black.png").getImage();
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!isSelected)
					isSelected = true;
				else{
					isSelected = false;
				}
				repaint();
			}

		});

	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		repaint();
	}

	public boolean getSelected() {
		return isSelected;
	}

	public void paintComponent(Graphics g) {

//		g.setColor(new Color(0, 0, 0, 0));
//		g.fillRect(0, 0, getWidth(), getHeight());

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		g2d.setColor(new Color(0, 134, 255));
		g2d.fillOval(4, 4, width - 7, height - 7);
		g2d.setColor(Color.WHITE);
		g2d.fillOval(6, 6, width - 11, height - 11);

		g2d.setColor(new Color(0, 134, 255));
		if (isSelected)
			g2d.fillOval(width / 4 + 3, height / 4 + 3, width / 2 - 5,
					height / 2 - 5);

		// if (isSelected) {
		// if (UserFrame.type == UserFrame.TYPE_0) {
		// g2d.drawImage(select_day, 0, 0, width, height, null);
		// // g2d.setColor(Color.BLACK);
		// // g2d.drawOval(5, 5, width - 11, height - 11);
		// // g2d.fillOval(9, 9, width - 18, height - 18);
		//
		// } else if (UserFrame.type == UserFrame.TYPE_1) {
		// g2d.drawImage(select_night, 0, 0, width, height, null);
		// // g2d.setColor(Color.WHITE);
		// // g2d.drawOval(5, 5, width - 11, height - 11);
		// // g2d.fillOval(9, 9, width - 18, height - 18);
		//
		// }
		// } else {
		// if (UserFrame.type == UserFrame.TYPE_0) {
		// g2d.drawImage(unselect_day, 0, 0, width, height, null);
		// // g2d.setColor(Color.BLACK);
		// // g2d.drawOval(5, 5, width - 11, height - 11);
		// } else if (UserFrame.type == UserFrame.TYPE_1) {
		// g2d.drawImage(unselect_night, 0, 0, width, height, null);
		// // g2d.setColor(Color.WHITE);
		// // g2d.drawOval(5, 5, width - 11, height - 11);
		// }
		// }

	}

}
