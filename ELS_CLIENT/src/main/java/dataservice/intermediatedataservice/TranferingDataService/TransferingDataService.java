package dataservice.intermediatedataservice.TranferingDataService;

import po.TranferingReceiptPO;

public interface TransferingDataService {
    public TranferingReceiptPO getTransferingReceipt();
    
    public boolean addOrder(String ID);
    
    public boolean deleteOrder(String ID);
    
    public boolean modifyOrder(String ID);
    
    public void updateTransferingReceipt(TranferingReceiptPO po);
}
