package presentation.commonui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentation.intermediateui.TransferingPanel;

public class UserFrameTest extends UserFrame {

	public UserFrameTest() {
		super();
		for (int i = 0; i < 6; i++) {
			JPanel panel = new JPanel();
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panel.add(new JButton("第 " + i + " 个"));
			panel.setBackground(new Color(10 * i, 20 * i, 30 * i));
			addFuncLabel(panel);
		}
		addFuncLabel(new TransferingPanel());

		setMessage("pig", "king");
		showFrame();
	}

	public static void main(String[] args) {
		new UserFrameTest();
	}

}
