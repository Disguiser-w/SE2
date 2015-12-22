package businesslogic.managebl.controller;

import vo.AllReceiptShowVO;
import businesslogic.managebl.ReviewReceiptBL;

public class ReviewReceiptController {

	private ReviewReceiptBL reviewReceipt;
	
	public int approve(String ID, Object ob){
		return reviewReceipt.approve(ID, ob);
	}
	
	public AllReceiptShowVO getReceiptList(){
		return reviewReceipt.getAllReceiptList();
	}
	
}
