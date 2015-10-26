package dataservice.intermediatedataservice.TranferingDataService;

import po.TransferingReceiptPO;

public interface TransferingDataService {
    public TransferingReceiptPO getTransferingReceipt();
    
    public boolean addOrder(String ID);
    
    public boolean deleteOrder(String ID);
    
    public boolean modifyOrder(String ID);
    
    public void updateTransferingReceipt(TransferingReceiptPO po);
}
