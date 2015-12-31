package businesslogic.repertorybl.controller;

import java.util.ArrayList;

import businesslogic.repertorybl.EnterRepertoryReceiptBL;
import businesslogicservice.repertoryblservice.EnterRepertoryReceiptBLService;
import vo.EnterRepertoryReceiptVO;

public class EnterRepertoryReceiptController implements EnterRepertoryReceiptBLService{

	private EnterRepertoryReceiptBL enterRepertoryReceiptBL;
	
	public EnterRepertoryReceiptController(){
		this.enterRepertoryReceiptBL = new EnterRepertoryReceiptBL();
	}
	
	public int addEnterRepertoryReceipt(String repertoryID, String userID, String[] goodsIDList, String[] timeList){
		return enterRepertoryReceiptBL.addEnterRepertoryReceipt(repertoryID, userID, goodsIDList, timeList);
	}
	
	/*public int deleteEnterReceipt(String receiptID){
		return enterRepertoryReceiptBL.deleteEnterReceipt(receiptID);
	}*/
	
	public int sendEnterReceipt(String receiptID){
		return enterRepertoryReceiptBL.sendEnterReceipt(receiptID);
	}
	
	public int approveEnterReceipt(String receiptID){
		return enterRepertoryReceiptBL.approveEnterReceipt(receiptID);
	}
	
	public int disapproveEnterReceipt(String receiptID){
		return enterRepertoryReceiptBL.disapproveEnterReceipt(receiptID);	
	}
	
	public EnterRepertoryReceiptVO findEnterReceiptByReceiptID(String receiptID){
		return enterRepertoryReceiptBL.findEnterReceiptByReceiptID(receiptID);	
	};
	
	public ArrayList<EnterRepertoryReceiptVO> findEnterReceiptByCreatorID(String creatorID){
		return enterRepertoryReceiptBL.findEnterReceiptByCreatorID(creatorID);	
	};
	
	public ArrayList<EnterRepertoryReceiptVO> findEnterReceiptByCreatorAndKeyword(String creator, String keyword){
		return enterRepertoryReceiptBL.findEnterReceiptByCreatorAndKeyword(creator, keyword);	
	};
	
	public ArrayList<EnterRepertoryReceiptVO> getAllSubmitedEnterReceipts(){
		return enterRepertoryReceiptBL.getAllSubmitedEnterReceipts();	
	};
	
	public ArrayList<EnterRepertoryReceiptVO> getAllEnterReceipts(){
		return enterRepertoryReceiptBL.getAllEnterReceipts();	
	};
	
	public String getEnterReceiptPost(){
		return enterRepertoryReceiptBL.getEnterReceiptPost();	
	};
	
}
