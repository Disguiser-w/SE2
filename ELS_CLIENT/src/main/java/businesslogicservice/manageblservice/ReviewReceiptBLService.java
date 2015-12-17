package businesslogicservice.manageblservice;

import java.util.ArrayList;

import vo.AllReceiptShowVO;

public interface ReviewReceiptBLService {
	public int approve(String ID, Object ob);
	//public boolean batch(String[] IDList, ArrayList<Object> obList);
	//public void reply(String userID);
	public AllReceiptShowVO getAllReceiptList();
	public AllReceiptShowVO refresh();
}
