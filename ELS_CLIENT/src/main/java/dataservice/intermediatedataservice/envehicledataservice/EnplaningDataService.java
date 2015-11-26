package dataservice.intermediatedataservice.envehicledataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import po.EnplaningReceiptPO;
import po.FarePO;
import po.OrderPO;
import po.PlanePO;
import po.TransferingReceiptPO;

public interface EnplaningDataService extends Remote {
    public ArrayList<PlanePO> getPlaneList();
    
    public PlanePO getPlane(String ID);
    
    public ArrayList<OrderPO> updateWaitingList(TransferingReceiptPO po);
    
    public EnplaningReceiptPO enplane(ArrayList<OrderPO> po);
    
    public ArrayList<EnplaningReceiptPO> updateEnplaningReceiptList(EnplaningReceiptPO po);
    
    public FarePO computeFare(ArrayList<EnplaningReceiptPO> po);
    
    public boolean updateFare(FarePO po);
    
    public boolean getEnplaningReceipt(ArrayList<EnplaningReceiptPO> po);
    
    public boolean updateEnplaningReceipt(ArrayList<EnplaningReceiptPO> po);
}
