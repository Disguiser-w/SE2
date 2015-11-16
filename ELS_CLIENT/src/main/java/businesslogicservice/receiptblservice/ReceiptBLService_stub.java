package businesslogicservice.receiptblservice;

import java.util.ArrayList;

import vo.ReceiptVO;

public class ReceiptBLService_stub implements ReceiptBLService{

	public boolean add(ReceiptVO vo) {
		// TODO 自动生成的方法存根	
		System.out.println("Add receipt successfully!");
		return true;
	}

	public boolean modify(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("modify receipt successfully!");
		return true;
	}

	public boolean batch(String[] ID) {
		// TODO 自动生成的方法存根
		System.out.println("batch successfully!");
		return true;
	}

	public boolean update(ReceiptVO vo) {
		// TODO 自动生成的方法存根
		System.out.println("update receipt successfully!");
		return true;
	}
	
	public void reply(String userID) {
		// TODO 自动生成的方法存根
		System.out.println("reply successfully!");
		
	}

	public ArrayList<ReceiptVO> view() {
		// TODO 自动生成的方法存根
		System.out.println("view successfully!");
		return null;
	}

	public ArrayList<ReceiptVO> refresh() {
		// TODO 自动生成的方法存根
		return null;
	}


	public boolean approve(String ID) {
		// TODO 自动生成的方法存根
		System.out.println("approve successfully!");
		return true;
	}
    
}
