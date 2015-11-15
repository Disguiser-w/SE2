package presentation.financeui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import vo.CollectionReceiptVO;
import vo.PaymentReceiptVO;

public class BusinessStateReceiptInfoTable extends JTable {
	private int width;
	private int height;
	TableColumn tc;

	ArrayList<CollectionReceiptVO> collectionReceiptList;
	ArrayList<PaymentReceiptVO> paymentReceiptList;

	public BusinessStateReceiptInfoTable(int row, int column) {
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
		tc.setPreferredWidth(width*11/36);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(1);
		tc.setPreferredWidth(width/9);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(2);
		tc.setPreferredWidth(width*11/36);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(3);
		tc.setPreferredWidth(width/9);
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

	public void setList(ArrayList<CollectionReceiptVO> collectionReceiptList,
			ArrayList<PaymentReceiptVO> paymentReceiptList) {
		this.collectionReceiptList = collectionReceiptList;
		this.paymentReceiptList = paymentReceiptList;
	}
}
