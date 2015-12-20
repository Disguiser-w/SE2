package presentation.commonui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTable extends JPanel {
	private JPanel scrollPanel;

	private int row;
	private int column;

	// 存放信息
	private String[] head;
	private ArrayList<String[]> infos;

	// 显示信息
	private ArrayList<MyRowPanel> rowPanel;

	// 总宽度
	private int width;
	// 每列宽度
	private int[] columnWidth;

	private int height;
	// 单选模式
	private int selectedNum;

	// 多选模式
	private boolean multiChoose;
	private JPanel panel = this;
	private MyCheckBox myBox;

	public MyTable(String[] head, ArrayList<String[]> infos, int[] widths, boolean multiChoose) {
		scrollPanel = new JPanel();
		
		
		scrollPanel.setBackground(new Color(235, 235, 235));
		setBackground(new Color(235, 235, 235));
		columnWidth = widths;
		width = 0;
		for (int i : widths)
			width += i;

		if (multiChoose)
			selectedNum = -1;
		this.head = head;
		this.infos = infos;
		column = head.length;
		row = infos.size();

		this.multiChoose = multiChoose;
		setLayout(null);

		if (multiChoose) {
			myBox = new MyCheckBox();
			myBox.setBounds(40 + width + 3, 13, 24, 24);

			myBox.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					if (myBox.getSelected()) {
						for (MyRowPanel i : rowPanel) {
							i.box.setSelected(true);
							// i.repaint();
						}
					} else
						for (MyRowPanel i : rowPanel) {
							i.box.setSelected(false);
							// i.repaint();
						}
				}

			});
			add(myBox);
		}

		// 声明
		loadPanels();

		addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {
				int dir = e.getWheelRotation();
				if (dir == 1) {
					if (panel.getY() + panel.getHeight() > scrollPanel.getHeight())
						panel.setLocation(0, panel.getY() - 10);

				}

				if (dir == -1) {
					if (panel.getY() < 0)
						panel.setLocation(0, panel.getY() + 10);

				}

			}

		});
	}

	public void setLocationAndSize(int x, int y, int width, int height) {
		scrollPanel.setBounds(x, y, width, height);
		scrollPanel.setLayout(null);
		setBounds(0, 0, scrollPanel.getWidth(), (row + 1) * 38 + 8);
		scrollPanel.add(this);
	}

	public void loadPanels() {

		row = infos.size();

		int extend = 0;
		if (multiChoose)
			extend = 80;
		else
			extend = 50;
		setSize(scrollPanel.getWidth(), (row + 1) * 38 + 8);

		rowPanel = new ArrayList<MyRowPanel>();

		for (int i = 0; i < row; i++) {
			MyRowPanel panel = new MyRowPanel(i);
			rowPanel.add(panel);
			panel.setLocation(10, 38 * (i + 1) + 8);
			add(panel);
		}

		scrollPanel.repaint();
	}

	// 尽量少用
	public void setInfos(ArrayList<String[]> infos) {

		for (MyRowPanel i : rowPanel)
			remove(i);

		this.infos = infos;
		loadPanels();
	}

	public ArrayList<Integer> getSelectedIndex() {
		ArrayList<Integer> selectedIndex = new ArrayList<Integer>();
		for (MyRowPanel i : rowPanel) {
			if (i.box.getSelected())
				selectedIndex.add(i.index);
		}
		return selectedIndex;
	}

	public void setRowValueAt(String[] info, int index) {
		infos.set(index, info);
		rowPanel.get(index).repaint();
		scrollPanel.repaint();
	}

	public void setValueAt(String info, int row, int column) {
		infos.get(row)[column] = info;
		rowPanel.get(row).repaint();
		scrollPanel.repaint();
	}

	public void addRow(String[] info) {
		setSize(getWidth(), getHeight() + 38);
		infos.add(info);
		MyRowPanel panel = new MyRowPanel(row++);
		panel.setLocation(10, 38 * row + 8);
		rowPanel.add(panel);
		add(panel);
		setSize(getWidth(), (row + 1) * 38 + 8);

		scrollPanel.repaint();
	}

	public void delRow(int r) {

		for (MyRowPanel panel : rowPanel) {
			int i = panel.index;
			if (i >= r && panel.index < row - 1) {
				setRowValueAt(infos.get(panel.index + 1), panel.index);
			}
		}

		remove(rowPanel.remove(row - 1));
		infos.remove(r);
		row--;

		setSize(getWidth(), (row + 1) * 38 + 8);
		scrollPanel.repaint();
	}

	public void setSelectedNum(int index) {
		if (!multiChoose) {
			rowPanel.get(selectedNum).cancelChoosed();

			selectedNum = index;
		} else {
			for (MyRowPanel i : rowPanel) {
				i.cancelChoosed();
			}
		}
	}

	public String[] getRowValueAt(int index) {
		return infos.get(index);
	}

	public String getValueAt(int row, int column) {
		return infos.get(row)[column];
	}

	public ArrayList<Integer> getRowIndex() {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		if (!multiChoose)
			nums.add(selectedNum);
		else {
			for (MyRowPanel i : rowPanel)
				if (i.box.getSelected())
					nums.add(i.index);
		}
		return nums;
	}

	// 有多选功能调用这个
	public ArrayList<String[]> getSelectedItems() {

		ArrayList<String[]> result = new ArrayList<String[]>();
		for (MyRowPanel i : rowPanel) {
			if (i.box.getSelected())
				result.add(infos.get(i.index));

		}

		return result;
	}

	// 如果是没有多选功能调用这个
	public String[] getSelectedItem() {
		return infos.get(selectedNum);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int widths = 30;

		g2d.setFont(new Font("WenQuanYi Micro Hei", Font.PLAIN, 15));
		FontMetrics fm = g2d.getFontMetrics();
		int ascent = fm.getAscent();

		g2d.drawLine(widths, 0, widths, height);
		int strWidth1 = fm.stringWidth("#");
		g2d.setColor(Color.BLACK);
		g2d.drawString("#", 10 + (widths - strWidth1) / 2, 8 + (30 + ascent) / 2);

		for (int i = 0; i < column; i++) {

			int strWidth = fm.stringWidth(head[i]);
			g2d.drawString(head[i], 8 + widths + (columnWidth[i] - strWidth) / 2, 8 + (30 + ascent) / 2);
			widths += columnWidth[i];
			// if (i != column - 1)
			// g2d.drawLine(widths, 0, widths, height);
		}

		// if (multiChoose) {
		// widths += columnWidth[column - 1];
		// g2d.drawLine(widths, 0, widths, height);
		// }

	}

	class MyRowPanel extends JPanel {
		int height;
		int index;
		MyCheckBox box;
		boolean isMouseOn;

		public MyRowPanel(int i) {
			this.index = i;
			height = 30;
			box = new MyCheckBox();
			if (multiChoose) {

				this.setSize(30 + width + 30, height);
				box.setBounds(30 + width + 3, 3, 24, height - 6);
				setLayout(null);
				add(box);
			} else
				setSize(30 + width, height);

			// box.addMouseListener(new MouseAdapter() {
			// public void mouseClicked(MouseEvent e) {
			//
			// repaint();
			// }
			// });

			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (box.getSelected()) {
						box.setSelected(false);
					} else {
						setSelectedNum(index);
						box.setSelected(true);
					}
					// repaint();
				}
			});

		}

		public void paintComponent(Graphics g) {
			String[] rowInfos = infos.get(index);
			int widths = 30;
			Graphics2D g2d = (Graphics2D) g;

			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setFont(new Font("WenQuanYi Micro Hei", Font.PLAIN, 14));
			FontMetrics fm = g2d.getFontMetrics();
			int ascent = fm.getAscent();
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, getWidth(), height);

			g2d.setColor(new Color(235, 235, 235));
			// g2d.setColor(Color.BLACK);
			g2d.drawLine(widths, 0, widths, height);

			int strWidth1 = fm.stringWidth(index + 1 + "");
			// g2d.setColor(new Color(102, 102, 102));
			g2d.setColor(Color.BLACK);
			g2d.drawString(index + 1 + "", (widths - strWidth1) / 2, (height + ascent) / 2 - 2);

			for (int i = 0; i < column; i++) {

				int strWidth = fm.stringWidth(rowInfos[i]);
				g2d.setColor(Color.BLACK);
				g2d.drawString(rowInfos[i], widths + (columnWidth[i] - strWidth) / 2, (height + ascent) / 2 - 2);
				g2d.setColor(new Color(235, 235, 235));
				widths += columnWidth[i];
				if (i != column - 1)
					g2d.drawLine(widths, 0, widths, height);
			}
			widths += columnWidth[column - 1];
			int width = getWidth();
			if (multiChoose) {

				g2d.setColor(new Color(235, 235, 235));
				g2d.drawLine(width - 31, 0, width - 31, height);
			}

			g2d.setColor(new Color(0, 134, 255));
			if (isMouseOn)
				g2d.drawRect(0, 0, widths - 1, height - 1);

			// if (box.getSelected()) {
			//
			// g2d.drawRect(0, 0, width - 1, height - 1);
			// g2d.drawRect(1, 1, width - 3, height - 3);
			// g2d.drawRect(2, 2, width - 5, height - 5);
			// }

		}

		// 用这个来刷新
		public void updateContent(int index) {
			this.index = index;
			repaint();
		}

		// 取消选中
		public void cancelChoosed() {
			box.setSelected(false);
			repaint();
		}

	}

	public JPanel getScrollPanel() {
		return scrollPanel;
	}

	// public static void main(String[] args) {
	// JFrame frame = new JFrame();
	// frame.setSize(400, 300);
	// frame.setVisible(true);
	// // frame.addMouseListener(new MouseAdapter() {
	// // public void mouseClicked(MouseEvent e) {
	// // System.out.println(e.getY());
	// // }
	// // });
	//
	// // MyCheckBox box = new MyCheckBox();
	// // box.setBounds(40, 40, 24, 24);
	// // frame.add(box);
	//
	// String[] head = { "第一列", "第二列", "第三列" };
	// ArrayList<String[]> infos = new ArrayList<String[]>();
	// infos.add(new String[] { "asdf", "asdfa", "asdfasdf" });
	// infos.add(new String[] { "asdf", "asdfa", "asdfasdf" });
	// infos.add(new String[] { "as222f", "asdfa", "asdfasdf" });
	//
	// int[] width = { 100, 100, 100 };
	// MyTable table = new MyTable(head, infos, width, true);
	// table.setLocationAndSize(0, 0, 380, 200);
	// frame.setLayout(null);
	// frame.add(table.getScrollPanel());
	//
	// try {
	// Thread.sleep(5000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// // frame.repaint();
	//
	// // ArrayList<String[]> infos1 = new ArrayList<String[]>();
	// // infos1.add(new String[] { "asdf", "asdfa", "asdfasdf" });
	// // infos1.add(new String[] { "asdf", "asdfa", "asdfasdf" });
	//
	// table.setRowValueAt(new String[] { "asdf", "asdfa", "asdfasdf" }, 2);
	// }

	public int getSelectedNum() {
		return selectedNum;
	}

	public void cancelSelected(int index) {
		rowPanel.get(index).box.setSelected(false);
	}

	public void setAllSelected(boolean b) {
		myBox.setSelected(b);
	}

}