package presentation.intermediateui;

import javax.swing.JTable;

import vo.TransferingReceiptVO;

public class InfoTable extends JTable{
	TransferingReceiptVO transferingReceipt;
	
	public InfoTable(int row,int column){
		super(row,column);
	}
	
	public void setTransferingReceipt(TransferingReceiptVO transferingReceipt){
		this.transferingReceipt = transferingReceipt;
	}
	
}
