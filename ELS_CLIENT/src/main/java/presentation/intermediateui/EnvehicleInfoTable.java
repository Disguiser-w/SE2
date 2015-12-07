package presentation.intermediateui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class EnvehicleInfoTable extends JTable {
	private int width;
	private int height;

	public EnvehicleInfoTable(AbstractTableModel model) {
		super(model);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		width = 720;
		height = 480;
		setuiInfo();
	}

	public void setuiInfo() {
		height = height / 13 * 13;
		setSize(width, height);

		this.setRowHeight(height / 13);
		this.setRowSelectionAllowed(false);

		TableColumn tc0 = this.columnModel.getColumn(0);
		tc0.setPreferredWidth(width / 3);
		// tc.setResizable(false);

		TableColumn tc1 = this.columnModel.getColumn(1);
		tc1.setPreferredWidth(width / 8);
		// tc.setResizable(false);

		TableColumn tc2 = this.columnModel.getColumn(2);
		tc2.setPreferredWidth(width / 8);
		// tc.setResizable(false);

		TableColumn tc3 = this.columnModel.getColumn(3);
		tc3.setPreferredWidth(width / 8);
		// tc.setResizable(false);

		TableColumn tc4 = this.columnModel.getColumn(4);
		tc4.setPreferredWidth(width / 8);
		// tc.setResizable(false);

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				if (row % 2 == 0)
					setBackground(Color.cyan);
				else
					setBackground(Color.white);

				return super.getTableCellRendererComponent(table, value,
						isSelected, hasFocus, row, column);
			}
		};

		tcr.setHorizontalAlignment(JLabel.CENTER);
		tc0.setCellRenderer(tcr);
		tc1.setCellRenderer(tcr);
		tc2.setCellRenderer(tcr);
		tc3.setCellRenderer(tcr);
		tc4.setCellRenderer(tcr);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		this.width = width;
		this.height = height;
	}

	public void paint(Graphics g) {
		setuiInfo();
		super.paint(g);
	}
}
