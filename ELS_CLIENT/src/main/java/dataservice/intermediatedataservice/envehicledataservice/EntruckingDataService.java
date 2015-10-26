package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EntruckingReceiptPO;
import po.OrderPO;
import po.TranferingReceiptPO;
import po.TruckPO;
import po.farePO;

public interface EntruckingDataService {
    public ArrayList<TruckPO> getTruckList();
    
    public TruckPO getTruck(String ID);
    
    public ArrayList<OrderPO> updateWaitingList(TranferingReceiptPO po);
    
    public EntruckingReceiptPO entruck(ArrayList<OrderPO> po);
    
    public ArrayList<EntruckingReceiptPO> updateEntruckingReceiptList(EntruckingReceiptPO po);
    
    public farePO computeFare(ArrayList<EntruckingReceiptPO> po);
    
    public boolean updateFare(farePO po);
    
    public boolean getEntruckingReceipt(ArrayList<EntruckingReceiptPO> po);
    
    public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptPO> po);


}
