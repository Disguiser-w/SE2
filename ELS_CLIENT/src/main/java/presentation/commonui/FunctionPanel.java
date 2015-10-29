package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
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

					int height = getHeight();
					int width = getWidth();
					System.out.println("Click!");
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

		labelPanel.add(funcLabel);
		labelPanel.setBackground(Color.gray);
		repaint();
	}

	public void down() {

		if (isMoving)
			return;
		isMoving = true;
		System.out.println("down");
		int distance = -funcLabels.get(0).getY();
		int times = distance / 20;
		for (int i = 0; i < times; i++) {
			for (FuncLabel label : funcLabels) {
				label.setLocation(0, label.getY() + 20);

			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			labelPanel.repaint();
		}
		isMoving = false;

		int i = 0;
		int width = labelPanel.getWidth();
		for (FuncLabel label : funcLabels) {
			label.setLocation(0, width * i);
			i++;
		}

		repaint();

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
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			labelPanel.repaint();
		}
		isMoving = false;

		int i = 0;
		int size = funcLabels.size();
		int width = labelPanel.getWidth();
		int height = labelPanel.getHeight();
		for (FuncLabel label : funcLabels) {
			label.setLocation(0, height - (size - i) * width);
			i++;
		}

		repaint();
	}

	public int getIndex(FuncLabel label) {
		return funcLabels.indexOf(label);
	}

	// 待修改
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();

		if (numOfFunc * width > height - width * 2 / 5) {
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
				g.fillRect(0, height - width / 5, width, width / 5);
		}

	}
}