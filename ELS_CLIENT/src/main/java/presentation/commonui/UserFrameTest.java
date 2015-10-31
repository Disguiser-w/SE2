package presentation.commonui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentation.intermediateui.TransferingPanel;

public class UserFrameTest extends UserFrame {

	public UserFrameTest() {
		super();
		for (int i = 0; i < 5; i++) {
			JPanel panel = new JPanel();

			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			panel.setBackground(new Color(1 * i, 12 * i, 25 * i));
			addFuncLabel(panel);
		}
		addFuncLabel(new TransferingPanel());

		showFrame();
	}

	public static void main(String[] args) {
		
		
		new UserFrameTest();

	}

}
