package common;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TestEvent extends JFrame {

	public TestEvent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setUndecorated(true);
		setSize(400, 600);
		setLocationRelativeTo(null);
		JPanel pane = new JPanel();
		pane.add(new JButton("ok"));
		pane.add(new JButton("cancel"));
		setContentPane(pane);

		pane.setBackground(new Color(0, 0, 0, 0.5f));
		setBackground(new Color(0, 0, 0, 0.5f));
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new TestEvent();
			}
		});
	}
}
