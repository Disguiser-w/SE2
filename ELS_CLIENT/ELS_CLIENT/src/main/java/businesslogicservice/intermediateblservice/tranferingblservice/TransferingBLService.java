package businesslogicservice.intermediateblservice.tranferingblservice;

import vo.TransferingReceiptVO;

public interface TransferingBLService {
    public TransferingReceiptVO showTransferingReceipt();
    
    public boolean addOrder(String ID);
    
    public boolean deleteOrder(String ID);
    
    public boolean modifyOrder(String ID);
    
    public void updateTransferingReceipt(TransferingReceiptVO vo);
}
