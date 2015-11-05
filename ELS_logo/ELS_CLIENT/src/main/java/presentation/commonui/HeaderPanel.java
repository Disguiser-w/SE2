package presentation.commonui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.glass.ui.Cursor;

class HeaderPanel extends JPanel {
	private JLabel minimizeLabel;
	private JLabel maximizeLabel;
	private JLabel exitLabel;
	private JFrame containFrame;
	private boolean isMaximum;
	private int x;
	private int y;

	public HeaderPanel(JFrame frame) {
		isMaximum = false;
		this.containFrame = frame;
		minimizeLabel = new JLabel();
		maximizeLabel = new JLabel();
		exitLabel = new JLabel();
		minimizeLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		maximizeLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		exitLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {

				int dx = x - e.getX();
				int dy = y - e.getY();

				containFrame.setLocation(containFrame.getX() - dx, containFrame.getY() - dy);

			}

			//
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}

			public void mouseReleased(MouseEvent e) {

			}

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (!isMaximum) {
						isMaximum = true;
						containFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					} else {
						isMaximum = false;
						containFrame.setExtendedState(JFrame.NORMAL);
					}
				}
			}

		});

		minimizeLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				containFrame.setExtendedState(JFrame.ICONIFIED);
			}
		});

		maximizeLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!isMaximum) {
					isMaximum = true;
					// containFrame.setResizable(true);
					containFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					// containFrame.setResizable(false);
				} else {
					isMaximum = false;
					// containFrame.setResizable(true);
					containFrame.setExtendedState(JFrame.NORMAL);
					// containFrame.setResizable(false);
				}
			}
		});

		exitLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				((UserFrame) (containFrame)).exitSystem();
			}
		});

		add(minimizeLabel);
		add(maximizeLabel);
		add(exitLabel);
		setLayout(null);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		minimizeLabel.setBounds(width - 3 * height - 2, 0, height, height);
		maximizeLabel.setBounds(width - 2 * height - 1, 0, height, height);
		exitLabel.setBounds(width - height, 0, height, height);
	}

}