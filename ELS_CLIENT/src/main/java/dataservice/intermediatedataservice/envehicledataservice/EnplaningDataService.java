package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EnplaningReceiptPO;
import po.OrderPO;
import po.PlanePO;
import po.TransferingReceiptPO;
import po.farePO;

public interface EnplaningDataService {
    public ArrayList<PlanePO> getPlaneList();
    
    public PlanePO getPlane(String ID);
    
    public ArrayList<OrderPO> updateWaitingList(TransferingReceiptPO po);
    
    public EnplaningReceiptPO enplane(ArrayList<OrderPO> po);
    
    public ArrayList<EnplaningReceiptPO> updateEnplaningReceiptList(EnplaningReceiptPO po);
    
    public farePO computeFare(ArrayList<EnplaningReceiptPO> po);
    
    public boolean updateFare(farePO po);
    
    public boolean getEnplaningReceipt(ArrayList<EnplaningReceiptPO> po);
    
    public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptPO> po);
}
