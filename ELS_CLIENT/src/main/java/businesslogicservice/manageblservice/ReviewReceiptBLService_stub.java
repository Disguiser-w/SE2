package businesslogicservice.manageblservice;

import java.util.ArrayList;

import vo.ReceiptVO;

public class ReviewReceiptBLService_stub implements ReviewReceiptBLService{
	
	public boolean modify(ReceiptVO vo){
		System.out.println("Modify receipt succeed!");
		return true;
	}
	public boolean batch(String[] ID){
		System.out.println("Batch review receipt succeed!");
		return true;
	}
	
	public boolean approve(String ID){
		System.out.println("Approve receipt succeed!");
		return true;
	}
	public void reply(String userID){
		System.out.println("Reply receipt succeed!");
	}
	public ArrayList<ReceiptVO> getReceiptList(){
		System.out.println("Get receipt list succeed!");
		return null;
	}
	public ArrayList<ReceiptVO> refresh(){
		System.out.println("Refresh receipt list succeed!");
		return null;
	}
	
}
