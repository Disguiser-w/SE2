package businesslogicservice.intermediateblservice;

import type.OperationState;
import vo.TransferingReceiptVO;

public interface TransferingBLService {
    public TransferingReceiptVO showTransferingReceipt();
    
    public boolean addOrder(String ID);
    
    public OperationState deleteOrder(String ID) throws Exception;
    
    public boolean modifyOrder(String ID);
    
    public TransferingReceiptVO updateTransferingReceipt(TransferingReceiptVO vo);
}
