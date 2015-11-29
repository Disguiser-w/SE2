package businesslogicservice.manageblservice;

import java.util.ArrayList;

import vo.ReceiptVO;

public interface ReviewReceiptBLService {
	public boolean modify(ReceiptVO vo);
	public boolean batch(String[] ID);
	public boolean approve(String ID);
	public void reply(String userID);
	public ArrayList<ReceiptVO> getReceiptList();
	public ArrayList<ReceiptVO> refresh();
}
