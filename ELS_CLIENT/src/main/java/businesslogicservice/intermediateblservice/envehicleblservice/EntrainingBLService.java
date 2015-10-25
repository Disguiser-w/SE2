package businesslogicservice.intermediateblservice.envehicleblservice;

import java.util.ArrayList;

public interface EntrainingBLService {
    public ArrayList<TrainVO> showTrainList();
    
    public TrainVO showTrain(String ID);
    
    public ArrayList<OrderVO> updateWaitingList(TranferingReceiptVO);
    
    public EntrainingReceiptVO entrain(ArrayList<OrderVO>);
    
    public ArrayList<EntrainingReceiptVO> updateEntrainingReceiptList(EntrainingReceiptVO);
    
    public fareVO computeFare(ArrayList<EntrainingReceiptVO>);
    
    public boolean updateFare(fareVO);
    
    public boolean showEntraningReceipt(ArrayList<EntraningReceiptVO>);
    
    public boolean updateEntraningReceipt(ArrayList<EntraningReceiptVO>);
}
