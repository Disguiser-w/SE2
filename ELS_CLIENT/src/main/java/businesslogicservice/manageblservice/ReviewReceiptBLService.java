package businesslogicservice.manageblservice;

import java.util.ArrayList;

import vo.ReceiptVO;

public interface ReviewReceiptBLService {
	public boolean approve(String ID, Object ob);
	public boolean batch(String[] IDList, ArrayList<Object> obList);
	public void reply(String userID);
	public ArrayList<ReceiptVO> getAllReceiptList();
	public ArrayList<ReceiptVO> refresh();
}
