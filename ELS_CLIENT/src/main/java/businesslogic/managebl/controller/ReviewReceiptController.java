package businesslogic.managebl.controller;

import java.util.ArrayList;

import vo.ReceiptVO;
import businesslogic.managebl.ReviewReceiptBL;

public class ReviewReceiptController {

	private ReviewReceiptBL reviewReceipt;
	
	public boolean modify(ReceiptVO vo){
		return reviewReceipt.modify(null);
	}
	public boolean batch(String[] ID){
		return reviewReceipt.batch(ID);
	}
	
	public boolean approve(String ID){
		return reviewReceipt.approve(ID);
	}
	
	public void reply(String userID){
		reviewReceipt.reply(userID);
	}
	
	public ArrayList<ReceiptVO> getReceiptList(){
		return reviewReceipt.getReceiptList();
	}
	
	public ArrayList<ReceiptVO> refresh(){
		return reviewReceipt.refresh();
	}
	
}
