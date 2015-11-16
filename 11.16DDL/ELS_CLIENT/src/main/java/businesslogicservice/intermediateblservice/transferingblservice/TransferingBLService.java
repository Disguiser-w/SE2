package businesslogicservice.intermediateblservice.transferingblservice;

import vo.TransferingReceiptVO;

public interface TransferingBLService {
    public TransferingReceiptVO showTransferingReceipt();
    
    public boolean addOrder(String ID);
    
    public boolean deleteOrder(String ID);
    
    public boolean modifyOrder(String ID);
    
    public TransferingReceiptVO updateTransferingReceipt(TransferingReceiptVO vo);
}
