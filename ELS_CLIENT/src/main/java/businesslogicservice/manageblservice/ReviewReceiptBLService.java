package businesslogicservice.manageblservice;

import vo.AllReceiptShowVO;

public interface ReviewReceiptBLService {
	
	public int approve(String ID, Object ob);
	public AllReceiptShowVO getAllReceiptList();
	
}
