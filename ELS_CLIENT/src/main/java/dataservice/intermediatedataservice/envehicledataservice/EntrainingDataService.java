package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

public interface EntrainingDataService {
    public ArrayList<TrainPO> getTrainList();
    
    public TrainPO getTrain(String ID);
    
    public ArrayList<OrderPO> updateWaitingList(TranferingReceiptPO);
    
    public EntrainingReceiptPO entrain(ArrayList<OrderPO>);
    
    public ArrayList<EntrainingReceiptPO> updateEntrainingReceiptList(EntrainingReceiptPO);
    
    public farePO computeFare(ArrayList<EntrainingReceiptPO>);
    
    public boolean updateFare(farePO);
    
    public boolean getEntraningReceipt(ArrayList<EntraningReceiptPO>);
    
    public boolean updateEntraningReceipt(ArrayList<EntraningReceiptPO>);
}
