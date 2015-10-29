package presentation.intermediateui;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import vo.TransferingReceiptVO;

public class InfoTable extends JTable{
	TransferingReceiptVO transferingReceipt;
	
	public InfoTable(int row,int column){
		super(row,column);
		this.setRowHeight(24);
		this.setRowSelectionAllowed(false);
		
		
		TableColumn tc = this.columnModel.getColumn(0);
		tc.setPreferredWidth(300);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(1);
		tc.setPreferredWidth(150);
		tc.setResizable(false);
		
		tc = this.columnModel.getColumn(2);
		tc.setPreferredWidth(150);
		tc.setResizable(false);
	}
	
	public void setTransferingReceipt(TransferingReceiptVO transferingReceipt){
		this.transferingReceipt = transferingReceipt;
	}
	
}
