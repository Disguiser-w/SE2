package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import common.ImageGetter;

public class OperationPanel extends JPanel implements Observer {
	private Image background;

	protected OperationPanel() {
		background = ImageGetter.getImage("background4.png").getImage();

	}

	public void add(MyTable table) {
		table.addObervable(this);
		add(table.getScrollPanel());
	}

	public void remove(MyTable table) {
		remove(table.getScrollPanel());
	}

	public void add(MyTextField field) {
		field.addObservable(this);
		add(field.getLabel());
	}

	public void add(MyTextArea area) {
		super.add(area);
		area.addObservable(this);
	}
	
	

	public void remove(MyTextField field) {
		remove(field.getLabel());
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// 圆角方法
		if (UserFrame.type == UserFrame.TYPE_0) {
			g2d.setColor(new Color(250, 250, 250));
			g2d.fillRect(0, 0, getWidth(), getHeight());
		} else if (UserFrame.type == UserFrame.TYPE_1)
			g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		// super.paintComponent(g);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}
