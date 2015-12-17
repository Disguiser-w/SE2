package businesslogicservice.manageblservice;

import java.util.ArrayList;

import vo.AllReceiptShowVO;

public class ReviewReceiptBLService_stub implements ReviewReceiptBLService{
	
	public boolean batch(String[] IDList, ArrayList<Object> obList){
		System.out.println("Batch review receipt succeed!");
		return true;
	}
	
	public int approve(String ID, Object ob){
		System.out.println("Approve receipt succeed!");
		return 0;
	}
	
	public void reply(String userID){
		System.out.println("Reply receipt succeed!");
	}
	
	public AllReceiptShowVO getAllReceiptList(){
		System.out.println("Get receipt list succeed!");
		return null;
	}
	
	public AllReceiptShowVO refresh(){
		System.out.println("Refresh receipt list succeed!");
		return null;
	}
	
}
