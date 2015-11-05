package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EntruckingReceiptPO;
import po.OrderPO;
import po.TransferingReceiptPO;
import po.TruckPO;
import po.FarePO;

public interface EntruckingDataService {
    public ArrayList<TruckPO> getTruckList();
    
    public TruckPO getTruck(String ID);
    
    public ArrayList<OrderPO> updateWaitingList(TransferingReceiptPO po);
    
    public EntruckingReceiptPO entruck(ArrayList<OrderPO> po);
    
    public ArrayList<EntruckingReceiptPO> updateEntruckingReceiptList(EntruckingReceiptPO po);
    
    public FarePO computeFare(ArrayList<EntruckingReceiptPO> po);
    
    public boolean updateFare(FarePO po);
    
    public boolean getEntruckingReceipt(ArrayList<EntruckingReceiptPO> po);
    
    public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptPO> po);


}
