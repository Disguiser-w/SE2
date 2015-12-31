package businesslogic.repertorybl.controller;

import java.util.ArrayList;

import businesslogic.repertorybl.LeaveRepertoryReceiptBL;
import vo.LeaveRepertoryReceiptVO;

public class LeaveRepertoryReceiptController {

	private LeaveRepertoryReceiptBL leaveRepertoryReceiptBL;
	
	public LeaveRepertoryReceiptController(){
		this.leaveRepertoryReceiptBL = new LeaveRepertoryReceiptBL();
	}
	
	public int addLeaveRepertoryReceipt(String repertoryID, String userID, String[] goodsIDList, String[] timeList){
		return leaveRepertoryReceiptBL.addLeaveRepertoryReceipt(repertoryID, userID, goodsIDList, timeList);
	}
	
	/*public int deleteLeaveReceipt(String receiptID){
		return leaveRepertoryReceiptBL.deleteLeaveReceipt(receiptID);
	}*/
	
	public int sendLeaveReceipt(String receiptID){
		return leaveRepertoryReceiptBL.sendLeaveReceipt(receiptID);
	}
	
	public int approveLeaveReceipt(String receiptID){
		return leaveRepertoryReceiptBL.approveLeaveReceipt(receiptID);
	}
	
	public int disapproveLeaveReceipt(String receiptID){
		return leaveRepertoryReceiptBL.disapproveLeaveReceipt(receiptID);	
	}
	
	public LeaveRepertoryReceiptVO findLeaveReceiptByReceiptID(String receiptID){
		return leaveRepertoryReceiptBL.findLeaveReceiptByReceiptID(receiptID);	
	};
	
	public ArrayList<LeaveRepertoryReceiptVO> findLeaveReceiptByCreatorID(String creatorID){
		return leaveRepertoryReceiptBL.findLeaveReceiptByCreatorID(creatorID);	
	};
	
	public ArrayList<LeaveRepertoryReceiptVO> findLeaveReceiptByCreatorAndKeyword(String creator, String keyword){
		return leaveRepertoryReceiptBL.findLeaveReceiptByCreatorAndKeyword(creator, keyword);	
	};
	
	public ArrayList<LeaveRepertoryReceiptVO> getAllSubmitedLeaveReceipts(){
		return leaveRepertoryReceiptBL.getAllSubmitedLeaveReceipts();	
	};
	
	public ArrayList<LeaveRepertoryReceiptVO> getAllLeaveReceipts(){
		return leaveRepertoryReceiptBL.getAllLeaveReceipts();	
	};
	
	public String getLeaveReceiptPost(){
		return leaveRepertoryReceiptBL.getLeaveReceiptPost();	
	};
	
}
