package presentation.financeui;

import java.awt.Graphics;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class CollectionReceiptInfoTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private TableColumn tc;
	
	final String head[]={ "编号", "状态", "日期", "客户", "操作员", "总额汇总" };

	public CollectionReceiptInfoTable(int row, int column) {
		super(row, column);
		width = 720;
		height = 480;
		setInfo();
	}

	public void setInfo() {
		height = height / 13 * 13;
		setSize(width, height);

		this.setRowHeight(height / 13);
		this.setRowSelectionAllowed(false);

		tc = this.columnModel.getColumn(0);
		tc.setPreferredWidth(width * 25 / 72);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(1);
		tc.setPreferredWidth(width * 5 / 24);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(2);
		tc.setPreferredWidth(width * 5 / 24);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(3);
		tc.setPreferredWidth(width * 5 / 72);
		tc.setResizable(false);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		this.width = width;
		this.height = height;
	}

	public void paint(Graphics g) {
		setInfo();
		super.paint(g);
	}
}
