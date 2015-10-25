package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

public interface EnplaningDataService {
    public ArrayList<PlanePO> getPlaneList();
    
    public PlanePO getPlane(String ID);
    
    public ArrayList<OrderPO> updateWaitingList(TranferingReceiptPO);
    
    public EnplaningReceiptPO enplane(ArrayList<OrderPO>);
    
    public ArrayList<EnplaningReceiptPO> updateEnplaningReceiptList(EnplaingReceiptPO);
    
    public farePO computeFare(ArrayList<EnplaningReceiptPO>);
    
    public boolean updateFare(farePO);
    
    public boolean getEnplaningReceipt(ArrayList<EnplaningReceiptPO>);
    
    public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptPO>);
}
