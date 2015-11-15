package presentation.commonui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;

public class LocationHelper {

	class ComponentStates {
		private JComponent component;

		private int pressX;
		private int pressY;
		// private int oldX;
		// private int oldY;
		// private int width;
		// private int height;

		private boolean isMoving;
		private boolean isResing;
		private boolean isPalMoving;

		public ComponentStates(JComponent comp) {
			this.component = comp;

			pressX = 0;
			pressY = 0;

			isMoving = false;
			isResing = false;
			isPalMoving = false;

			component.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getX() <= component.getWidth() / 2 || e.getY() <= component.getHeight() / 2) {
						isMoving = true;
					} else {
						isResing = true;
					}
					pressX = e.getX();
					pressY = e.getY();
					container.setFocusable(true);
				}

				public void mouseReleased(MouseEvent e) {
					isMoving = false;
					isResing = false;

					showLocation();

				}

				public void mouseClicked(MouseEvent e) {
					if (isPalMoving)
						isPalMoving = false;
					else
						isPalMoving = true;
				}
			});

			component.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					int newX = e.getX();
					int newY = e.getY();
					if (isMoving) {
						component.setLocation(component.getX() + newX - pressX, component.getY() + newY - pressY);
					} else if (isResing) {
						component.setSize(component.getWidth() + newX - pressX, component.getHeight() + newY - pressY);
						pressX = newX;
						pressY = newY;
					}
				}
			});

		}

		public void setPressX(int x) {
			pressX = x;
		}

		public int getPressX() {
			return pressX;
		}

		public void setPressY(int y) {
			pressY = y;
		}

		public int getPressY() {
			return pressY;
		}

		public JComponent getComponent() {
			return component;
		}

	}

	private int num;
	private JComponent container;

	private ArrayList<ComponentStates> components;

	public LocationHelper(JComponent container) {
		this.container = container;
		components = new ArrayList<ComponentStates>();
		num = -1;

		int num = container.getComponentCount();
		for (int i = 0; i < num; i++) {
			addCmp((JComponent) container.getComponent(i));
		}

		container.setFocusable(true);
		container.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				for (ComponentStates i : components)
					if (i.isPalMoving) {
						JComponent component = i.getComponent();
						switch (e.getKeyCode()) {
						case KeyEvent.VK_UP:
							component.setLocation(component.getX(), component.getY() - 1);
							break;
						case KeyEvent.VK_DOWN:
							component.setLocation(component.getX(), component.getY() + 1);
							break;
						case KeyEvent.VK_LEFT:
							component.setLocation(component.getX() - 1, component.getY());
							break;
						case KeyEvent.VK_RIGHT:
							component.setLocation(component.getX() + 1, component.getY());
							break;
						}
					}
			}
		});
	}

	public void addCmp(JComponent c) {
		c.setBounds(0 + num / 7 * 20, 20 * num % 140, 20, 20);
		c.setFocusable(false);
		if (c instanceof JLabel || c instanceof JTable)
			c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		if (c instanceof JLabel) {
			((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
		}
		components.add(new ComponentStates(c));
		num++;
		// 添加监听

	}

	public void showLocation() {
		int width = container.getWidth();
		int height = container.getHeight();
		for (ComponentStates i : components) {
			JComponent component = i.getComponent();
			int x = component.getX();
			int y = component.getY();
			int cWidth = component.getWidth();
			int cHeight = component.getHeight();

			System.out.println(".setBounds(" + "(int)(width * " + x * 25.0 / width + "/25),(int)(height * "
					+ y * 20.0 / height + "/25),(int)(width *  " + cWidth * 25.0 / width + " /25),(int)(height *  "
					+ cHeight * 20.0 / height + "/20));");
		}
		System.out.println();
	}
}
