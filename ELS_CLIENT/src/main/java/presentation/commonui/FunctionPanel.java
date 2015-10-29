package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FunctionPanel extends JPanel {
	private ArrayList<FuncLabel> funcLabels;
	private int numOfFunc;
	private boolean onTheTop;
	private boolean isMoving;

	private boolean tooMuchFunc;

	public FunctionPanel() {
		funcLabels = new ArrayList<FuncLabel>();
		numOfFunc = 0;

		onTheTop = false;
		tooMuchFunc = false;
		isMoving = false;

		addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseMoved(MouseEvent e) {

				int height = getHeight();

				int y = e.getY();
				if (y < height / 4 && !onTheTop) {
					onTheTop = true;

					if (tooMuchFunc) {
						// 下来
						down();
					}
				}

				if (y > height * 3 / 4 && onTheTop) {
					onTheTop = false;

					if (tooMuchFunc) {
						// 上去
						up();
					}
				}

			}

		});
	}

	public void addFuncLabel(FuncLabel funcLabel) {
		int width = getWidth();
		int height = getHeight();
		numOfFunc++;

		funcLabel.setBounds(0, width * (numOfFunc - 1), width, width);
		funcLabels.add(funcLabel);

		repaint();
	}

	public int getIndex(FuncLabel funcLabel) {
		return funcLabels.indexOf(funcLabel);
	}

	public void down() {
		if (isMoving)
			return;
		isMoving = true;
		int distance = -funcLabels.get(0).getY();
		int times = distance / 10;
		for (int i = 0; i < times; i++) {
			for (FuncLabel label : funcLabels) {
				label.setLocation(0, getY() + distance);
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isMoving = false;

	}

	public void up() {
		if (isMoving)
			return;
		isMoving = true;
		int distance = funcLabels.get(numOfFunc).getY() - getHeight();

		int times = distance / 10;
		for (int i = 0; i < times; i++) {
			for (FuncLabel label : funcLabels) {
				label.setLocation(0, getY() - distance);
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isMoving = false;
	}

	// 待修改
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();

		if (numOfFunc * width > height) {
			tooMuchFunc = true;
		}

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, width / 5);
		g.fillRect(0, height - width / 5, width, width / 5);

		g.setColor(Color.BLACK);
		if (tooMuchFunc) {
			if (onTheTop)
				g.fillRect(0, 0, width, width / 5);
			else
				g.fillRect(0, height - width / 5, width, width / 5);
		}

	}
}