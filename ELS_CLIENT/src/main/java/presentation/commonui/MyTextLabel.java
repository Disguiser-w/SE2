package presentation.commonui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class MyTextLabel extends JLabel {
	private MyObservable observable;
	private int time;

	public MyTextLabel(String text) {
		super(text);
		observable = new MyObservable();
		time = 0;
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
	}

	public MyTextLabel() {
		this("");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (UserFrame.type == UserFrame.TYPE_0) {
			setForeground(Color.BLACK);
			if (time == 0) {
				time++;
				observable.setData();
			}
		} else {
			setForeground(Color.WHITE);
			if (time == 1) {
				time--;
				observable.setData();
			}
		}
	}

	public void addObservable(Object o) {
		observable.addObserver((Observer) o);
	}

	class MyObservable extends Observable {
		public void setData() {
			setChanged();
			notifyObservers();
		}
	}

}
