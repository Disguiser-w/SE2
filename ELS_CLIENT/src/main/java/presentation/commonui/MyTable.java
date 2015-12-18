package presentation.commonui;

import javax.swing.JLabel;

public class MyTable {
	private int row;
	private int column;
	
	//存放信息
	private String[][] infos;
	
	//显示信息
	private JLabel[] rowLabels;
//	private  JPanel
	private int rowHeight;
	
	private int x;
	private int y;
	
	
	public MyTable(int row, int column) {
		this.row = row;
		this.column = column;
		
		rowLabels = new JLabel[row];
	}
	
	
}
