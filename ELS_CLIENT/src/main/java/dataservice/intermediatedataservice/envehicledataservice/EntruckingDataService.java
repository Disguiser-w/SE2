package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

public interface EntruckingDataService {
    public ArrayList<TruckPO> getTruckList();
    
    public TruckPO getTrain(String ID);
    
    public ArrayList<OrderPO> updateWaitingList(TranferingReceiptPO);
    
    public EntruckingReceiptPO entruck(ArrayList<OrderPO>);
    
    public ArrayList<EntruckingReceiptPO> updateEntruckingReceiptList(EntruckingReceiptPO);
    
    public farePO computeFare(ArrayList<EntruckingReceiptPO>);
    
    public boolean updateFare(farePO);
    
    public boolean getEntruckingReceipt(ArrayList<EntruckingReceiptPO>);
    
    public boolean updateEntruckingReceipt(ArrayList<EntruckingReceiptPO>);
}
