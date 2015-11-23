package presentation.commonui;

import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class MessageDialog extends JDialog {
	private String[] message;
	private MessagePanel messagePanel;

	public MessageDialog(String[] message) {
		this.message = message;
		messagePanel = new MessagePanel();
		repaint();
		setVisible(true);
	}

	class MessagePanel extends JPanel {
		public void paintComponent(Graphics g) {
			FontMetrics fm = g.getFontMetrics();
			int width = getWidth();
			int height = getHeight();
			
			
		}
	}
}
