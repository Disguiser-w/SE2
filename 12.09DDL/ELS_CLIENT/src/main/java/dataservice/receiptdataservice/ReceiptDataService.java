package dataservice.receiptdataservice;

import java.util.ArrayList;

import po.ReceiptPO;

public interface ReceiptDataService {
    public boolean add(ReceiptPO po);
    
    public ReceiptPO find(String ID);
    
    public boolean modify(ReceiptPO po);
    
    public ArrayList<ReceiptPO> get();
    
    public void init();
}
