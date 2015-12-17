//package presentation.commonui;
//
//import java.awt.Color;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionListener;
//
//import javax.swing.BorderFactory;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//class HeaderPanel extends JPanel {
//
//	private JFrame containFrame;
//
//	private int x;
//	private int y;
//
//	public HeaderPanel(JFrame frame) {
//
//		this.containFrame = frame;
//		containFrame.setSize(UserFrame.DEFAULT_WIDTH, UserFrame.DEFAULT_HEIGHT);
//
//
//		addMouseMotionListener(new MouseMotionListener() {
//
//			public void mouseDragged(MouseEvent e) {
//
//				int dx = x - e.getX();
//				int dy = y - e.getY();
//
//				containFrame.setLocation(containFrame.getX() - dx, containFrame.getY() - dy);
//
//			}
//
//			//
//			public void mouseMoved(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
//		addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				x = e.getX();
//				y = e.getY();
//			}
//
//			public void mouseReleased(MouseEvent e) {
//
//			}
//
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) {
//					if (!isMaximum) {
//						isMaximum = true;
//						containFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//					} else {
//						isMaximum = false;
//						containFrame.setExtendedState(JFrame.NORMAL);
//					}
//				}
//			}
//
//		});
//
//
//
//		add(minimizeLabel);
//		add(maximizeLabel);
//		add(exitLabel);
//		setLayout(null);
//	}
//
//	public void setBounds(int x, int y, int width, int height) {
//		super.setBounds(x, y, width, height);
//		minimizeLabel.setBounds(width - 3 * height - 2, 0, height, height);
//		maximizeLabel.setBounds(width - 2 * height - 1, 0, height, height);
//		exitLabel.setBounds(width - height, 0, height, height);
//	}
//
//}