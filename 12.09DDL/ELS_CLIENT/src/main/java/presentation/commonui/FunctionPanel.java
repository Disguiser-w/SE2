package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FunctionPanel extends JPanel {
	private ArrayList<FuncLabel> funcLabels;
	private int numOfFunc;
	private boolean onTheTop;
	private boolean isMoving;

	private boolean tooMuchFunc;
	private JPanel labelPanel;

	public FunctionPanel() {
		funcLabels = new ArrayList<FuncLabel>();
		numOfFunc = 0;

		onTheTop = true;
		tooMuchFunc = false;
		isMoving = false;

		setLayout(null);

	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		if (labelPanel != null) {
			labelPanel.setBounds(0, width / 5, width, height - width * 2 / 5);

			for (FuncLabel label : funcLabels) {
				if (onTheTop)
					onButtom();
				else
					onTop();
			}
		}
	}

	public void addFuncLabel(FuncLabel funcLabel) {

		funcLabels.add(funcLabel);

		if (labelPanel == null) {
			int width = getWidth();
			int height = getHeight();

			labelPanel = new JPanel();
			labelPanel.setBounds(0, width / 5, width, height - width * 2 / 5);
			labelPanel.setLayout(null);

			addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					if (numOfFunc <= 4)
						return;
					int height = getHeight();
					int width = getWidth();

					int y = e.getY();

					if (0 < y && y < width / 5 && !onTheTop) {
						onTheTop = true;

						if (tooMuchFunc) {
							// 下来
							(new Thread(new Runnable() {
								public void run() {
									down();
								}
							})).start();
						}
					}

					if (height - width / 5 < y && y < height && onTheTop) {
						onTheTop = false;

						if (tooMuchFunc) {
							// 上去
							(new Thread(new Runnable() {
								public void run() {
									up();
								}
							})).start();
						}
					}

				}

			});
			add(labelPanel);
		}

		int width = getWidth();
		numOfFunc++;
		funcLabel.setBounds(0, width * (numOfFunc - 1), width, width);

		// test
		//
		Image image = Images.getImageByName("test" + numOfFunc + ".png");
		funcLabel.setImage(image);
		funcLabel.setRolloverImage(image);
		funcLabel.setPressImage(image);

		labelPanel.add(funcLabel);
		labelPanel.setBackground(Color.gray);
		repaint();
	}

	public void down() {

		if (isMoving)
			return;
		isMoving = true;

		int distance = -funcLabels.get(0).getY();
		int times = distance / 20;
		for (int i = 0; i < times; i++) {
			for (FuncLabel label : funcLabels) {
				label.setLocation(0, label.getY() + 20);

			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			labelPanel.repaint();
		}
		isMoving = false;

		onButtom();

		repaint();

	}

	private void onButtom() {
		int i = 0;
		int width = labelPanel.getWidth();
		for (FuncLabel label : funcLabels) {
			label.setBounds(0, width * i, width, width);
			i++;

		}
	}

	public void up() {

		if (isMoving)
			return;
		isMoving = true;

		int distance = funcLabels.get(numOfFunc - 1).getY() + labelPanel.getWidth() - labelPanel.getHeight();

		int times = distance / 20;

		for (int i = 0; i < times; i++) {
			for (FuncLabel label : funcLabels) {
				label.setLocation(0, label.getY() - 20);
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			labelPanel.repaint();
		}
		isMoving = false;
		onTop();
		repaint();
	}

	private void onTop() {
		int i = 0;
		int size = funcLabels.size();
		int width = labelPanel.getWidth();
		int height = labelPanel.getHeight();
		for (FuncLabel label : funcLabels) {
			label.setBounds(0, height - (size - i) * width, width, width);
			i++;

		}

	}

	public int getIndex(FuncLabel label) {
		return funcLabels.indexOf(label);
	}

	// 待修改
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();

		if (numOfFunc * width > height - width * 2 / 5 && numOfFunc >= 5) {
			tooMuchFunc = true;
		}

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, width / 5);
		g.fillRect(0, height - width / 5, width, width / 5);

		g.setColor(Color.BLACK);
		if (tooMuchFunc) {

			if (!onTheTop)
				g.fillRect(0, 0, width, width / 5);
			else
				g.fillRect(0, height - width / 5 - 1, width, width / 5 + 1);
		}

	}
}