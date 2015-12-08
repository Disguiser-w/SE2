package presentation.userui;

import java.awt.Graphics;

import javax.swing.JTable;
//import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class UserInformationTable extends JTable {

	private static final long serialVersionUID = 7L;
	
	private int width;
	private int height;
	private TableColumn tc;

	public UserInformationTable(int row, int column) {
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
		tc.setPreferredWidth(width * 65 / 720);
		tc.setResizable(false);

		tc = this.columnModel.getColumn(1);
		tc.setPreferredWidth(width * 80 / 720);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(2);
		tc.setPreferredWidth(width * 8 / 72);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(3);
		tc.setPreferredWidth(width * 10 / 72);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(4);
		tc.setPreferredWidth(width * 13 / 72);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(5);
		tc.setPreferredWidth(width * 8 / 72);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(6);
		tc.setPreferredWidth(width * 65 / 720);
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
