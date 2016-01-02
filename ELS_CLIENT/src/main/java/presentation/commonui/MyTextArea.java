package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

public class MyTextArea extends JTextArea {
	private MyObservable observable;
	private int time;

	public MyTextArea() {
		time = 0;
		observable = new MyObservable();
		// setBorder(BorderFactory.createLineBorder(new Color(190, 190, 190,
		// 125)));
		setBackground(new Color(150, 150, 150, 75));
	}

	public void setText(String str) {
		super.setText(str);

	}

	public void addObservable(Object o) {
		observable.addObserver((Observer) o);
	}

	public void paintComponent(Graphics g) {
		if (UserFrame.type == UserFrame.TYPE_0) {
			setForeground(Color.BLACK);
			if (time == 0) {
				time++;
				observable.setData();
			}
		} else if (UserFrame.type == UserFrame.TYPE_1) {
			setForeground(Color.WHITE);
			if (time == 1) {
				time--;
				observable.setData();
				
			}
		}
		super.paintComponent(g);

	}
}

class MyObservable extends Observable {
	public void setData() {
		setChanged();
		notifyObservers();
	}
}
