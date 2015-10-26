package dataservice.intermediatedataservice.envehicledataservice;

import java.util.ArrayList;

import po.EntrainingReceiptPO;
import po.OrderPO;
import po.TrainPO;
import po.TransferingReceiptPO;
import po.FarePO;

public interface EntrainingDataService {
    public ArrayList<TrainPO> getTrainList();
    
    public TrainPO getTrain(String ID);
    
    public ArrayList<OrderPO> updateWaitingList(TransferingReceiptPO po);
    
    public EntrainingReceiptPO entrain(ArrayList<OrderPO> po);
    
    public ArrayList<EntrainingReceiptPO> updateEntrainingReceiptList(EntrainingReceiptPO po);
    
    public FarePO computeFare(ArrayList<EntrainingReceiptPO> po);
    
    public boolean updateFare(FarePO po);
    
    public boolean getEntraningReceipt(ArrayList<EntrainingReceiptPO> po);
    
    public boolean updateEntraningReceipt(ArrayList<EntrainingReceiptPO> po);
}
