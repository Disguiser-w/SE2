package presentation.intermediateui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class EnvehicleInfoTable extends JTable {
	private int width = 720;
	private int height = 480;

	TableColumn tc0;
	TableColumn tc1;
	TableColumn tc2;
	TableColumn tc3;
	TableColumn tc4;

	public EnvehicleInfoTable(AbstractTableModel model) {
		super(model);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		setuiInfo();

		this.setRowHeight(height * 22 / 480);
		this.setRowSelectionAllowed(false);

		tc0 = this.columnModel.getColumn(0);
		tc0.setPreferredWidth(width / 3);

		tc1 = this.columnModel.getColumn(1);
		tc1.setPreferredWidth(width / 8);

		tc2 = this.columnModel.getColumn(2);
		tc2.setPreferredWidth(width / 8);

		tc3 = this.columnModel.getColumn(3);
		tc3.setPreferredWidth(width / 8);

		tc4 = this.columnModel.getColumn(4);
		tc4.setPreferredWidth(width / 8);
	}

	public void setuiInfo() {
		tc0 = this.columnModel.getColumn(0);
		tc1 = this.columnModel.getColumn(1);
		tc2 = this.columnModel.getColumn(2);
		tc3 = this.columnModel.getColumn(3);
		tc4 = this.columnModel.getColumn(4);

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
}
