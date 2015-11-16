package businesslogicservice.receiptblservice;

import java.util.ArrayList;

import vo.ReceiptVO;

public interface ReceiptBLService {
    public boolean add(ReceiptVO vo);
    
    public boolean modify(String ID);
    
    public boolean batch(String[] ID);
    
    public boolean update(ReceiptVO vo);
    
    public void reply(String userID);
    
    public ArrayList<ReceiptVO> view();
    
    public ArrayList<ReceiptVO> refresh();
    
    public boolean approve(String ID);
    
    
    
    
}
