package businesslogic.managebl;

import java.util.ArrayList;

import vo.ReceiptVO;
import businesslogic.receiptbl.ReceiptBL;
import businesslogicservice.manageblservice.ReviewReceiptBLService;

public class ReviewReceiptBL implements ReviewReceiptBLService{
	private ReceiptBL receiptBL;
	
	public boolean modify(ReceiptVO vo){
		return receiptBL.modify(null);
	}
	public boolean batch(String[] ID){
		return receiptBL.batch(ID);
	}
	
	public boolean approve(String ID){
		return receiptBL.approve(ID);
	}
	public void reply(String userID){
		receiptBL.reply(userID);
	}
	public ArrayList<ReceiptVO> getReceiptList(){
		return receiptBL.view();
	}
	public ArrayList<ReceiptVO> refresh(){
		return receiptBL.refresh();
	}
	
}
