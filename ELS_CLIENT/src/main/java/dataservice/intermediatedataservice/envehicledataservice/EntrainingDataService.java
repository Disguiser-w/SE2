package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EntrainingReceiptPO;
import po.OrderPO;
import po.TrainPO;
import po.TranferingReceiptPO;
import po.farePO;

public interface EntrainingDataService {
    public ArrayList<TrainPO> getTrainList();
    
    public TrainPO getTrain(String ID);
    
    public ArrayList<OrderPO> updateWaitingList(TranferingReceiptPO po);
    
    public EntrainingReceiptPO entrain(ArrayList<OrderPO> po);
    
    public ArrayList<EntrainingReceiptPO> updateEntrainingReceiptList(EntrainingReceiptPO po);
    
    public farePO computeFare(ArrayList<EntrainingReceiptPO> po);
    
    public boolean updateFare(farePO po);
    
    public boolean getEntraningReceipt(ArrayList<EntrainingReceiptPO> po);
    
    public boolean updateEntraningReceipt(ArrayList<EntrainingReceiptPO> po);
}
