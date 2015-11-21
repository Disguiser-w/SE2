package presentation.intermediateui;

import java.awt.Graphics;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import vo.TransferingReceiptVO;

public class EnvehicleInfoTable extends JTable{
	private int width;
	private int height;
	
	private TableColumn tc;

	TransferingReceiptVO transferingReceipt;

	public EnvehicleInfoTable(int row, int column) {
		super(row, column);
		width = 720;
		height = 480;
		setInfo();
	}

	private void setInfo() {

		height = height / 13 * 13;
		setSize(width, height);

		this.setRowHeight(height / 13);
		this.setRowSelectionAllowed(false);

		tc = this.columnModel.getColumn(0);
		tc.setPreferredWidth(width / 3);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(1);
		tc.setPreferredWidth(width / 8);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(2);
		tc.setPreferredWidth(width / 8);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(3);
		tc.setPreferredWidth(width / 8);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(4);
		tc.setPreferredWidth(width / 8);
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
