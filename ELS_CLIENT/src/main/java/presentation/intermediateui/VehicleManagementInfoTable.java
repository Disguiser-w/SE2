package presentation.intermediateui;

import java.awt.Graphics;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class VehicleManagementInfoTable extends JTable {
	private int width;
	private int height;
	private TableColumn tc;

	public VehicleManagementInfoTable(int row, int column) {
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
		tc.setPreferredWidth(width * 80 / 720);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(1);
		tc.setPreferredWidth(width * 200 / 720);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(2);
		tc.setPreferredWidth(width * 6 / 72);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(3);
		tc.setPreferredWidth(width * 20 / 72);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(4);
		tc.setPreferredWidth(width * 6 / 72);
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
