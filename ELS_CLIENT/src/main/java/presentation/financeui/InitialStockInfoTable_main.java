package presentation.financeui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import vo.InitInfoVO;

public class InitialStockInfoTable_main extends JTable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6925560804172162467L;
	private int width;
	private int height;
	private TableColumn tc;
	
	ArrayList<InitInfoVO> initInfoList;
	
	public InitialStockInfoTable_main(int row,int column){
		super(row, column);
		width = 720;
		height = 480;
		setInfo();
	}
	
	public void setInfo() {
		height = height / 13 * 13;
		setSize(width, height);
		
		this.setRowHeight(height/13);
		this.setRowSelectionAllowed(false);
		
		tc = this.columnModel.getColumn(0);
		tc.setPreferredWidth(width*35/72);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(1);
		tc.setPreferredWidth(width*25/72);
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
	
	public void setList(ArrayList<InitInfoVO> initInfoList){
		this.initInfoList = initInfoList;
	}
}
