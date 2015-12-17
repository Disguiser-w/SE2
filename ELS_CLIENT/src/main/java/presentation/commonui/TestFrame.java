package presentation.commonui;

import java.awt.Cursor;
import java.awt.Point;

import javax.swing.JFrame;

/**
 * 程序启动的入口
 * 
 * @author Administrator
 * 
 */
public class TestFrame extends JFrame {
	private boolean isMoved;
	private Point pre_point;
	private Point end_point;

	public static void main(String args[]) {
		final TestFrame lui = new TestFrame();
		lui.showUI();

	}

	// 构造函数
	public TestFrame() {
		this.setSize(300, 370);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);// 去掉窗口的边框

	}

	// 显示窗口的函数
	public void showUI() {
		this.setVisible(true);
		this.setDragable(this);
	}

	// 为窗口加上监听器，使得窗口可以被拖动

	private void setDragable(final TestFrame lui) {
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent e) {
				isMoved = false;// 鼠标释放了以后，是不能再拖拽的了
				lui.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				isMoved = true;
				pre_point = new Point(e.getX(), e.getY());// 得到按下去的位置
				lui.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}
		});
		// 拖动时当前的坐标减去鼠标按下去时的坐标，就是界面所要移动的向量。
		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) {
				if (isMoved) {// 判断是否可以拖拽
					end_point = new Point(lui.getLocation().x + e.getX() - pre_point.x,
							lui.getLocation().y + e.getY() - pre_point.y);
					lui.setLocation(end_point);
				}
			}
		});
	}

}
