package presentation.intermediateui;

import java.awt.Graphics;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import vo.TransferingReceiptVO;

public class TransferingInfoTable extends JTable {
	private int width;
	private int height;
	TableColumn tc;

	TransferingReceiptVO transferingReceipt;

	public TransferingInfoTable(int row, int column) {
		super(row, column);
		width = 720;
		height = 480;
		setInfo();
	}

	private void setInfo() {

		height = height / 15 * 15;
		setSize(width, height);

		this.setRowHeight(height / 15);
		this.setRowSelectionAllowed(false);

		tc = this.columnModel.getColumn(0);
		tc.setPreferredWidth(width / 2);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(1);
		tc.setPreferredWidth(width / 4);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(2);
		tc.setPreferredWidth(width / 4);
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

	public void setTransferingReceipt(TransferingReceiptVO transferingReceipt) {
		this.transferingReceipt = transferingReceipt;
	}
}
