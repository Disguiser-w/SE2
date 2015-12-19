package presentation.commonui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import presentation.image.ImageGetter;

public class FunctionPanel extends JPanel {
	private ArrayList<FuncLabel> funcLabels;
	private int numOfFunc;
	private int nowFunc;
	private Image background;

	public FunctionPanel() {
		funcLabels = new ArrayList<FuncLabel>();
		numOfFunc = 0;
		nowFunc = 0;
		background = ImageGetter.getImage("background3.png").getImage();
		setLayout(null);

	}

	public void setNowLabel(int num) {

		funcLabels.get(nowFunc).setChoosed(false);
		nowFunc = num;
		funcLabels.get(num).setChoosed();
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
	}

	public void addFuncLabel(FuncLabel funcLabel) {
		funcLabels.add(funcLabel);
		int width = getWidth();
		int height = getHeight();
		add(funcLabel);
		funcLabel.setBounds(0, (height / 9 + 1) * numOfFunc, width, height / 9);
		numOfFunc++;
		repaint();
	}

	public int getIndex(FuncLabel label) {
		return funcLabels.indexOf(label);
	}

	// 待修改
	public void paintComponent(Graphics g) {
		int height = getHeight();
		int width = getWidth();

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0, 121, 255));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// 圆角方法
		 g2d.fillRoundRect(0, -7, width, height + 7, 14, 14);
//		g2d.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		for (int i = 0; i < numOfFunc; i++) {
			g2d.setColor(new Color(0, 82, 130));
			g2d.fillRect(0, (i + 1) * (height / 9 + 1) - 1, width, 1);

		}
	}
}